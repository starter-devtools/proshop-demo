package com.sdt.proshop.extensions

import com.sdt.proshop.constants.DATE_FORMAT
import com.sdt.proshop.constants.DATE_TIME_FORMAT
import java.time.LocalDate
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

/**
 * Formats a ZonedDateTime with an API specific pattern.
 * @return the formatted dateTime
 */
fun ZonedDateTime.apiFormat(): String = ZonedDateTime.now().format(dateTimeFormatter)

/**
 * Formats a ZonedDateTime with the FormatStyle.LONG
 * @return the formatted dateTime
 */
fun ZonedDateTime.longFormat(): String = ZonedDateTime.now().format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG))

private val dateTimeFormatter: DateTimeFormatter = dateTimeFormatterPattern(DATE_TIME_FORMAT)

/**
 * Formats a LocalDate with an API specific pattern.
 * @return the formatted date
 */
fun LocalDate.apiFormat(): String = LocalDate.now().format(dateFormatter)
private val dateFormatter: DateTimeFormatter = dateTimeFormatterPattern(DATE_FORMAT)

private fun dateTimeFormatterPattern(pattern: String) = DateTimeFormatter.ofPattern(pattern)