package rdf;

import static org.junit.Assert.*;

import java.util.stream.IntStream;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(JUnitParamsRunner.class)
public class HSBTest {
    
    private HSB hsb;
    private float epsilon;
    
    @Before
    public void setUp() {
        hsb = new HSB(0.0f, 0.0f, 0.0f);
        epsilon = 0.0001f;
    }
    
    public Object[] getSingleValues() {
        return new Object[] {
                // { value, expected_result }
                new Object[] { 0.0f, 0.0f },
                new Object[] { 1.0f, 1.0f },
                new Object[] { 0.3f, 0.3f },
                new Object[] { 0.5f, 0.5f },
                new Object[] { -0.1f, 0.0f },
                new Object[] { 1.1f, 1.0f },
                new Object[] { 3.0f, 1.0f },
                new Object[] { -5.0f, 0.0f },
        };
    }
    
    public Object[] getAllValues() {
        return new Object[] {
                // {hue, exp_hue, sat, exp_sat, bright, exp_bright }
                new Object[] { 0.0f, 0.0f, 0.0f, 0.0f, 0.0f, 0.0f },
                new Object[] { 1.0f, 1.0f, 1.0f, 1.0f, 1.0f, 1.0f },
                new Object[] { 0.6f, 0.6f, 0.7f, 0.7f, 0.8f, 0.8f },
                new Object[] { 0.5f, 0.5f, 0.5f, 0.5f, 0.5f, 0.5f },
                new Object[] { -0.1f, 0.0f, -0.1f, 0.0f, -0.1f, 0.0f },
                new Object[] { 1.1f, 1.0f, 1.1f, 1.0f, 1.1f, 1.0f },
        };
    }
    
    public Object[] getEqualityTestsData() {
        return new Object[] {
                // {first, second, expected result}
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(0.0f, 0.0f, 0.0f), true },
                new Object[] { new HSB(0.5f, 0.5f, 0.5f), new HSB(0.5f, 0.5f, 0.5f), true },
                new Object[] { new HSB(1.0f, 1.0f, 1.0f), new HSB(1.0f, 1.0f, 1.0f), true },
                new Object[] { new HSB(0.1f, 0.0f, 0.0f), new HSB(0.0f, 0.0f, 0.0f), false },
                new Object[] { new HSB(0.0f, 0.1f, 0.0f), new HSB(0.0f, 0.0f, 0.0f), false },
                new Object[] { new HSB(0.0f, 0.0f, 0.1f), new HSB(0.0f, 0.0f, 0.0f), false },
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(0.1f, 0.0f, 0.0f), false },
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(0.0f, 0.1f, 0.0f), false },
                new Object[] { new HSB(0.0f, 0.0f, 0.0f), new HSB(0.0f, 0.0f, 0.1f), false },
        };
    }
    
    @Test
    @Parameters(method = "getAllValues")
    public void testConstructing(float hue, float exp_hue, float sat, float exp_sat, float bright, float exp_bright) {
        HSB model = new HSB(hue, sat, bright);
        assertEquals(exp_hue, model.getHue(), epsilon);
        assertEquals(exp_sat, model.getSaturation(), epsilon);
        assertEquals(exp_bright, model.getBrightness(), epsilon);
    }
    
    @Test
    @Parameters(method = "getSingleValues")
    public void testHue(float value, float expected) {
       hsb.setHue(value);
       assertEquals(expected, hsb.getHue(), epsilon);
    }
    
    @Test
    @Parameters(method = "getSingleValues")
    public void testSaturation(float value, float expected) {
       hsb.setSaturation(value);
       assertEquals(expected, hsb.getSaturation(), epsilon);
    }

    @Test
    @Parameters(method = "getSingleValues")
    public void testBrightness(float value, float expected) {
       hsb.setBrightness(value);
       assertEquals(expected, hsb.getBrightness(), epsilon);
    }
    
    @Test
    public void modifyComponents() {
        float[] components = hsb.getColorComponents();
        float oldHue = hsb.getHue();
        float oldSaturation = hsb.getSaturation();
        float oldBrightness = hsb.getBrightness();
        IntStream.range(0, components.length).forEach(i -> components[i] = -(i+1));
        float newHue = hsb.getHue();
        float newSaturation = hsb.getSaturation();
        float newBrightness = hsb.getBrightness();
        assertNotEquals(oldHue, newHue, epsilon);
        assertNotEquals(oldSaturation, newSaturation, epsilon);
        assertNotEquals(oldBrightness, newBrightness, epsilon);
    }
    
    @Test
    @Parameters(method = "getEqualityTestsData")
    public void testEquality(HSB first, HSB second, boolean expected) {
        assertEquals(expected, first.equals(second));
    }
}
