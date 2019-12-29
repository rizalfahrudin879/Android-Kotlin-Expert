package com.rizalfahrudin.submissionaplikasimoviecatalogue

import android.os.Parcel
import android.os.Parcelable

data class Movie(
    var title: String?,
    var date: String?,
    var genres: String?,
    var runtime: String?,
    var description: String?,
    var image: Int?
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readString(),
        parcel.readValue(Int::class.java.classLoader) as? Int
    )

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(title)
        parcel.writeString(date)
        parcel.writeString(genres)
        parcel.writeString(runtime)
        parcel.writeString(description)
        parcel.writeValue(image)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Movie> {
        override fun createFromParcel(parcel: Parcel): Movie {
            return Movie(parcel)
        }

        override fun newArray(size: Int): Array<Movie?> {
            return arrayOfNulls(size)
        }
    }
}