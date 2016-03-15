package com.aeq.query

class SplitQuery {
  def splitQuery(query: String) :List[String]={
    val lower = query.toLowerCase();
    val tables = lower.split("from").toList
    return tables
  }
}