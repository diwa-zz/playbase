package com.diwa.playbase.framework.web.play.assethelpers

import com.diwa.playbase.framework.spec.support.BaseSpec

class AssetsSpec extends BaseSpec {
  "css" should {
    "render simple url" in {
      css.url("test") must equalTo("/assets/css/test.css")
    }
  }

  "img" should {
    "render simple image" in {
      img.url("test.png") must equalTo("/assets/img/test.png")
    }
  }
}
