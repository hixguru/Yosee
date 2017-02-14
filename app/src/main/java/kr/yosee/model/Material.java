package kr.yosee.model;

/**
 * Created by hwanik on 2017. 2. 6..
 */

public class Material {
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
}
