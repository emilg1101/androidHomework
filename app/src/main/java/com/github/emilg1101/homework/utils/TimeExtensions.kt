package com.github.emilg1101.homework.utils

fun Int.toTimeFormat() = if (this < 10) "0$this" else this.toString()
