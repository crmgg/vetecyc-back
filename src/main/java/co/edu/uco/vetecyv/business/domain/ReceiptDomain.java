package co.edu.uco.vetecyv.business.domain;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.*;

public final class ReceiptDomain extends Domain {

    private AdministratorDomain administrator;
    private ConsultationDomain consultation;
    private String code;
    private Date dateTime;
    private double totalCoast;

    ReceiptDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAdministrator(AdministratorDomain.getDefaultValue());
        setConsultation(ConsultationDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setDateTime(DateHelper.getDefault());
        setTotalCoast(NumericHelper.getDefaultWithZero());
    }

    public ReceiptDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setAdministrator(AdministratorDomain.getDefaultValue());
        setConsultation(ConsultationDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setDateTime(DateHelper.getDefault());
        setTotalCoast(NumericHelper.getDefaultWithZero());
    }

    public ReceiptDomain(final UUID id, final AdministratorDomain administrator, final ConsultationDomain consultation,
                         final String code, final Date dateTime, final double totalCoast) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setAdministrator(administrator);
        setConsultation(consultation);
        setCode(code);
        setDateTime(dateTime);
        setTotalCoast(totalCoast);
    }

    protected static ReceiptDomain getDefaultValue() {
        return new ReceiptDomain();
    }

    static ReceiptDomain getDefaultValue(final ReceiptDomain receipt) {
        return ObjectHelper.getDefault(receipt, getDefaultValue());
    }

    public AdministratorDomain getAdministrator() {
        return administrator;
    }

    public void setAdministrator(final AdministratorDomain administrator) {
        this.administrator = ObjectHelper.getDefault(administrator, AdministratorDomain.getDefaultValue());
    }

    public ConsultationDomain getConsultation() {
        return consultation;
    }

    public void setConsultation(final ConsultationDomain consultation) {
        this.consultation = ObjectHelper.getDefault(consultation, ConsultationDomain.getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(final Date dateTime) {
        this.dateTime = ObjectHelper.getDefault(dateTime, DateHelper.getDefault());
    }

    public double getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(final double totalCoast) {
        this.totalCoast = totalCoast;
    }
}