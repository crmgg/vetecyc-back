package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class ReceiptEntity extends Entity {

    private AdministratorEntity administrator;
    private ConsultationEntity consultation;
    private Integer code;
    private Date date;
    private BigDecimal totalCoast;

    public ReceiptEntity(){
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAdministrator(AdministratorEntity.createDefault());
        setConsultation(ConsultationEntity.createDefault());
        setCode(Integer.valueOf(0));
        setDate(new Date());
        setTotalCoast(BigDecimal.ZERO);
    }

    public ReceiptEntity(final UUID id){
        super(id);
        setAdministrator(AdministratorEntity.createDefault());
        setConsultation(ConsultationEntity.createDefault());
        setCode(Integer.valueOf(0));
        setDate(new Date());
        setTotalCoast(BigDecimal.ZERO);
    }

    public ReceiptEntity(final UUID id, final AdministratorEntity administrator, final ConsultationEntity consultation,
                         final Integer code, final LocalDate date, final BigDecimal totalCoast) {
        super(id);
        setAdministrator(administrator);
        setConsultation(consultation);
        setCode(code);
        setDate(new Date());
        setTotalCoast(totalCoast);
    }

    public AdministratorEntity getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorEntity administrator) {
        this.administrator = administrator;
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public BigDecimal getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(BigDecimal totalCoast) {
        this.totalCoast = totalCoast;
    }

}
