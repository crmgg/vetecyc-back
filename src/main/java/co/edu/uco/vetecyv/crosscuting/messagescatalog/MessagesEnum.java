package co.edu.uco.vetecyv.crosscuting.messagescatalog;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public enum MessagesEnum {

    USER_ERROR_SQL_CONNECTION_IS_EMPTY("Conexión vacía",
            "La conexión requerida para realizar la operación contra la fuente de información está vacía. "
                    + "Por favor intente nuevamente y si el problema persiste contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_SQL_CONNECTION_IS_EMPTY("Conexión nula",
            "La conexión requerida para realizar la operación contra la base de datos llegó nula. "
                    + "Por favor revise la configuración de la fuente de datos y loguee el incidente."),

    USER_ERROR_SQL_CONNECTION_IS_CLOSED("Conexión cerrada",
            "La conexión requerida para realizar la operación contra la fuente de información está cerrada. "
                    + "Por favor intente nuevamente y si el problema persiste contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_SQL_CONNECTION_IS_CLOSED("Conexión cerrada en base de datos",
            "La conexión requerida para realizar la operación contra la base de datos llegó cerrada. "
                    + "Por favor revise la disponibilidad del servicio de base de datos y la configuración del pool."),

    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error inesperado validando conexión",
            "Se presentó un problema inesperado al validar el estado de la conexión. "
                    + "Por favor intente nuevamente y si el problema persiste contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_CONNECTION_STATUS("Excepción SQL validando conexión",
            "Se produjo una excepción SQL al validar el estado de la conexión contra la base de datos. "
                    + "Revise los registros de la base de datos y la red."),

    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_CONNECTION_STATUS("Error técnico inesperado validando conexión",
            "Se presentó un error técnico inesperado al intentar validar el estado de la conexión contra la base de datos. "
                    + "Por favor intente nuevamente y revise los logs."),

    USER_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción no iniciada",
            "La operación no puede completarse porque la transacción requerida no ha sido iniciada. "
                    + "Por favor inicie la transacción e intente nuevamente."),

    TECHNICAL_ERROR_TRANSACTION_IS_NOT_STARTED("Transacción no iniciada en base de datos",
            "La transacción requerida no fue iniciada correctamente en la base de datos. "
                    + "Por favor revise la lógica de inicio de transacciones y los registros."),

    USER_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error validando inicio de transacción",
            "Se presentó un problema inesperado al validar el inicio de la transacción. "
                    + "Por favor intente nuevamente y si el problema persiste contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_SQL_CONNECTION_SQL_EXCEPTION_VALIDATING_TRANSACTION_IS_STARTED("Excepción SQL validando inicio de transacción",
            "Se produjo una excepción SQL al intentar validar el estado de la transacción. "
                    + "Revise la conexión y los registros del servidor de base de datos."),

    TECHNICAL_ERROR_SQL_CONNECTION_UNEXPECTED_ERROR_VALIDATING_TRANSACTION_IS_STARTED("Error técnico validando inicio de transacción",
            "Se presentó un error técnico inesperado al validar el inicio de la transacción. "
                    + "Por favor revise los registros del sistema."),

    USER_ERROR_SQL_DATASOURCE_NOT_AVAILABLE("Fuente de datos no disponible",
            "La fuente de datos requerida no está disponible. Por favor intente nuevamente y si el problema persiste contacte al administrador de la aplicación."),

    TECHNICAL_ERROR_SQL_DATASOURCE_NOT_AVAILABLE("Fuente de datos no disponible - técnico",
            "La fuente de datos requerida no está disponible. Revise la configuración del DataSource, la conectividad y el servicio de base de datos."),

    USER_ERROR_SQL_CANNOT_INIT_TRANSACTION("No se pudo iniciar la transacción",
            "No fue posible iniciar la transacción para completar la operación. Por favor intente nuevamente."),

    TECHNICAL_ERROR_SQL_CANNOT_INIT_TRANSACTION("No se pudo inicializar la transacción",
            "Ocurrió un error técnico al intentar iniciar la transacción en la base de datos. Revise los registros y la configuración."),

    USER_ERROR_SQL_UNEXPECTED_ERROR_INIT_TRANSACTION("Error inesperado al iniciar transacción",
            "Se presentó un error inesperado al intentar iniciar la transacción. Intente nuevamente y contacte al administrador si persiste."),

    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INIT_TRANSACTION("Error técnico inesperado al iniciar transacción",
            "Se produjo un error técnico inesperado al iniciar la transacción. Revise los registros y el estado del servicio de base de datos."),

    USER_ERROR_SQL_CANNOT_COMMIT_TRANSACTION("No se pudo confirmar la transacción",
            "No fue posible confirmar la transacción. Por favor intente nuevamente y contacte al administrador si el problema persiste."),

    TECHNICAL_ERROR_SQL_CANNOT_COMMIT_TRANSACTION("Error al confirmar la transacción",
            "Ocurrió una excepción técnica al intentar confirmar (commit) la transacción en la base de datos. Revise los logs."),

    USER_ERROR_SQL_UNEXPECTED_ERROR_COMMIT_TRANSACTION("Error inesperado al confirmar transacción",
            "Se presentó un error inesperado al confirmar la transacción. Intente nuevamente y contacte al administrador si persiste."),

    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_COMMIT_TRANSACTION("Error técnico inesperado al confirmar transacción",
            "Se produjo un error técnico inesperado al confirmar la transacción. Revise los registros y la integridad de la base de datos."),

    USER_ERROR_SQL_CANNOT_ROLLBACK_TRANSACTION("No se pudo revertir la transacción",
            "No fue posible revertir la transacción. Por favor intente nuevamente y contacte al administrador si el problema persiste."),

    TECHNICAL_ERROR_SQL_CANNOT_ROLLBACK_TRANSACTION("Error al revertir la transacción",
            "Ocurrió una excepción técnica al intentar revertir (rollback) la transacción en la base de datos. Revise los registros."),

    USER_ERROR_SQL_UNEXPECTED_ERROR_ROLLBACK_TRANSACTION("Error inesperado al revertir transacción",
            "Se presentó un error inesperado al intentar revertir la transacción. Intente nuevamente y reporte el incidente si persiste."),

    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_ROLLBACK_TRANSACTION("Error técnico inesperado al revertir transacción",
            "Se produjo un error técnico inesperado al revertir la transacción. Revise los registros y la consistencia del sistema."),

    USER_ERROR_SQL_CANNOT_CLOSE_CONNECTION("No se pudo cerrar la conexión",
            "No fue posible cerrar la conexión con la fuente de datos. Intente nuevamente y contacte al administrador si persiste."),

    TECHNICAL_ERROR_SQL_CANNOT_CLOSE_CONNECTION("Error técnico al cerrar la conexión",
            "Ocurrió un error técnico al intentar cerrar la conexión con la base de datos. Revise los registros y recursos asociados."),

    USER_ERROR_SQL_UNEXPECTED_ERROR_CLOSE_CONNECTION("Error inesperado al cerrar conexión",
            "Se presentó un error inesperado al cerrar la conexión con la fuente de datos. Intente nuevamente y contacte al administrador si persiste."),

    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_CLOSE_CONNECTION("Error técnico inesperado al cerrar conexión",
            "Se produjo un error técnico inesperado al cerrar la conexión. Revise los registros del sistema y el estado del pool de conexiones.");

    private String title;
    private String content;

    private MessagesEnum(final String title, final String content) {
        setTitle(title);
        setContent(content);
    }

    public String getTitle() {
        return title;
    }

    private void setTitle(final String title) {
        this.title = TextHelper.getDefaultWithTrim(title);
    }

    public String getContent() {
        return content;
    }

    private void setContent(final String content) {
        this.content = TextHelper.getDefaultWithTrim(content);
    }
}