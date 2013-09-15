package com.diwa.playbase.framework.data.slick.repositories

import scala.slick.lifted.Query
import play.api.db.slick.Config.driver.simple._
import java.sql.SQLException
import com.hirepro.framework.data.slick.tables.RepoTable
import com.diwa.playbase.framework.business.domain.models.Model
import com.diwa.playbase.framework.business.domain.repositories.Repo

trait DbRepo[M <: Model[M]] extends Repo[M] {
  protected def table: RepoTable[M]

  def get(id: Long)(implicit session: Session): M =
    (for (f <- table if f.id is id) yield f).first

  def getOption(id: Long)(implicit session: Session): Option[M] =
    (for (f <- table if f.id is id) yield f).firstOption

  def all()(implicit session: Session): Seq[M] = table.map(t => t).list

  def save(model: M)(implicit session: Session): M = {
    try {
      val result = model.id match {
        case Some(_) => update(model)
        case None    => model.withId(insert(model))
      }
      result
    } catch {
      case t: SQLException => throw new SQLException(s"error persisting $model", t)
    }
  }

  def count()(implicit session: Session): Int = Query(table.length).first

  def upsert(model: M)(implicit session: Session): M = {
    val maybeId = table.where(_.id === model.id).map(_.id).list
    maybeId match {
      case Nil     => insert(model)
      case List(_) => update(model)
    }
    model
  }

  def insertAll(models: M*)(implicit session: Session): Seq[Long] = {
    table.autoInc.insertAll(models: _*)
  }

  //HACK : Temporarily expose insert and update methods in addition to save to ease transition
  def insert(model: M)(implicit session: Session): Long = table.autoInc.insert(model)

  def update(model: M)(implicit session: Session): M = {
    val target = for (t <- table if t.id === model.id.get) yield t
    val count = target.update(model)
    assert(1 == count)
    model
  }
}
