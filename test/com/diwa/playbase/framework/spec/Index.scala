package com.diwa.playbase.framework.spec

import org.specs2._
import runner.SpecificationsFinder._
import org.specs2.io.Path

abstract class Index extends Specification {
  def is =

    examplesLinks("All specifications")

  def examplesLinks(t: String) = t.title ^ specifications(basePath = Path("test").dirPath).map(see)
}
