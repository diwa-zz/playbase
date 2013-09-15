package com.diwa.playbase.framework.data.slick

import scala.slick.session.PlayDatabase

class SlickDatabase(ds: javax.sql.DataSource) extends PlayDatabase {
  protected def datasource = ds
}

object SlickDatabase {

  def forDataSource(datasource: javax.sql.DataSource): SlickDatabase = {
    new SlickDatabase(datasource)
  }
}