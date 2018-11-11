package com.github.emilg1101.homework.model

import android.os.Parcelable
import android.support.annotation.RawRes
import kotlinx.android.parcel.Parcelize

@Parcelize
class Track(val title: String, @RawRes val raw: Int) : Parcelable
