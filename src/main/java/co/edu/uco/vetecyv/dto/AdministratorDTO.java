package co.edu.uco.vetecyv.dto;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

public final class AdministratorDTO {

    private UUID id;
    private String identityDocument;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean emailConfirmation;
    private boolean phoneConfirmation;
    private boolean accountState;

    public AdministratorDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setIdentityDocument(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmation(false);
        setPhoneConfirmation(false);
        setAccountState(false);
    }

    public AdministratorDTO(final UUID id) {
        setId(id);
        setIdentityDocument(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmation(false);
        setPhoneConfirmation(false);
        setAccountState(false);
    }

    public AdministratorDTO(final UUID id, final String identityDocument, final String name,
                            final String firstLastName, final String secondLastName,
                            final String email, final String phoneNumber, final String password,
                            final boolean emailConfirmation, final boolean phoneConfirmation,
                            final boolean accountState) {
        setId(id);
        setIdentityDocument(identityDocument);
        setName(name);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        setEmailConfirmation(emailConfirmation);
        setPhoneConfirmation(phoneConfirmation);
        setAccountState(accountState);
    }

    static AdministratorDTO getDefaultValue() {
        return new AdministratorDTO();
    }

    static AdministratorDTO getDefaultValue(final AdministratorDTO administrator){
        return ObjectHelper.getDefault(administrator, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(final String identityDocument) {
        this.identityDocument = TextHelper.getDefaultWithTrim(identityDocument);
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(final String firstLastName) {
        this.firstLastName = TextHelper.getDefaultWithTrim(firstLastName);
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(final String secondLastName) {
        this.secondLastName = TextHelper.getDefaultWithTrim(secondLastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = TextHelper.getDefaultWithTrim(email);
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(final String phoneNumber) {
        this.phoneNumber = TextHelper.getDefaultWithTrim(phoneNumber);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(final String password) {
        this.password = TextHelper.getDefaultWithTrim(password);
    }

    public boolean isEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(final boolean emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
    }

    public boolean isPhoneConfirmation() {
        return phoneConfirmation;
    }

    public void setPhoneConfirmation(final boolean phoneConfirmation) {
        this.phoneConfirmation = phoneConfirmation;
    }

    public boolean isAccountState() {
        return accountState;
    }

    public void setAccountState(final boolean accountState) {
        this.accountState = accountState;
    }
}