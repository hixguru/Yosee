package kr.yosee.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwanik on 2017. 2. 6..
 */

public class Material implements Parcelable {
    public String matName;
    public String matAmount;
    public String matUnit;

    public Material() {
    }

    public Material(String matName, String matAmount, String matUnit) {
        this.matName = matName;
        this.matAmount = matAmount;
        this.matUnit = matUnit;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.matName);
        dest.writeString(this.matAmount);
        dest.writeString(this.matUnit);
    }

    protected Material(Parcel in) {
        this.matName = in.readString();
        this.matAmount = in.readString();
        this.matUnit = in.readString();
    }

    public static final Parcelable.Creator<Material> CREATOR = new Parcelable.Creator<Material>() {
        @Override
        public Material createFromParcel(Parcel source) {
            return new Material(source);
        }

        @Override
        public Material[] newArray(int size) {
            return new Material[size];
        }
    };
}
