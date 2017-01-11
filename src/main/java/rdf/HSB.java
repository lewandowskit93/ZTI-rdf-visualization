package rdf;

import java.awt.Color;
import java.util.Arrays;

public class HSB implements ColorModel<HSB> {
    private float[] colorComponents = new float[3];
    
    public HSB(float hue, float saturation, float brightness) {
        setHue(hue);
        setSaturation(saturation);
        setBrightness(brightness);
    }
    
    public float getHue() {
        return colorComponents[0];
    }
    
    public void setHue(float hue) {
        colorComponents[0] = Math.min(1.0f,Math.max(0.0f, hue));
    }
    
    public float getSaturation() {
        return colorComponents[1];
    }
    
    public void setSaturation(float saturation) {
        colorComponents[1] = Math.min(1.0f,Math.max(0.0f, saturation));
    }
    
    public float getBrightness() {
        return colorComponents[2];
    }
    
    public void setBrightness(float brightness) {
        colorComponents[2] = Math.min(1.0f,Math.max(0.0f, brightness));
    }
    
    public float[] getColorComponents() {
        return colorComponents;
    }
    
    public Color toColor() {
        return Color.getHSBColor(getHue(), getSaturation(), getBrightness());
    }
    
    @Override
    public HSB clone() {
        return new HSB(getHue(), getSaturation(), getBrightness());
    }

    @Override
    public boolean equals(HSB other) {
        if (other == null) return false;
        return Arrays.equals(getColorComponents(), other.getColorComponents());
    }
}
