package co.edu.uco.vetecyv.crosscuting.messagescatalog;

public enum MessagesEnumAdministratorRule {
    ADMINISTRATOR_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada son nulos o inválidos."),
    ADMINISTRATOR_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada es 2 o 3."),
    ADMINISTRATOR_RULE_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    ADMINISTRATOR_RULE_ADMINISTRATOR_ALREADY_EXISTS("Administrador existente","Ya existe un administrador con número de identificación %s."),
    ADMINISTRATOR_RULE_NOT_FOUND("Administrador no encontrado","No existe administrador con id %s."),

    // Mensajes para validación de móvil (si se usan)
    ADMINISTRATOR_MOBILE_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de móvil son nulos o inválidos."),
    ADMINISTRATOR_MOBILE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de móvil es 2."),
    ADMINISTRATOR_MOBILE_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    ADMINISTRATOR_MOBILE_ALREADY_EXISTS("Móvil duplicado","Ya existe un administrador registrado con el móvil %s."),

    // Mensajes para validación de email (si se usan)
    ADMINISTRATOR_EMAIL_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de email son nulos o inválidos."),
    ADMINISTRATOR_EMAIL_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de email es 2."),
    ADMINISTRATOR_EMAIL_INVALID_DATA_TYPES("Datos inválidos","Los tipos de datos enviados a la regla son inválidos."),
    ADMINISTRATOR_EMAIL_ALREADY_EXISTS("Email duplicado","Ya existe un administrador registrado con el email %s.");

    private final String title;
    private final String content;

    MessagesEnumAdministratorRule(String title, String content) {
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

