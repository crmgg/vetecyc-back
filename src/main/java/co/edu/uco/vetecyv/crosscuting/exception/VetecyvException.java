package co.edu.uco.vetecyv.crosscuting.exception;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public final class VetecyvException extends RuntimeException {

    private static final long serialVersionUID = 512335343454L;

    private final Throwable rootException;
    private final String userMessage;
    private final String technicalMessage;

    // Constructor privado: solo se puede crear a través de los métodos estáticos
    private VetecyvException(final Throwable rootException, final String userMessage, final String technicalMessage) {
        super(userMessage, rootException);
        this.rootException = ObjectHelper.getDefault(rootException, new Exception());
        this.userMessage = TextHelper.getDefaultWithTrim(userMessage);
        this.technicalMessage = TextHelper.getDefaultWithTrim(technicalMessage);
    }

    public static VetecyvException create(final String userMessage) {
        return new VetecyvException(new Exception(), userMessage, userMessage);
    }

    public static VetecyvException create(final String userMessage, final String technicalMessage) {
        return new VetecyvException(new Exception(), userMessage, technicalMessage);
    }

    public static VetecyvException create(final Throwable rootException, final String userMessage, final String technicalMessage) {
        return new VetecyvException(rootException, userMessage, technicalMessage);
    }


    public Throwable getRootException() {
        return rootException;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getTechnicalMessage() {
        return technicalMessage;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }
}
