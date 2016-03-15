package com.aeq.query

class TableColumnMapping {
  def getTableAndColumnMapping(allColumns: Set[String], allTables: Set[String]): scala.collection.mutable.Map[String, List[String]] = {
    val columns = allColumns.toList.mkString(" ")
    val splitColumns = columns.split(" ").toList
    var columnsList = List[(String, String)]()
    for (col <- splitColumns) {
      val index = col.indexOf(".")
      val table = col.substring(0, index)
      val column = col.substring(index + 1, col.length())
      var value = (table, column)
      columnsList ++= List(value)
    }
    val tableColumnsMap = scala.collection.mutable.Map[String, List[String]]()
    val tables = allTables.toList
    for (col <- columnsList) {
      if (tables.contains(col._1)) {
        val colList = tableColumnsMap.get(col._1)
        var finalColList: List[String] = null
        if (colList.isEmpty) {
          finalColList = List[String]()
        } else {
          finalColList = colList.get
        }
        val newList = finalColList :+ col._2
        tableColumnsMap.put(col._1, newList)
      }
    }
    return tableColumnsMap
  }
  def getTableAndColumnMapping(allcolumns: Set[String], allTables: Set[String], map: Map[String, String]) {
    val value = map.values.toList
    val columns = allcolumns.toList.mkString(" ")
    val splitColumns = columns.split(" ").toList
    var columnsList = List[(String, String)]()
    for (col <- splitColumns) {
      val index = col.indexOf(".")
      val table = col.substring(0, index)
      val column = col.substring(index + 1, col.length())
      var value = (table, column)
      columnsList ++= List(value)
    }
    val tableColumnsMap = scala.collection.mutable.Map[String, List[String]]()
    for (col <- columnsList) {
      if (value.contains(col._1)) {
        val colList = tableColumnsMap.get(col._1)
        var finalColList: List[String] = null
        if (colList.isEmpty) {
          finalColList = List[String]()
        } else {
          finalColList = colList.get
        }
        val newList = finalColList :+ col._2
        tableColumnsMap.put(col._1, newList)
      }
    }
    println("Table Alaise Map "+map)
    println("Table column map "+ tableColumnsMap)
    val tableToAliasmap = map.map(pair => pair._2 -> pair._1)
    val res =tableColumnsMap.map{
      case(k,v)=>tableToAliasmap.getOrElse(k, k)->v
    } 
    println("Final Map "+res)
  }
}