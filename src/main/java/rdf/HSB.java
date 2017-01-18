package rdf;

import java.awt.Color;
import java.util.Arrays;

/**
 * Class representing HSB (Hue, Saturation, Brightness) color model.
 * @author ventyl
 */
public class HSB implements ColorModel<HSB> {
    private float[] colorComponents = new float[3];
    
    /**
     * Creates a new HSB color model instance by given components.
     * @param hue Color's hue component
     * @param saturation Color's saturation component
     * @param brightness Color's brightness component
     */
    public HSB(float hue, float saturation, float brightness) {
        setHue(hue);
        setSaturation(saturation);
        setBrightness(brightness);
    }
    
    /**
     * @return Color's hue component
     */
    public float getHue() {
        return colorComponents[0];
    }
    
    /**
     * Sets color's hue component to given value
     * @param hue
     */
    public void setHue(float hue) {
        colorComponents[0] = Math.min(1.0f,Math.max(0.0f, hue));
    }
    
    /**
     * @return Color's saturation component
     */
    public float getSaturation() {
        return colorComponents[1];
    }
    
    /**
     * Sets color's saturation component
     * @param saturation
     */
    public void setSaturation(float saturation) {
        colorComponents[1] = Math.min(1.0f,Math.max(0.0f, saturation));
    }
    
    /**
     * @return Color's brightness component
     */
    public float getBrightness() {
        return colorComponents[2];
    }
    
    /**
     * Sets color's brightness component
     * @param brightness
     */
    public void setBrightness(float brightness) {
        colorComponents[2] = Math.min(1.0f,Math.max(0.0f, brightness));
    }
    
    /**
     * Returns color components array containing 3 elements in order Hue, Saturation, Brightness
     */
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
