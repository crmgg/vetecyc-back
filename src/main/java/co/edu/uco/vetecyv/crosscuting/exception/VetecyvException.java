package co.edu.uco.vetecyv.crosscuting.exception;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public final class VetecyvException extends RuntimeException {

    private static final long serialVersionUID= 512335343454L;
    private Throwable rootException;
    private static String userMessage;
    private String technicalMessage;

    private VetecyvException(final Throwable rootException, final String suerMessage, String userMessage2) {
        setRootException(rootException);
        setUserMessage(suerMessage);
        setTechnicalMessage(suerMessage);

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

    private Throwable getRootException() {
        return rootException;
    }
    private void setRootException(Throwable rootException) {
        this.rootException = ObjectHelper.getDefault(rootException, new Exception());
    }
    public String getUserMessage() {
        return userMessage;
    }
    private void setUserMessage(final String userMessage) {
        this.userMessage = TextHelper.getDefaultWithTrim(userMessage);
    }
    public String getTechnicalMessage() {
        return technicalMessage;
    }
    private void setTechnicalMessage(final String technicalMessage) {
        this.technicalMessage = TextHelper.getDefaultWithTrim(technicalMessage);
    }
    public static long getSerialversionuid() {
        return serialVersionUID;
    }



}