package com.diwa.playbase.framework.data.slick.types.mappers

import slick.lifted.MappedTypeMapper
import org.joda.time.DateTime
import java.sql.Timestamp
import slick.lifted.TypeMapper.DateTypeMapper

object DateTimeMapper {

  implicit def date2TimeStamp = MappedTypeMapper.base[DateTime, Timestamp](
    dateTime => new Timestamp(dateTime.getMillis),
    date => new DateTime(date))
}

