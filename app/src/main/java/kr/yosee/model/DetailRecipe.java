package kr.yosee.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hwanik on 2017. 1. 28..
 */

public class DetailRecipe implements Parcelable {
    private String name;
    private String num;
    private String unit;

    public DetailRecipe(String name, String num, String unit) {
        this.name = name;
        this.num = num;
        this.unit = unit;
    }

    public DetailRecipe(Parcel in) {
        name = in.readString();
        num = in.readString();
        unit = in.readString();
    }

    public static final Creator<DetailRecipe> CREATOR = new Creator<DetailRecipe>() {
        @Override
        public DetailRecipe createFromParcel(Parcel in) {
            return new DetailRecipe(in);
        }

        @Override
        public DetailRecipe[] newArray(int size) {
            return new DetailRecipe[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(num);
        parcel.writeString(unit);
    }
}
