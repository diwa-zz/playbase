package com.diwa.playbase.framework.data.slick

import net.codingwell.scalaguice.ScalaModule
import com.google.inject.{ Singleton, AbstractModule }

trait DbInfo {
  def database: SlickDatabase
  def driverName: String
}

class SlickModule(dbInfo: DbInfo) extends AbstractModule with ScalaModule {
  def configure(): Unit = {
    lazy val db = dbInfo.driverName match {
      case MySQL.driverName => new MySQL(dbInfo.database)
      case H2.driverName    => new H2(dbInfo.database)
    }
    bind[DataBaseComponent].toInstance(db)
    bind[Database].in[Singleton]
  }
}
