package co.edu.uco.vetecyv.crosscuting.messagescatalog;

public enum MessagesEnumTutorRule {
    TUTOR_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada son nulos o inválidos."),
    TUTOR_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada es 2 o 3."),
    TUTOR_RULE_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    TUTOR_RULE_TUTOR_ALREADY_EXISTS("Tutor existente","Ya existe un tutor con número de identificación."),
    TUTOR_RULE_NOT_FOUND("Tutor no encontrado","No existe tutor con ese id."),

    // Mensajes para validación de móvil (TutorMobileDoesNotExistRule)
    TUTOR_MOBILE_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de móvil son nulos o inválidos."),
    TUTOR_MOBILE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de móvil es 2."),
    TUTOR_MOBILE_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    TUTOR_MOBILE_ALREADY_EXISTS("Móvil duplicado","Ya existe un tutor registrado con el móvil."),

    // Mensajes para validación de email (TutorEmailDoesNotExistRule)
    TUTOR_EMAIL_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de email son nulos o inválidos."),
    TUTOR_EMAIL_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de email es mayor a 1 y con su respectivo @ seguido con la dependencia."),
    TUTOR_EMAIL_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    TUTOR_EMAIL_ALREADY_EXISTS("Email duplicado","Ya existe un tutor registrado con el email."),

    TUTOR_ERROR_TRYING_TO_MAKE_AN_OPERATION("Error de operación","Se ha presentado un error tratando de realizar la operación sobre el tutor."),
    TECHNICAL_ERROR_WRONG_TUTOR_LENGTH_VALUE("Error técnico","Se ha presentado un error técnico por un valor de longitud inválido para el tutor."),
    TUTOR_ERROR_TUTOR_DOES_NOT_EXITS("Tutor no existente","El tutor que se intenta actualizar no existe."),
    TECHNICAL_ERROR_TUTOR_DOES_NOT_EXITS("Error técnico","Se ha presentado un error técnico porque el tutor que se intenta actualizar no existe."),

    TUTOR_ERROR_TUTOR_DOES_EXITS("Tutor existente","El tutor que se intenta registrar ya existe."),
    TECHNICAL_ERROR_TUTOR_DOES_EXISTS_BY_EMAIL("Error técnico","Se ha presentado un error técnico porque el tutor que se intenta registrar ya existe con el email proporcionado.");

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
