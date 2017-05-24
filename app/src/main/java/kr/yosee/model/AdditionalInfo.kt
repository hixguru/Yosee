package kr.yosee.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by hwanik on 2017. 5. 24..
 */
data class AdditionalInfo(val cookingTime: String, val serving: String, val tip: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<AdditionalInfo> = object : Parcelable.Creator<AdditionalInfo> {
            override fun createFromParcel(source: Parcel): AdditionalInfo = AdditionalInfo(source)
            override fun newArray(size: Int): Array<AdditionalInfo?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
    source.readString(),
    source.readString(),
    source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(cookingTime)
        dest.writeString(serving)
        dest.writeString(tip)
    }
}
