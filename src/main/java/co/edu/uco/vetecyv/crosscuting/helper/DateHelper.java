package co.edu.uco.vetecyv.crosscuting.helper;

import java.time.LocalDateTime;

public final class DateHelper {

    // Fecha mínima permitida: 1 de enero del 2000
    private static final LocalDateTime MIN_DATE = LocalDateTime.of(2000, 1, 1, 0, 0);

    private DateHelper() {
        // Evita instanciación
    }

    /**
     * Devuelve la fecha y hora actual.
     */
    public static LocalDateTime getDefault() {
        return LocalDateTime.now();
    }

    /**
     * Devuelve la fecha recibida si no es nula y válida; de lo contrario, devuelve la fecha actual.
     */
    public static LocalDateTime getDefault(final LocalDateTime value) {
        if (value == null || value.isBefore(MIN_DATE)) {
            return getDefault();
        }
        return value;
    }

    /**
     * Devuelve la fecha mínima permitida (01/01/2000 00:00).
     */
    public static LocalDateTime getMinDate() {
        return MIN_DATE;
    }

    /**
     * Verifica si una fecha es válida (no antes del 01/01/2000).
     */
    public static boolean isValid(final LocalDateTime value) {
        return value != null && !value.isBefore(MIN_DATE);
    }
}
