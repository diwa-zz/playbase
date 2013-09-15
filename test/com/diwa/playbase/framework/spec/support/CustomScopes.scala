package com.diwa.playbase.framework.spec.support

import org.specs2.execute.AsResult
import org.specs2.execute.Result

abstract class WithInMemoryDbData extends WithInMemoryApplication {
  override def around[T: AsResult](t: => T): Result = super.around {
    setupData()
    t
  }

  def setupData() {
    // setup data
  }
}