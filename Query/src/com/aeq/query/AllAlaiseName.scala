package com.aeq.query
import scala.collection.mutable.ListBuffer
import java.lang.Boolean
class AllAlaiseName {
   def getAlaise(columns:Set[String]):Set[String]={
    val findAliases = new ListBuffer[String]()
    for (x <- columns) {
      if (x.contains(".")) {
        //x.replaceAll("()", "")
        val index = x.indexOf(".")
        findAliases += x.substring(0, index)
      }
    }
   return findAliases.toSet
  }
  def removeAsAlaiseFromTable(string:String):String={
    val split = string.split(" ")
    val makeString = new ListBuffer[String]()
    for (x <- split) {
      if (!x.equals("as")) {
        makeString += x
      }
    }
    return makeString.mkString(" ")
  }
  def checkAlaiseNameAndTableName(tables:Set[String],alaise:Set[String]):Boolean={
    if(alaise.toList.equals(tables.toList)){
      return true
    }
    else{
      return false
    }
  }
  def tableAndAlaiseMap(table: String): Map[String, String] ={
    var tableAndAlaiseMap: Map[String, String] = null
    val split = table.trim().replaceAll(" +", " ")
    val cc = split.split(" ").toList
    val alaises = new ListBuffer[String]()
    val tables = new ListBuffer[String]()
    var i = 0
    for (x <- cc) {
      if (i % 2 == 0) {
        tables += x
      } else {
        alaises += x
      }
      i = i + 1
    }
    //println(tables.zip(alaises).toMap)
    tableAndAlaiseMap = tables.zip(alaises).toMap
    return tables.zip(alaises).toMap
  }
}