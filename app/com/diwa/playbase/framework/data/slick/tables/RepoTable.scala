package com.hirepro.framework.data.slick.tables

import play.api.db.slick.Config.driver.simple._
import scala.slick.lifted.{ Column, TypeMapper, ColumnOption }
import com.diwa.playbase.framework.business.domain.models.Model

abstract class RepoTable[M <: Model[M]](name: String) extends Table[M](name.toUpperCase) {
  def id = column[Long]("id", O.PrimaryKey, O.AutoInc, O.DBType("INT(11)"))
  def autoInc = * returning id

  override def column[C](n: String, options: ColumnOption[C]*)(implicit tm: TypeMapper[C]): Column[C] = {
    super.column(n.toUpperCase, options: _*)(tm)
  }
}

