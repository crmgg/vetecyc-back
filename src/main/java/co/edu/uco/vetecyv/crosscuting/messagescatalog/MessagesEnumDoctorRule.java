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
    DOCTOR_EMAIL_ALREADY_EXISTS("Email duplicado","Ya existe un doctor registrado con el email %s."),

    // Mensajes para uso en DoctorBusinessImpl / validadores
    DOCTOR_ERROR_NOT_FOUND("Doctor no encontrado.",
            "No existe doctor con id proporcionado."),
    DOCTOR_ERROR_INVALID("Doctor inválido.",
            "El objeto DoctorDomain es nulo."),
    DOCTOR_ERROR_INVALID_NAME("Nombre inválido.",
            "Nombre del doctor nulo o demasiado largo."),
    DOCTOR_ERROR_INVALID_FIRSTLASTNAME("Primer apellido inválido.",
            "Primer apellido nulo o demasiado largo."),
    DOCTOR_ERROR_INVALID_SECONDLASTNAME("Segundo apellido inválido.",
            "Segundo apellido nulo o demasiado largo."),
    DOCTOR_ERROR_INVALID_EMAIL("Email inválido.",
            "Email nulo o con formato incorrecto."),
    DOCTOR_ERROR_INVALID_PASSWORD("Password inválido.",
            "Password nulo o demasiado largo."),
    DOCTOR_ERROR_INVALID_PHONE("Teléfono inválido.",
            "Número de teléfono nulo o con formato incorrecto."),
    DOCTOR_ERROR_DUPLICATED_EMAIL("Email duplicado.",
            "Ya existe un doctor con ese email."),
    DOCTOR_ERROR_DUPLICATED_PHONE("Teléfono duplicado.",
            "Ya existe un doctor con ese número de teléfono."),
    DOCTOR_ERROR_DUPLICATED_EMAIL_ON_UPDATE("Email duplicado en actualización.",
            "Otro doctor ya usa ese email."),
    DOCTOR_ERROR_DUPLICATED_PHONE_ON_UPDATE("Teléfono duplicado en actualización.",
            "Otro doctor ya usa ese teléfono."),
    DOCTOR_ERROR_INVALID_IDENTITYDOCUMENT_LENGTH("Documento de identidad inválido.",
            "El documento de identidad debe tener entre 8 y 10 caracteres."),

    // Mensaje genérico para formato inválido de campos (sin mensajes hardcodeados en reglas)
    DOCTOR_ERROR_INVALID_FIELD_FORMAT("Formato inválido.",
            "El campo %s no puede contener espacios y debe iniciar con mayúscula."),

    // Mensajes específicos para formato de nombre/apellidos/password
    DOCTOR_ERROR_INVALID_NAME_FORMAT("Nombre inválido.",
            "El nombre '%s' contiene espacios o no inicia con mayúscula; no se puede registrar."),
    DOCTOR_ERROR_INVALID_FIRSTLASTNAME_FORMAT("Primer apellido inválido.",
            "El primer apellido '%s' contiene espacios o no inicia con mayúscula; no se puede registrar."),
    DOCTOR_ERROR_INVALID_SECONDLASTNAME_FORMAT("Segundo apellido inválido.",
            "El segundo apellido '%s' contiene espacios o no inicia con mayúscula; no se puede registrar."),
    DOCTOR_ERROR_INVALID_PASSWORD_FORMAT("Password inválido.",
            "El password no puede contener espacios y debe iniciar con mayúscula.");


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
