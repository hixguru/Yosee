package kr.yosee.model;

import android.os.Parcel;
import android.os.Parcelable;
import java.util.List;

/**
 * Created by hwanik on 2017. 1. 27..
 */

public class Recipe implements Parcelable {
    private String objectId;
    private MainStep mainStep;
    private AdditionalInfo additionalInfo;
    private List<Material> materials;

    public Recipe() {
    }

    public static class Builder {
        String objectId;
        MainStep mainStep;
        List<Material> materials;
        AdditionalInfo additionalInfo;

        public Builder objectId(String objectId) {
            this.objectId = objectId;
            return this;
        }

        public Builder main(MainStep mainStep) {
            this.mainStep = mainStep;
            return this;
        }

        public Builder additionalInfo(AdditionalInfo additionalInfo) {
            this.additionalInfo = additionalInfo;
            return this;
        }

        public Builder materials(List<Material> materials) {
            this.materials = materials;
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }

    public Recipe(Builder builder) {
        this.objectId = builder.objectId;
        this.mainStep = builder.mainStep;
        this.additionalInfo = builder.additionalInfo;
        this.materials = builder.materials;
    }

    public MainStep getMainStep() {
        return mainStep;
    }

    public AdditionalInfo getAdditionalInfo() {
        return additionalInfo;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.objectId);
        dest.writeParcelable(this.mainStep, flags);
        dest.writeParcelable(this.additionalInfo, flags);
        dest.writeTypedList(this.materials);
    }

    protected Recipe(Parcel in) {
        this.objectId = in.readString();
        this.mainStep = in.readParcelable(MainStep.class.getClassLoader());
        this.additionalInfo = in.readParcelable(AdditionalInfo.class.getClassLoader());
        this.materials = in.createTypedArrayList(Material.CREATOR);
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel source) {
            return new Recipe(source);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };
}
