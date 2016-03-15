package com.aeq.query

class AllTablesName {
  def tablesAsString(columnsAfterFrom:List[String],withoutKeywords:String):String={
    var multipleTablesAsString = columnsAfterFrom.foldLeft(withoutKeywords) { (res, cur) =>
      res.replaceAll(cur, "")
    }
    return multipleTablesAsString
  }
  def getAllTables(alaise:Set[String],tableString:String):List[String]={
    val string = tableString.split(" ").mkString(" ").trim()
    
    val removeAsAlaise = new AllAlaiseName()
    val splitTables = removeAsAlaise.removeAsAlaiseFromTable(string).trim().replaceAll(" +", " ").split(" ").toList
    return splitTables.toList
    
  }
}