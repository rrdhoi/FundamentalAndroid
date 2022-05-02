package com.belajarbareng

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class User(
    var name: String,
    var photo: Int,
    var userLocation : String,
) : Parcelable