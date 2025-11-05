package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class DoctorEntity {

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

    public DoctorEntity() {
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

    public DoctorEntity(final UUID id) {
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

    public DoctorEntity(final UUID id, final String identityDocument, final String name,
                        final String firstLastName, final String secondLastName,
                        final String email, final String phoneNumber, final String password,
                        final Boolean emailConfirmation, final Boolean phoneConfirmation,
                        final Boolean accountState) {
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

    public static DoctorEntity createDefault() {
        return new DoctorEntity();
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