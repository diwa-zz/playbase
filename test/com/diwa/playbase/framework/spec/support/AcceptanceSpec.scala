package com.diwa.playbase.framework.spec.support

import play.api.test._
import com.diwa.playbase.framework.spec.support.InMemoryFixtures.inMemoryApp


abstract class WithInMemoryBrowser extends WithBrowser(app = inMemoryApp)
abstract class AcceptanceSpec extends BaseSpec
