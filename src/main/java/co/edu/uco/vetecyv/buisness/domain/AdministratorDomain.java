package co.edu.uco.vetecyv.buisness.domain;

import java.util.UUID;

import  co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import  co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import static org.apache.tomcat.util.net.openssl.OpenSSLStatus.setName;

public class AdministratorDomain extends Domain{

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

    public AdministratorDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setIdentityDocument(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhone(TextHelper.getDefault());
        setUserName(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmation(TextHelper.getDefault());
        setPhoneConfirmation(TextHelper.getDefault());
        setAccountState(TextHelper.getDefault());
    }

    public AdministratorDomain(final UUID id){
        super(id);
        setIdentityDocument(TextHelper.getDefault());
        setFirstLastName(TextHelper.getDefault());
        setSecondLastName(TextHelper.getDefault());
        setEmail(TextHelper.getDefault());
        setPhone(TextHelper.getDefault());
        setUserName(TextHelper.getDefault());
        setPassword(TextHelper.getDefault());
        setEmailConfirmation(TextHelper.getDefault());
        setPhoneConfirmation(TextHelper.getDefault());
        setAccountState(TextHelper.getDefault());
    }

    public String getIdentityDocument(){return identityDocument; }
    public void setIdentityDocument

}
