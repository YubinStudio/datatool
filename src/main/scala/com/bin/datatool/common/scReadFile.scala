package com.bin.common

import com.bin.bean.EntityUnion.Person
import org.apache.spark.broadcast.Broadcast
import org.apache.spark.rdd.RDD
import org.apache.spark.sql.{SaveMode, SparkSession}

object scReadFile {
  def main(args: Array[String]): Unit = {
    val fileDir = "H:\\myIdea\\javautils\\person.txt"
    val spark: SparkSession = SparkSession.builder().master("local[*]").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val sc = spark.sparkContext
    val fRdd = spark.read.textFile(fileDir).rdd


    //    val result: RDD[(String, Int)] = fRdd.flatMap(_.split(",")).map((_, 1)).reduceByKey(_ + _)

    val dfResult = fRdd.map(x => {
      val line = x.split(",")
      Person(line(0), line(1), line(2), line(3))
    })
    import spark.implicits._
    dfResult.toDF().write.format("csv").mode(SaveMode.Overwrite).option("character", "UTF-8").save("H:\\myIdea\\javautils\\person.csv")

    val persons: RDD[Person] = fRdd.mapPartitions(x => {
      x.map(y => {
        val line = y.split(",")
        Person(line(0), line(1), line(2), line(3))
      })
    })
    /**
      * 注意：分发广播需要一个action操作触发。
      * 而非对象的引用。广播Person的对象引用会出错。 使用broadcast广播Person到每个Executor中！
      */
    val Bd: Broadcast[Array[Person]] = sc.broadcast(persons.collect())
    val persons1: Array[Person] = Bd.value
//    println("-----------------------------" + persons1)

    persons.foreach(x => println(x.name + "," + x.age + "," + x.pno + "," + x.lastUpdateTime))

    //    result.saveAsTextFile("H:\\myIdea\\javautils\\personOut")
    //    result.saveAsObjectFile("H:\\myIdea\\javautils\\personOutObj")

    spark.stop()
  }

}
