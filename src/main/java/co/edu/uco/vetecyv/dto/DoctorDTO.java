package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class DoctorDTO {

    private UUID id;
    private String identificationDocument;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String phoneNumber;
    private String password;
    private boolean emailConfirmed;
    private boolean phoneConfirmed;
    private boolean accountState;

    public DoctorDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setIdentificationDocument(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmed(false);
        setPhoneConfirmed(false);
        setAccountState(false);
    }

    public DoctorDTO(final UUID id) {
        setId(id);
        setIdentificationDocument(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhoneNumber(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmed(false);
        setPhoneConfirmed(false);
        setAccountState(false);
    }

    public DoctorDTO(final UUID id, final String identificationDocument, final String name,
                     final String firstLastName, final String secondLastName,
                     final String email, final String phoneNumber, final String password,
                     final boolean emailConfirmed, final boolean phoneConfirmed,
                     final boolean accountState) {
        setId(id);
        setIdentificationDocument(identificationDocument);
        setName(name);
        setFirstLastName(firstLastName);
        setSecondLastName(secondLastName);
        setEmail(email);
        setPhoneNumber(phoneNumber);
        setPassword(password);
        setEmailConfirmed(emailConfirmed);
        setPhoneConfirmed(phoneConfirmed);
        setAccountState(accountState);
    }

    static DoctorDTO getDefaultValue() {
        return new DoctorDTO();
    }

    static DoctorDTO getDefaultValue(final DoctorDTO doctor) {
        return ObjectHelper.getDefault(doctor, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public String getIdentificationDocument() {
        return identificationDocument;
    }

    public void setIdentificationDocument(final String identificationDocument) {
        this.identificationDocument = TextHelper.getDefaultWithTrim(identificationDocument);
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

    public boolean isEmailConfirmed() {
        return emailConfirmed;
    }

    public void setEmailConfirmed(final boolean emailConfirmed) {
        this.emailConfirmed = emailConfirmed;
    }

    public boolean isPhoneConfirmed() {
        return phoneConfirmed;
    }

    public void setPhoneConfirmed(final boolean phoneConfirmed) {
        this.phoneConfirmed = phoneConfirmed;
    }

    public boolean isAccountState() {
        return accountState;
    }

    public void setAccountState(final boolean accountState) {
        this.accountState = accountState;
    }
}