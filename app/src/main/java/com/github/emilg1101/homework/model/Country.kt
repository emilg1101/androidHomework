package com.github.emilg1101.homework.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Country(val id: Int, val name: String, val iso: Int, val location: String) : Parcelable
