package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public class TutorDTO extends DTO {

    private String identityDocument;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String phoneNumber;
    private String password;
    private Boolean emailConfirmationIsDefaultValue;
    private Boolean emailConfirmation;
    private Boolean phoneConfirmationIsDefaultValue;
    private Boolean phoneConfirmation;
    private Boolean accountStateIsDefaultValue;
    private Boolean accountState;

    public TutorDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setIdentityDocument(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmation(false);
        setEmailConfirmationIsDefaultValue(true);
        setPhoneConfirmation(false);
        setPhoneConfirmationIsDefaultValue(true);
        setAccountState(false);
        setAccountStateIsDefaultValue(true);
    }

    public TutorDTO(final UUID id) {
        super(id);
        setIdentityDocument(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmation(false);
        setEmailConfirmationIsDefaultValue(true);
        setPhoneConfirmation(false);
        setPhoneConfirmationIsDefaultValue(true);
        setAccountState(false);
        setAccountStateIsDefaultValue(true);
    }

    public TutorDTO(final UUID id, final String identityDocument, final String name, final String firstLastName, final String secondLastName,
                    final String email, final String phoneNumber, final String password,
                    final Boolean emailConfirmation, final Boolean phoneConfirmation,
                    final Boolean accountState) {

        super(id);
        setIdentityDocument(identityDocument);
        setName(name);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        if (emailConfirmation != null) {
            setEmailConfirmation(emailConfirmation);
        }
        if (phoneConfirmation != null) {
            setPhoneConfirmation(phoneConfirmation);
        }
        if (accountState != null) {
            setAccountState(accountState);
        }
    }

    public static TutorDTO createDefault() {
        return new TutorDTO(UUIDHelper.getUUIDHelper().getDefault());
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = TextHelper.getDefaultWithTrim(identityDocument);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = TextHelper.getDefaultWithTrim(firstLastName);
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = TextHelper.getDefaultWithTrim(secondLastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = TextHelper.getDefaultWithTrim(email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = TextHelper.getDefaultWithTrim(phoneNumber);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = TextHelper.getDefaultWithTrim(password);
    }

    public Boolean getEmailConfirmationIsDefaultValue() {
        return emailConfirmationIsDefaultValue;
    }

    public void setEmailConfirmationIsDefaultValue(Boolean emailConfirmationIsDefaultValue) {
        this.emailConfirmationIsDefaultValue = emailConfirmationIsDefaultValue;
    }

    public Boolean getEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(final boolean emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
        setEmailConfirmationIsDefaultValue(false);
    }

    public Boolean getPhoneConfirmationIsDefaultValue() {
        return phoneConfirmationIsDefaultValue;
    }

    public void setPhoneConfirmationIsDefaultValue(final boolean phoneConfirmationIsDefaultValue) {
        this.phoneConfirmationIsDefaultValue = phoneConfirmationIsDefaultValue;
    }

    public Boolean getPhoneConfirmation() {
        return phoneConfirmation;
    }

    public void setPhoneConfirmation(final boolean phoneConfirmation) {
        this.phoneConfirmation = phoneConfirmation;
        setPhoneConfirmationIsDefaultValue(false);
    }

    public Boolean getAccountStateIsDefaultValue() {
        return accountStateIsDefaultValue;
    }

    public void setAccountStateIsDefaultValue(Boolean accountStateIsDefaultValue) {
        this.accountStateIsDefaultValue = accountStateIsDefaultValue;
    }

    public Boolean getAccountState() {
        return accountState;
    }

    public void setAccountState(Boolean accountState) {
        this.accountState = accountState;
        setAccountStateIsDefaultValue(false);
    }
}