package com.diwa.playbase.framework.business.domain.models

trait Model[M <: Model[M]] {
  val id: Option[Long]
  def withId(id: Long): M
}
