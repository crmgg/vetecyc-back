package co.edu.uco.vetecyv.crosscuting.messagescatalog;

public enum MessagesEnumDoctorRule {
    DOCTOR_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada son nulos o inválidos."),
    DOCTOR_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada es 2 o 3."),
    DOCTOR_RULE_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    DOCTOR_RULE_DOCTOR_ALREADY_EXISTS("Doctor existente","Ya existe un doctor con número de identificación %s."),
    DOCTOR_RULE_NOT_FOUND("Doctor no encontrado","No existe doctor con id %s."),

    // Mensajes para validación de móvil (si se usan)
    DOCTOR_MOBILE_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de móvil son nulos o inválidos."),
    DOCTOR_MOBILE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de móvil es 2."),
    DOCTOR_MOBILE_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    DOCTOR_MOBILE_ALREADY_EXISTS("Móvil duplicado","Ya existe un doctor registrado con el móvil %s."),

    // Mensajes para validación de email (si se usan)
    DOCTOR_EMAIL_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de email son nulos o inválidos."),
    DOCTOR_EMAIL_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de email es 2."),
    DOCTOR_EMAIL_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    DOCTOR_EMAIL_ALREADY_EXISTS("Email duplicado","Ya existe un doctor registrado con el email %s.");

    private final String title;
    private final String content;

    MessagesEnumDoctorRule(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }
}

