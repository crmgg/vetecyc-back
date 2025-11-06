package co.edu.uco.vetecyv.crosscuting.messagescatalog;

public enum MessagesEnumGeneric {
    STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de formato son nulos o inválidos."),
    STRING_FORMAT_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de formato es 4."),
    STRING_FORMAT_VALUES_IS_VALID_RULE_FORMAT_IS_INVALID("Formato inválido","El valor de %s no cumple con el formato definido (%s)."),

    STRING_VALUES_PRESENT_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de presencia son nulos o inválidos."),
    STRING_VALUES_PRESENT_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de presencia es 3."),
    STRING_VALUES_PRESENT_RULE_DATA_IS_EMPTY("Dato vacío","El valor de %s no puede estar vacío."),

    STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de longitud son nulos o inválidos."),
    STRING_LENGTH_VALUES_IS_VALID_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de longitud es 5."),
    STRING_LENGTH_VALUES_IS_VALID_RULE_LENGTH_IS_INVALID("Longitud inválida","El valor de %s debe tener entre %s y %s caracteres."),

    ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada son nulos o inválidos."),
    ID_VALUE_IS_NOT_DEFAULT_RULE_DATA_LENGHT_INVALID("Datos inválidos","La cantidad de datos esperada es 2."),
    ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_NULL("Dato inválido","El valor de %s no puede ser nulo."),
    ID_VALUE_IS_NOT_DEFAULT_RULE_UUID_IS_DEFAULT("Valor por defecto","El valor de %s no puede ser el valor por defecto."),

    // Mensajes para validación de UUIDs usados por reglas genéricas
    UUID_VALUE_IS_PRESENT_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada para validación de UUID son nulos o inválidos."),
    UUID_VALUE_IS_PRESENT_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada para validación de UUID es 2."),
    UUID_VALUE_IS_PRESENT_RULE_UUID_IS_NULL("Dato inválido","El valor de %s no puede ser nulo."),
    UUID_VALUE_IS_PRESENT_RULE_UUID_IS_DEFAULT("Valor por defecto","El valor de %s no puede ser el valor por defecto."),

    // Entradas genéricas adicionales (no usadas por esta regla pero útiles en el catálogo genérico)
    GENERIC_RULE_DATA_IS_NULL("Datos inválidos","Los datos de entrada son nulos o inválidos."),
    GENERIC_RULE_DATA_LENGTH_INVALID("Datos inválidos","La cantidad de datos esperada es inválida."),

    // Etiquetas genéricas para campos (usar getContent() para obtener la etiqueta)
    GENERIC_ID_NUMBER_LABEL("Etiqueta","número de identificación"),
    GENERIC_FIRST_NAME_LABEL("Etiqueta","primer nombre"),
    GENERIC_SECOND_NAME_LABEL("Etiqueta","segundo nombre"),
    GENERIC_FIRST_SURNAME_LABEL("Etiqueta","primer apellido"),
    GENERIC_SECOND_SURNAME_LABEL("Etiqueta","segundo apellido"),
    GENERIC_EMAIL_LABEL("Etiqueta","email"),
    GENERIC_MOBILE_LABEL("Etiqueta","teléfono movil"),
    GENERIC_ID_TYPE_LABEL("Etiqueta","Tipo de Identificación"),
    GENERIC_HOME_CITY_LABEL("Etiqueta","Ciudad de Residencia");

    private final String title;
    private final String content;

    MessagesEnumGeneric(String title, String content) {
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
