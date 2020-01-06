package com.bin.datatool.rdd

import org.apache.spark.rdd.RDD
import org.apache.spark.sql.SparkSession

/**
  * @program: datatool
  * @description: ${description}
  * @author: jyb
  * @create: 2019-08-27 18:13
  **/
object RddTest {
  def main(args: Array[String]): Unit = {
    val spark = SparkSession.builder().master("local[4]").appName("rddTest").getOrCreate()
    val sc = spark.sparkContext

    val arr = Array(1,2,3,4,5)
    val rt: RDD[Int] = sc.parallelize(arr)
//    rt.collect()
    rt.foreach(x=>print(x))
  }
}
