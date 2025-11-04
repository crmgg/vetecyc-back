package co.edu.uco.vetecyv.crosscuting.messagescatalog;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public enum MessagesEnum {

    /* Conexión y transacción */
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
            "Se produjo una excepción SQL al intentar validar el inicio de la transacción. "
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
            "Se produjo un error técnico inesperado al intentar iniciar la transacción. Revise los registros y el estado del servicio de base de datos."),

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
            "Se produjo un error técnico inesperado al cerrar la conexión. Revise los registros del sistema y el estado del pool de conexiones."),

    /* Mensajes para Administrador DAO */
    ADMINISTRATOR_ERROR_SQL_INSERT_ADMINISTRATOR("Error registrando administrador",
            "No fue posible registrar el administrador. Por favor intente nuevamente. Si el problema persiste contacte al administrador del sistema."),
    TECHNICAL_ERROR_SQL_INSERT_ADMINISTRATOR("Error técnico registrando administrador",
            "Se produjo una excepción técnica al intentar insertar el administrador en la base de datos. Revise los registros y la integridad de la base de datos."),
    ADMINISTRATOR_ERROR_SQL_UNEXPECTED_ERROR_INSERT_ADMINISTRATOR("Error inesperado registrando administrador",
            "Se presentó un error inesperado al registrar el administrador. Intente nuevamente y reporte el incidente si persiste."),
    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_ADMINISTRATOR("Error técnico inesperado registrando administrador",
            "Se produjo un error técnico inesperado al registrar el administrador. Revise los registros del sistema."),
    ADMINISTRATOR_ERROR_SQL_UPDATE_ADMINISTRATOR("Error actualizando administrador",
            "No fue posible actualizar la información del administrador. Por favor intente nuevamente."),
    TECHNICAL_ERROR_SQL_UPDATE_ADMINISTRATOR("Error técnico actualizando administrador",
            "Se produjo una excepción técnica al intentar actualizar el administrador en la base de datos. Revise los registros."),
    ADMINISTRATOR_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_ADMINISTRATOR("Error inesperado actualizando administrador",
            "Se presentó un error inesperado al actualizar el administrador. Intente nuevamente y contacte al administrador si persiste."),
    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_ADMINISTRATOR("Error técnico inesperado actualizando administrador",
            "Se produjo un error técnico inesperado al actualizar el administrador. Revise los registros del sistema."),
    ADMINISTRATOR_ERROR_SQL_DELETE_ADMINISTRATOR("Error eliminando administrador",
            "No fue posible eliminar el administrador. Por favor intente nuevamente."),
    TECHNICAL_ERROR_SQL_DELETE_ADMINISTRATOR("Error técnico eliminando administrador",
            "Se produjo una excepción técnica al intentar eliminar el administrador en la base de datos. Revise los registros."),
    ADMINISTRATOR_ERROR_SQL_UNEXPECTED_ERROR_DELETE_ADMINISTRATOR("Error inesperado eliminando administrador",
            "Se presentó un error inesperado al eliminar el administrador. Intente nuevamente y reporte el incidente si persiste."),
    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_ADMINISTRATOR("Error técnico inesperado eliminando administrador",
            "Se produjo un error técnico inesperado al eliminar el administrador. Revise los registros del sistema."),
    ADMINISTRATOR_ERROR_SQL_EXECUTING_FIND_BY_FILTER_ADMINISTRATOR("Error ejecutando búsqueda de administradores",
            "Se produjo un error al ejecutar la consulta para buscar administradores. Revise la consulta y los parámetros."),
    TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_ADMINISTRATOR("Error técnico ejecutando búsqueda de administradores",
            "Se produjo una excepción técnica al ejecutar la sentencia SQL de búsqueda de administradores. Revise los logs y la base de datos."),
    ADMINISTRATOR_ERROR_SQL_MAPPING_ADMINISTRATOR("Error mapeando administrador",
            "Se produjo un error al mapear los resultados de la consulta a la entidad Administrador. Revise los registros."),
    TECHNICAL_ERROR_SQL_MAPPING_ADMINISTRATOR("Error técnico mapeando administrador",
            "Se produjo una excepción técnica durante el mapeo del ResultSet a la entidad Administrador. Revise los registros y la definición de columnas en la consulta."),
    ADMINISTRATOR_ERROR_SQL_UNEXPECTED_MAPPING_ADMINISTRATOR("Error inesperado mapeando administrador",
            "Se presentó un error inesperado al mapear los datos del administrador. Intente nuevamente y contacte al administrador si persiste."),
    TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_ADMINISTRATOR("Error técnico inesperado mapeando administrador",
            "Se produjo un error técnico inesperado al mapear los datos del administrador. Revise los registros del sistema."),

    USER_ERROR_SQL_EXCEPTION_REGISTERING_USER("Error registrando usuario",
            "No fue posible registrar el usuario. Intente nuevamente."),
    TECHNICAL_ERROR_SQL_EXCEPTION_REGISTERING_USER("Error técnico registrando usuario",
            "Se produjo una excepción técnica al intentar insertar el usuario en la base de datos. Revise los registros."),
    USER_ERROR_UNEXPECTED_EXCEPTION_REGISTERING_USER("Error inesperado registrando usuario",
            "Se presentó un error inesperado al registrar el usuario. Intente nuevamente y contacte soporte si persiste."),

    USER_ERROR_SQL_EXCEPTION_FINDING_USER_WHILE_EXECUTION("Error ejecutando búsqueda de usuario",
            "Se produjo un error al ejecutar la consulta para buscar usuarios. Revise la consulta y los parámetros."),
    TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_USER_WHILE_PREPARATION("Error técnico preparando búsqueda de usuario",
            "Se produjo una excepción técnica al preparar la sentencia SQL de búsqueda de usuarios. Revise los logs."),
    TECHNICAL_ERROR_SQL_EXCEPTION_FINDING_USER_WHILE_EXECUTION("Error técnico ejecutando búsqueda de usuario",
            "Se produjo una excepción técnica al ejecutar la sentencia SQL de búsqueda de usuarios. Revise los logs y la base de datos."),
    USER_ERROR_UNEXPECTED_EXCEPTION_FINDING_USER_WHILE_EXECUTION("Error inesperado ejecutando búsqueda de usuario",
            "Se presentó un error inesperado al ejecutar la búsqueda de usuarios. Intente nuevamente."),
    TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_USER_WHILE_PREPARATION("Error técnico inesperado preparando búsqueda de usuario",
            "Se produjo un error técnico inesperado al preparar la consulta de búsqueda de usuarios. Revise los registros."),
    TECHNICAL_ERROR_UNEXPECTED_EXCEPTION_FINDING_USER_WHILE_EXECUTION("Error técnico inesperado ejecutando búsqueda de usuario",
            "Se produjo un error técnico inesperado al ejecutar la consulta de búsqueda de usuarios. Revise los registros."),

    USER_ERROR_SQL_MAPPING_USER("Error mapeando usuario",
            "Se produjo un error al mapear los resultados de la consulta a la entidad Usuario."),
    TECHNICAL_ERROR_SQL_MAPPING_USER("Error técnico mapeando usuario",
            "Se produjo una excepción técnica durante el mapeo del ResultSet a la entidad Usuario. Revise la definición de columnas y los registros."),
    USER_ERROR_SQL_UNEXPECTED_MAPPING_USER("Error inesperado mapeando usuario",
            "Se presentó un error inesperado al mapear los datos del usuario. Intente nuevamente."),

    USER_ERROR_SQL_DELETE_USER("Error eliminando usuario",
            "No fue posible eliminar el usuario. Intente nuevamente."),
    TECHNICAL_ERROR_SQL_DELETE_USER("Error técnico eliminando usuario",
            "Se produjo una excepción técnica al intentar eliminar el usuario en la base de datos. Revise los registros."),
    USER_ERROR_SQL_UPDATE_USER("Error actualizando usuario",
            "No fue posible actualizar la información del usuario. Intente nuevamente."),
    TECHNICAL_ERROR_SQL_UPDATE_USER("Error técnico actualizando usuario",
            "Se produjo una excepción técnica al intentar actualizar el usuario en la base de datos. Revise los registros."),


    AGENDA_ERROR_SQL_INSERT_AGENDA("Error SQL al insertar agenda",
                                           "Technical error SQL insert agenda: {0}"),
    TECHNICAL_ERROR_SQL_INSERT_AGENDA("Error técnico al insertar agenda",
                                           "Technical error SQL insert agenda: {0}"),

    AGENDA_ERROR_SQL_UNEXPECTED_ERROR_INSERT_AGENDA("Error inesperado al insertar agenda",
                                                            "Technical unexpected error insert agenda: {0}"),
    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_INSERT_AGENDA("Error técnico inesperado al insertar agenda",
                                                            "Technical unexpected error insert agenda: {0}"),

    AGENDA_ERROR_SQL_UPDATE_AGENDA("Error SQL al actualizar agenda",
                                           "Technical error SQL update agenda: {0}"),
    TECHNICAL_ERROR_SQL_UPDATE_AGENDA("Error técnico al actualizar agenda",
                                           "Technical error SQL update agenda: {0}"),

    AGENDA_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_AGENDA("Error inesperado al actualizar agenda",
                                                            "Technical unexpected error update agenda: {0}"),
    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_UPDATE_AGENDA("Error técnico inesperado al actualizar agenda",
                                                            "Technical unexpected error update agenda: {0}"),

    AGENDA_ERROR_SQL_DELETE_AGENDA("Error SQL al eliminar agenda",
                                           "Technical error SQL delete agenda: {0}"),
   TECHNICAL_ERROR_SQL_DELETE_AGENDA("Error técnico al eliminar agenda",
                                           "Technical error SQL delete agenda: {0}"),

    AGENDA_ERROR_SQL_UNEXPECTED_ERROR_DELETE_AGENDA("Error inesperado al eliminar agenda",
                                                            "Technical unexpected error delete agenda: {0}"),
    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_AGENDA("Error técnico inesperado al eliminar agenda",
                                                            "Technical unexpected error delete agenda: {0}"),

    AGENDA_ERROR_SQL_EXECUTING_FIND_BY_FILTER_AGENDA("Error SQL ejecutando findByFilter agenda",
                                                             "Technical error SQL executing findByFilter agenda: {0}"),
    TECHNICAL_ERROR_SQL_EXECUTING_FIND_BY_FILTER_AGENDA("Error técnico ejecutando findByFilter agenda",
                                                             "Technical error SQL executing findByFilter agenda: {0}"),

    AGENDA_ERROR_SQL_MAPPING_AGENDA("Error SQL mapeando agenda",
                                            "Technical error SQL mapping agenda: {0}"),
    TECHNICAL_ERROR_SQL_MAPPING_AGENDA("Error técnico mapeando agenda",
                                            "Technical error SQL mapping agenda: {0}"),

    AGENDA_ERROR_SQL_UNEXPECTED_MAPPING_AGENDA("Error inesperado mapeando agenda",
                                                       "Technical unexpected error mapping agenda: {0}"),
    TECHNICAL_ERROR_SQL_UNEXPECTED_MAPPING_AGENDA("Error técnico inesperado mapeando agenda",
                                                       "Technical unexpected error mapping agenda: {0}"),

    AGENDA_ERROR_SQL_DELETE_AGENDA_BY_ENTITY("Error SQL al eliminar agenda (por entidad)",
                                                     "Technical error SQL delete agenda (by entity): {0}"),
    TECHNICAL_ERROR_SQL_DELETE_AGENDA_BY_ENTITY("Error técnico al eliminar agenda (por entidad)",
                                                     "Technical error SQL delete agenda (by entity): {0}"),

    AGENDA_ERROR_SQL_UNEXPECTED_ERROR_DELETE_AGENDA_BY_ENTITY("Error inesperado al eliminar agenda (por entidad)",
                                                                      "Technical unexpected error delete agenda (by entity): {0}"),

    TECHNICAL_ERROR_SQL_UNEXPECTED_ERROR_DELETE_AGENDA_BY_ENTITY("Error técnico inesperado al eliminar agenda (por entidad)",
                                                                      "Technical unexpected error delete agenda (by entity): {0}"),


























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