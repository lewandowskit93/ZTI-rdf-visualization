package rdf;

import java.awt.Color;

/**
 * Interface for representing colors in various color models i.e. RGB, CMYK.
 * @author ventyl
 *
 * @param <T> - model
 */
public interface ColorModel<T extends ColorModel<T>> extends Cloneable {
    /** 
     * @return Array of color components for given instance.
     */
    public float[] getColorComponents();
    
    /**
     * @return java.awt.Color representation of color in color model.
     */
    public Color toColor();
    
    /**
     * @return Clone of given instance.
     */
    public T clone();
    
    /**
     * Checks for equality of given colors. Colors are equal if all of it's corresponding components are equal.
     * @param other
     * @return true if colors are equal of false otherwise.
     */
    public boolean equals(T other);
}
