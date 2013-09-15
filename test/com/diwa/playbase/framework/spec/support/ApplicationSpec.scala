package com.diwa.playbase.framework.spec.support

import play.api.test.WithApplication
import com.diwa.playbase.framework.spec.support.InMemoryFixtures.inMemoryApp


abstract class WithInMemoryApplication extends WithApplication(inMemoryApp)

abstract class ApplicationSpec extends BaseSpec
