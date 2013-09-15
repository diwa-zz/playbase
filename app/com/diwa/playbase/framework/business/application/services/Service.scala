package com.diwa.playbase.framework.business.application.services

trait Service[T <: Service[T]] {
}

trait AppService[T <: AppService[T]] extends Service[T] {
}