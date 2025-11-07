package co.edu.uco.vetecyv.crosscuting.messagescatalog;

public enum MessagesEnumTutorRule {
    TUTOR_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada son nulos o inválidos."),
    TUTOR_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada es 2 o 3."),
    TUTOR_RULE_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    TUTOR_RULE_TUTOR_ALREADY_EXISTS("Tutor existente","Ya existe un tutor con número de identificación %s."),
    TUTOR_RULE_NOT_FOUND("Tutor no encontrado","No existe tutor con id %s."),

    // Mensajes para validación de móvil (TutorMobileDoesNotExistRule)
    TUTOR_MOBILE_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de móvil son nulos o inválidos."),
    TUTOR_MOBILE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de móvil es 2."),
    TUTOR_MOBILE_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    TUTOR_MOBILE_ALREADY_EXISTS("Móvil duplicado","Ya existe un tutor registrado con el móvil %s."),

    // Mensajes para validación de email (TutorEmailDoesNotExistRule)
    TUTOR_EMAIL_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de email son nulos o inválidos."),
    TUTOR_EMAIL_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de email es 2."),
    TUTOR_EMAIL_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    TUTOR_EMAIL_ALREADY_EXISTS("Email duplicado","Ya existe un tutor registrado con el email %s.");

    private final String title;
    private final String content;

    MessagesEnumTutorRule(String title, String content) {
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
