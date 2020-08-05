package main

import java.util.Properties

import org.apache.spark.sql.SparkSession
import utils.PropertiesUtils

object test extends App {
  val config = new Properties()
  val propertiesUtils = new PropertiesUtils()
  val propFilePath = args(0)


  val spark = SparkSession
    .builder()
    .appName("Stream-SQL-ADLS ")
    .enableHiveSupport()
    .getOrCreate()

  val applicationId=spark.sparkContext.getConf.getAppId

  if (args.length == 0 || args.length < 1) {
    println("No or less than 1 Agrument supplied, exit application")
    System.exit(0)
  }


  propertiesUtils.loadProperties()

  //Lines Of Code

  import java.util.Properties

  import org.apache.spark.sql.functions._
  val df =spark.read.format("CSV").load(config.getProperty("table.read.path"))
  df.show()
  df.write.format("CSV").save(config.getProperty("table.write.path"))


}
