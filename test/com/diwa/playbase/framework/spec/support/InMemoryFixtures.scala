package com.diwa.playbase.framework.spec.support

import play.api.test.Helpers._
import play.api.test.FakeApplication

object InMemoryFixtures {
  def inMemoryApp = FakeApplication(additionalConfiguration = inMemoryDatabase())
}
