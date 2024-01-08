package com.sdt.proshop.extensions

/**
 * Sanity checks that a String is valid.
 * @return true if the string is not null, empty, or blank.
 */
fun String?.isNotEmptyOrBlank(): Boolean = !this.isNullOrEmpty() && this.isNotBlank()

/**
 * Checks if an Object is null
 * @return true if the object is null
 */
fun Any?.isNull(): Boolean = this == null

/**
 * Checks if an Object is not null
 * @return true if the object is not null
 */
fun Any?.isNotNull(): Boolean = this != null

/**
 * A String checks itself for validity.
 */
fun String?.checkSelf(): String? {
    return if(this.isNotEmptyOrBlank()) {
        this
    } else {
        null
    }
}
