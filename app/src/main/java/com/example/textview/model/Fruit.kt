package com.example.textview.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Fruit(
    val id: Int,
    val name: String,
    val image: Int
) : Parcelable
