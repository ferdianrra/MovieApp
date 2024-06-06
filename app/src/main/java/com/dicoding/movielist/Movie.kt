package com.dicoding.movielist

import android.media.Rating
import android.os.Parcelable
import android.provider.ContactsContract.Contacts.Photo
import kotlinx.parcelize.Parcelize
import java.time.Duration

@Parcelize
data class Movie(
    val title: String,
    val description: String,
    val photo: Int,
    val rating: String,
    val genre: String,
    val duration: String
) : Parcelable


