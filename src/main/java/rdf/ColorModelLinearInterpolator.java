package rdf;

import java.util.stream.IntStream;


public class ColorModelLinearInterpolator<CM extends ColorModel<CM>> implements
        Interpolator<CM> {

    @Override
    public CM interpolate(CM c1, CM c2, double ratio) {
        float[] components1 = c1.getColorComponents();
        float[] components2 = c2.getColorComponents();
        CM result = c1.clone();
        float[] resultComponents = result.getColorComponents();
        IntStream.range(0, components1.length).forEach(
           i -> resultComponents[i] = components1[i] + (components2[i]-components1[i]) * (float)ratio
         );
        return result;
    }

}
