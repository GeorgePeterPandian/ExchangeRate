package com.george.exchange.rates.core.defaultvalues

fun Long?.orZero() = this ?: 0L

fun Int?.orZero() = this ?: 0

fun Boolean?.orFalse() = this ?: false