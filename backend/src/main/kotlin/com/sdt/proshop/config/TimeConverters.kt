package com.sdt.proshop.config

import org.springframework.core.convert.converter.Converter
import java.time.Instant
import java.util.*

/**
 * Allows mongo to read a timestamp as an Instant
 */
class InstantReadConverter: Converter<Date, Instant> {
    override fun convert(source: Date): Instant? = source.toInstant()
}

/**
 * Allows mongo to write an Instant as a timestamp
 */
class InstantWriteConverter: Converter<Instant, Date> {
    override fun convert(source: Instant): Date? = Date.from(source)
}