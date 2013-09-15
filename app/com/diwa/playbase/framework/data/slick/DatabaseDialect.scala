package com.diwa.playbase.framework.data.slick

trait DatabaseDialect[T <: DataBaseComponent] {

}

object MySqlDatabaseDialect extends DatabaseDialect[MySQL] {

}

object H2DatabaseDialect extends DatabaseDialect[H2] {

}
