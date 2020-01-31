package com.rizalfahrudin.submissionaplikasimoviecatalogue.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class MovieAndTv(
    var id: Int?,
    var title: String?,
    var date: String?,
    var image: String?,
    var description: String?
) : Parcelable