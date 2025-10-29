// java
package co.edu.uco.vetecyv.crosscuting.helper;

import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;

public final class DateHelper {

    // Fecha mínima permitida: 1 de enero del 2000 00:00:00.000
    private static final Date MIN_DATE;
    static {
        Calendar cal = new GregorianCalendar(2000, Calendar.JANUARY, 1, 0, 0, 0);
        cal.set(Calendar.MILLISECOND, 0);
        MIN_DATE = cal.getTime();
    }

    private DateHelper() {
        // Evita instanciación
    }

    /**
     * Devuelve la fecha y hora actual.
     */
    public static Date getDefault() {
        return new Date();
    }

    /**
     * Devuelve la fecha recibida si no es nula y válida; de lo contrario, devuelve la fecha actual.
     */
    public static Date getDefault(final Date value) {
        if (value == null || value.before(MIN_DATE)) {
            return getDefault();
        }
        return new Date(value.getTime());
    }

    /**
     * Devuelve la fecha mínima permitida (01/01/2000 00:00).
     */
    public static Date getMinDate() {
        return new Date(MIN_DATE.getTime());
    }

    /**
     * Verifica si una fecha es válida (no antes del 01/01/2000).
     */
    public static boolean isValid(final Date value) {
        return value != null && !value.before(MIN_DATE);
    }
}