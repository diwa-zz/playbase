package com.diwa.playbase.framework.business.domain.repositories

import scala.slick.session.Session
import com.diwa.playbase.framework.business.domain.models.Model

trait Repo[M <: Model[M]] {
  def get(id: Long)(implicit session: Session): M
  def getOption(id: Long)(implicit session: Session): Option[M]

  def all()(implicit session: Session): Seq[M]
  def save(model: M)(implicit session: Session): M
  def count()(implicit session: Session): Int
  def upsert(model: M)(implicit session: Session): M

  def insertAll(models: M*)(implicit session: Session): Seq[Long]

  def insert(model: M)(implicit session: Session): Long
  def update(model: M)(implicit session: Session): M
}

