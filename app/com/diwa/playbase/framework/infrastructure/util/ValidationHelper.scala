package com.diwa.playbase.framework.infrastructure.util

import org.joda.time.DateTime

object ValidationHelper {
  def validate(condition: Boolean, message: String): Option[String] = if (condition) Some(message) else None
  def validate(option: Option[_], message: String): Option[String] = validate(option.isDefined, message)
  def validate(from: Option[DateTime], to: Option[DateTime], message: String): Option[String] = {
    validate(!from.forall(_.isBeforeNow()), message) orElse validate(!to.forall(_.isAfterNow()), message)
  }
}
