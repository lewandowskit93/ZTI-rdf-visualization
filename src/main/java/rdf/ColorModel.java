package rdf;

import java.awt.Color;

public interface ColorModel<T extends ColorModel<T>> extends Cloneable {
    public float[] getColorComponents();
    public Color toColor();
    public T clone();
    public boolean equals(T other);
}
