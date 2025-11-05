package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.NumericHelper;

import java.util.Date;
import java.util.UUID;

public final class ReceiptEntity {

    private UUID id;
    private AdministratorEntity administrator;
    private ConsultationEntity consultation;
    private String code;
    private Date dateTime;
    private Double totalCoast;

    public ReceiptEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAdministrator(AdministratorEntity.createDefault());
        setConsultation(ConsultationEntity.createDefault());
        setCode(TextHelper.getDefault());
        setDateTime(DateHelper.getDefault());
        setTotalCoast(NumericHelper.getDefaultWithZero());
    }

    public ReceiptEntity(final UUID id) {
        setId(id);
        setAdministrator(AdministratorEntity.createDefault());
        setConsultation(ConsultationEntity.createDefault());
        setCode(TextHelper.getDefault());
        setDateTime(DateHelper.getDefault());
        setTotalCoast(NumericHelper.getDefaultWithZero());
    }

    public ReceiptEntity(final UUID id, final AdministratorEntity administrator, final ConsultationEntity consultation,
                         final String code, final Date dateTime, final Double totalCoast) {
        setId(id);
        setAdministrator(administrator);
        setConsultation(consultation);
        setCode(code);
        setDateTime(dateTime);
        setTotalCoast(totalCoast);
    }

    public static ReceiptEntity createDefault() {
        return new ReceiptEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public AdministratorEntity getAdministrator() {
        return administrator;
    }

    public void setAdministrator(final AdministratorEntity administrator) {
        this.administrator = administrator == null ? AdministratorEntity.createDefault() : administrator;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(final ConsultationEntity consultation) {
        this.consultation = consultation == null ? ConsultationEntity.createDefault() : consultation;
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
        this.dateTime = DateHelper.getDefault(dateTime);
    }

    public Double getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(final Double totalCoast) {
        this.totalCoast = NumericHelper.getDefaultWithZero(totalCoast);
    }
}