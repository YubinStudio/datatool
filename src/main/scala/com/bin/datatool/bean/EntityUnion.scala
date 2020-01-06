package com.bin.bean

import com.esotericsoftware.kryo.Kryo
import org.apache.spark.serializer.KryoRegistrator

/*
* 自定义实体
* */
object EntityUnion {
  case class Person(name: String, age: String, pno: String, lastUpdateTime: String) extends KryoRegistrator {
    override def registerClasses(kryo: Kryo): Unit = {
      kryo.register(classOf[Person])
    }
  }



}
