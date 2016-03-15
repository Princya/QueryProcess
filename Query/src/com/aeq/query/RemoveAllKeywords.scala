package com.aeq.query

class RemoveAllKeywords {
  val keywords = List("from", "inner join", "outer join", "left join", "right join", "on", "and", "where")
  def removeKeywords(query:String):String={
    val withoutKeywords = keywords.foldLeft(query) { (res, cur) =>
      res.replaceAll(cur, "")
    }
    return withoutKeywords
  }
}