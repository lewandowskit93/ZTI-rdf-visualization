package rdf;

/**
 * Interface for calculating interpolation between two values.
 * @author ventyl
 *
 * @param <T> Type of value to interpolate between.
 */
public interface Interpolator<T> {
    /**
     * @param t1 First value
     * @param t2 Second value
     * @param ratio Ratio for interpolation
     * @return Interpolated value
     */
    T interpolate(T t1, T t2, double ratio);
}
