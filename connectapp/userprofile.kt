package com.example.connectapp

import android.os.Parcel
import android.os.Parcelable

data class userprofile(
    val name:String,
    val image:String,
    val age:String,
    val mail:String,
    val hobbies:String,
    val lookingfor:String,
    val describe:String
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!,
        parcel.readString()!!
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeString(image)
        parcel.writeString(age)
        parcel.writeString(mail)
        parcel.writeString(hobbies)
        parcel.writeString(lookingfor)
        parcel.writeString(describe)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<userprofile> {
        override fun createFromParcel(parcel: Parcel): userprofile {
            return userprofile(parcel)
        }

        override fun newArray(size: Int): Array<userprofile?> {
            return arrayOfNulls(size)
        }
    }
}
