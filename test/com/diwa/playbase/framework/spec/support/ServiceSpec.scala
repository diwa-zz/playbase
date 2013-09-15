package com.diwa.playbase.framework.spec.support

import com.diwa.playbase.framework.business.application.services.Service

abstract class ServiceSpec[S <: Service[S]] extends ApplicationSpec
