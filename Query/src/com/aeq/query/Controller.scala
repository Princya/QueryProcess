package com.aeq.query

object Controller extends App {
    
    val sqlQuery="SELECT Orders.OrderID, Customers.CustomerName, Orders.OrderDate FROM Orders INNER JOIN Customers ON Orders.CustomerID=Customers.CustomerID"
    val sqlQuery1 ="SELECT p.product_id, SUM(po.quantity) FROM `products` p LEFT JOIN `product_orders` po ON p.product_id=po.product_id LEFT JOIN `orders` o ON o.order_id=po.order_id AND o.is_current=po.is_current"
    
    val split = new SplitQuery()
    val queryAfterSplit = split.splitQuery(sqlQuery1)
    //Remove All Keywords
    val removeAllKeyWords = new RemoveAllKeywords()
    val withoutKeywords = removeAllKeyWords.removeKeywords(queryAfterSplit(1))
    //Get All Columns
    val columns = new AllColumnsName()
    val columnsAfterFrom =columns.getColumnsAfterFrom(withoutKeywords)
    val columnsBeforeFrom = columns.getColumnsBeforeFrom(queryAfterSplit(0))
    val allColumns = columns.getAllColumns(columnsAfterFrom, columnsBeforeFrom)
    println("All Columns "+allColumns)
    //Get All Alaises
    val findAlaise = new AllAlaiseName()
    val allAlaise = findAlaise.getAlaise(allColumns)
    println("All Alaises "+allAlaise)
    //Get All Table
    val table = new AllTablesName()
    val tableString =table.tablesAsString(columnsAfterFrom, withoutKeywords)
    val tablesWithAlaise = table.getAllTables(allAlaise, tableString)
    //Check the table has alaise or not
    val check = findAlaise.checkAlaiseNameAndTableName(allAlaise,tablesWithAlaise.toSet)
    val mapping = new TableColumnMapping()
    if(check){
      val allTables = tablesWithAlaise.toSet
      println("All Tables : "+ allTables)
      println(mapping.getTableAndColumnMapping(allColumns, tablesWithAlaise.toSet))
    }
    else{
      val tableList =tablesWithAlaise.toList
      val diff = tablesWithAlaise.toList.diff(allAlaise.toList)
      val allTables = diff.toSet
      println("All Tables : "+ allTables)
      val tableAlaise=findAlaise.tableAndAlaiseMap(tableList.mkString(" "))
      mapping.getTableAndColumnMapping(allColumns, allTables,tableAlaise)
    }
  }
