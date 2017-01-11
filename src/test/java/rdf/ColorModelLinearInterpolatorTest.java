package rdf;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class ColorModelLinearInterpolatorTest {
    private Interpolator<HSB> interpolator;
    private float epsilon;
    
    @Before
    public void setUp() {
        interpolator = new ColorModelLinearInterpolator<HSB>();
        epsilon = 0.0001f;
    }
    
    public Object[] getTestData() {
        return new Object[] {
                // component1, component2, ration, expected result
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(0.0f, 0.0f, 0.0f), 0.0f, new HSB(0.0f, 0.0f, 0.0f)},
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(0.0f, 0.0f, 0.0f), 0.5f, new HSB(0.0f, 0.0f, 0.0f)},
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(0.0f, 0.0f, 0.0f), 1.0f, new HSB(0.0f, 0.0f, 0.0f)},
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(1.0f, 1.0f, 1.0f), 0.0f, new HSB(0.0f, 0.0f, 0.0f)},
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(1.0f, 1.0f, 1.0f), 0.25f, new HSB(0.25f, 0.25f, 0.25f)},
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(1.0f, 1.0f, 1.0f), 0.5f, new HSB(0.5f, 0.5f, 0.5f)},
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(1.0f, 1.0f, 1.0f), 1.0f, new HSB(1.0f, 1.0f, 1.0f)},
                new Object[] { new HSB(1.0f, 1.0f, 1.0f), new HSB(0.0f, 0.0f, 0.0f), 0.0f, new HSB(1.0f, 1.0f, 1.0f)},
                new Object[] { new HSB(1.0f, 1.0f, 1.0f), new HSB(0.0f, 0.0f, 0.0f), 0.25f, new HSB(0.75f, 0.75f, 0.75f)},
                new Object[] { new HSB(1.0f, 1.0f, 1.0f), new HSB(0.0f, 0.0f, 0.0f), 0.5f, new HSB(0.5f, 0.5f, 0.5f)},
                new Object[] { new HSB(1.0f, 1.0f, 1.0f), new HSB(0.0f, 0.0f, 0.0f), 1.0f, new HSB(0.0f, 0.0f, 0.0f)},
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(0.4f, 0.6f, 0.8f), 0.5f, new HSB(0.2f, 0.3f, 0.4f)},
        };
    }
    
    @Test
    @Parameters(method = "getTestData")
    public void testInterpolation(HSB c1, HSB c2, float ratio, HSB expected) {
        HSB result = interpolator.interpolate(c1, c2, ratio);
        float[] eComponents = expected.getColorComponents();
        float[] resComponents = result.getColorComponents();
        boolean equality = IntStream.range(0, eComponents.length).mapToObj(i -> Math.abs(eComponents[i] - resComponents[i]) < epsilon).reduce(true, (e, ce) -> e && ce);
        assertTrue(equality);
    }

}
