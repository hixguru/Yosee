package kr.yosee.model

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by hwanik on 2017. 5. 24..
 */
data class MainStep(var title: String, var description: String, var image: String) : Parcelable {
    companion object {
        @JvmField val CREATOR: Parcelable.Creator<MainStep> = object : Parcelable.Creator<MainStep> {
            override fun createFromParcel(source: Parcel): MainStep = MainStep(source)
            override fun newArray(size: Int): Array<MainStep?> = arrayOfNulls(size)
        }
    }

    constructor(source: Parcel) : this(
        source.readString(),
        source.readString(),
        source.readString()
    )

    override fun describeContents() = 0

    override fun writeToParcel(dest: Parcel, flags: Int) {
        dest.writeString(title)
        dest.writeString(description)
        dest.writeString(image)
    }
}
