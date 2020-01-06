package com.bin.common

import org.apache.spark.sql.{Dataset, SparkSession}

object scReadCsv {
  def main(args: Array[String]): Unit = {
    val spark: SparkSession = SparkSession.builder().appName("scReadCsv").master("local[2]").getOrCreate()
    spark.sparkContext.setLogLevel("WARN")

    val filePath = "H:\\myIdea\\javautils\\person.csv\\part-r-00000-f67461a6-bc64-4282-b37f-8f0d288bdccc.csv"
    val csvData: Dataset[String] = spark.read.format("csv").textFile(filePath)


    val array: Array[String] = csvData.rdd.flatMap(_.split(",")).take(10)
    for (str <- array) {
      println(str)
    }

    spark.stop()
  }
}
