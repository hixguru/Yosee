package kr.yosee.model;

import com.google.firebase.database.Exclude;
import com.google.firebase.database.IgnoreExtraProperties;
import java.util.HashMap;
import java.util.List;

/**
 * Created by hwanik on 2017. 1. 27..
 */

@IgnoreExtraProperties
public class Recipe {
    public String uid;
    public MainStep mainStep;
    public List<Material> materials;

    public Recipe() {
    }

    public static class Builder {
        public List<Material> materials;
        public MainStep mainStep;

        public Builder main(MainStep mainStep) {
            this.mainStep = mainStep;
            return this;
        }

        public Builder mat(List<Material> materials) {
            this.materials = materials;
            return this;
        }

        public Recipe build() {
            return new Recipe(this);
        }
    }

    public Recipe(Builder builder) {
        this.mainStep = builder.mainStep;
        this.materials = builder.materials;
    }

    // public Recipe(String uid, String mainTitle, String mainDescription, String mainImage,
    //               List<Material> materials) {
    //     this.uid = uid;
    //     this.mainTitle = mainTitle;
    //     this.mainDescription = mainDescription;
    //     this.mainImage = mainImage;
    //     this.materials = materials;
    // }

    @Exclude
    public HashMap<String, Object> toMap() {
        HashMap<String, Object> result = new HashMap<>();
        result.put("uid", uid);
        result.put("main", mainStep);
        result.put("materials", materials);

        return result;
    }

    public static class MainStep {
        public String mainTitle;
        public String mainDescription;
        public String mainImage;

        public MainStep() {
        }

        public MainStep(String mainTitle, String mainDescription, String mainImage) {
            this.mainTitle = mainTitle;
            this.mainDescription = mainDescription;
            this.mainImage = mainImage;
        }
    }
}
