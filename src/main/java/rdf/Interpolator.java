package rdf;

public interface Interpolator<T> {
    T interpolate(T t1, T t2, double ratio);
}
