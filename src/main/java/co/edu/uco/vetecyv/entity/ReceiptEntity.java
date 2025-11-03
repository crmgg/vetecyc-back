package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public final class ReceiptEntity {

    private UUID id;
    private AdministratorEntity administrator;
    private ConsultationEntity consultation;
    private Integer code;
    private Date date;
    private BigDecimal totalCoast;

    public ReceiptEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAdministrator(AdministratorEntity.createDefault());
        setConsultation(ConsultationEntity.createDefault());
        setCode(Integer.valueOf(0));
        setDate(new Date());
        setTotalCoast(BigDecimal.ZERO);
    }

    public ReceiptEntity(final UUID id) {
        setId(id);
        setAdministrator(AdministratorEntity.createDefault());
        setConsultation(ConsultationEntity.createDefault());
        setCode(Integer.valueOf(0));
        setDate(new Date());
        setTotalCoast(BigDecimal.ZERO);
    }

    public ReceiptEntity(final UUID id, final AdministratorEntity administrator, final ConsultationEntity consultation,
                         final Integer code, final Date date, final BigDecimal totalCoast) {
        setId(id);
        setAdministrator(administrator);
        setConsultation(consultation);
        setCode(code);
        setDate(date);
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

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code == null ? Integer.valueOf(0) : code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = date == null ? new Date() : date;
    }

    public BigDecimal getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(final BigDecimal totalCoast) {
        this.totalCoast = totalCoast == null ? BigDecimal.ZERO : totalCoast;
    }
}