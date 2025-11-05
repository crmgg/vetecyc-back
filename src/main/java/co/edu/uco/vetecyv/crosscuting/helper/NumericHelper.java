package co.edu.uco.vetecyv.crosscuting.helper;

import java.math.BigDecimal;
import java.math.RoundingMode;

public final class NumericHelper {

    private NumericHelper() {}

    public static Integer getDefault(final Integer value, final Integer defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static Double getDefault(final Double value, final Double defaultValue) {
        return value == null ? defaultValue : value;
    }

    public static Integer getDefaultWithZero(final Integer value) {
        return getDefault(value, Integer.valueOf(0));
    }

    public static Double getDefaultWithZero(final Double value) {
        return getDefault(value, Double.valueOf(0.0d));
    }

    public static Double getDefaultWithZero(  ) {
        return (0.0);
    }

    public static Integer safeParseInteger(final String text) {
        if (text == null) return Integer.valueOf(0);
        try {
            return Integer.valueOf(text.trim());
        } catch (NumberFormatException ex) {
            return Integer.valueOf(0);
        }
    }

    public static Double safeParseDouble(final String text) {
        if (text == null) return Double.valueOf(0.0d);
        try {
            return Double.valueOf(Double.parseDouble(text.trim()));
        } catch (NumberFormatException ex) {
            return Double.valueOf(0.0d);
        }
    }

    public static boolean isGreaterThanZero(final Double value) {
        return Double.compare(getDefaultWithZero(value).doubleValue(), 0.0d) > 0;
    }

    public static boolean isGreaterOrEqualZero(final Double value) {
        return Double.compare(getDefaultWithZero(value).doubleValue(), 0.0d) >= 0;
    }

    public static boolean isZero(final Double value) {
        return Double.compare(getDefaultWithZero(value).doubleValue(), 0.0d) == 0;
    }

    public static boolean isNegative(final Double value) {
        return Double.compare(getDefaultWithZero(value).doubleValue(), 0.0d) < 0;
    }

    public static Double round(final Double value, final int decimals) {
        final BigDecimal bd = BigDecimal.valueOf(getDefaultWithZero(value));
        return bd.setScale(Math.max(0, decimals), RoundingMode.HALF_UP).doubleValue();
    }

    public static boolean betweenInclusive(final Double value, final Double min, final Double max) {
        final double v = getDefaultWithZero(value).doubleValue();
        final double a = getDefaultWithZero(min).doubleValue();
        final double b = getDefaultWithZero(max).doubleValue();
        return Double.compare(v, a) >= 0 && Double.compare(v, b) <= 0;
    }
}