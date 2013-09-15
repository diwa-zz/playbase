package com.diwa.playbase.framework.data.slick

import scala.slick.driver.{ MySQLDriver, H2Driver, ExtendedDriver }

trait DataBaseComponent {
  // the actual Slick driver (e.g. H2 or MySQL)
  val Driver: ExtendedDriver
  // dialect specific to this driver for functions that Slick does not support
  // Usage will be discussed in future blog post
  val dialect: DatabaseDialect[_]
  // A database instance for which connections can be created.
  // Encapsulates either a DataSource or parameters for DriverManager.getConnection().
  val handle: SlickDatabase
  // MySQL and H2 have different preferences on casing the table and column names.
  // H2 specifically prefers upper case
  def entityName(name: String): String = name
}

class H2(val handle: SlickDatabase) extends DataBaseComponent {
  val Driver = H2Driver
  val dialect = H2DatabaseDialect
  override def entityName(name: String): String = name.toUpperCase()
}

object H2 {
  val driverName = "org.h2.Driver"
}

class MySQL(val handle: SlickDatabase) extends DataBaseComponent {
  val Driver = MySQLDriver
  val dialect = MySqlDatabaseDialect
}

object MySQL {
  val driverName = "com.mysql.jdbc.Driver"
}