package co.edu.uco.vetecyv.buisness.domain;

import java.util.UUID;

import  co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import  co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;


public class AdministratorDomain extends Domain{

    private final String IdentityDocument;
    private String identityDocument;
    private String name;
    private String firstLastName;
    private String secondLastName;
    private String email;
    private String phone;
    private String userName;
    private String password;
    private Boolean emailConfirmation;
    private Boolean phoneConfirmation;
    private Boolean accountState;

    public AdministratorDomain(String identityDocument) {
        super(UUIDHelper.getUUIDHelper().getDefault());
        IdentityDocument = identityDocument;
        setIdentityDocument(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhone(TextHelper.getDefault());
        setUserName(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmation(false);
        setPhoneConfirmation(false);
        setAccountState(false);
    }

    public AdministratorDomain(final UUID id){
        super(id);
        IdentityDocument = identityDocument;
        setIdentityDocument(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhone(TextHelper.getDefault());
        setUserName(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmation(false);
        setPhoneConfirmation(false);
        setAccountState(false);
    }

    public AdministratorDomain(final UUID id, final String IdentityDocument){
        super(id);
        this.IdentityDocument = TextHelper.getDefault(IdentityDocument);
    }

    public String getIdentityDocument() {
        return identityDocument;
    }

    public void setIdentityDocument(String identityDocument) {
        this.identityDocument = TextHelper.getDefault(identityDocument);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = TextHelper.getDefault(name);
    }

    public String getFirstLastName() {
        return firstLastName;
    }

    public void setFirstLastName(String firstLastName) {
        this.firstLastName = TextHelper.getDefault(firstLastName);
    }

    public String getSecondLastName() {
        return secondLastName;
    }

    public void setSecondLastName(String secondLastName) {
        this.secondLastName = TextHelper.getDefault(secondLastName);
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = TextHelper.getDefault(email);
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = TextHelper.getDefault(phone);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = TextHelper.getDefault(userName);
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = TextHelper.getDefault(password);
    }

    public Boolean getEmailConfirmation() {
        return emailConfirmation;
    }

    public void setEmailConfirmation(final boolean emailConfirmation) {
        this.emailConfirmation = emailConfirmation;
    }

    public Boolean getPhoneConfirmation() {
        return phoneConfirmation;
    }

    public void setPhoneConfirmation(final boolean phoneConfirmation) {
        this.phoneConfirmation = phoneConfirmation;
    }

    public Boolean getAccountState() {
        return accountState;
    }

    public void setAccountState(final boolean accountState) {
        this.accountState = accountState;
    }
}
