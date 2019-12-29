package com.rizalfahrudin.submissionaplikasimoviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Movie(
    var title: String?,
    var date: String?,
    var genres: String?,
    var runtime: String?,
    var description: String?,
    var image: Int?
) : Parcelable