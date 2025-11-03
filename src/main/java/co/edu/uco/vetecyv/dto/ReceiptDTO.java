package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.ConsultationDTO;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

public final class ReceiptDTO {

    private UUID id;
    private AdministratorDTO administrator;
    private ConsultationDTO consultation;
    private Integer code;
    private Date date;
    private BigDecimal totalCoast;

    public ReceiptDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAdministrator(AdministratorDTO.getDefaultValue());
        setConsultation(ConsultationDTO.getDefaultValue());
        setCode(Integer.valueOf(0));
        setDate(DateHelper.getDefault());
        setTotalCoast(BigDecimal.ZERO);
    }

    public ReceiptDTO(final UUID id) {
        setId(id);
        setAdministrator(AdministratorDTO.getDefaultValue());
        setConsultation(ConsultationDTO.getDefaultValue());
        setCode(Integer.valueOf(0));
        setDate(DateHelper.getDefault());
        setTotalCoast(BigDecimal.ZERO);
    }

    public ReceiptDTO(final UUID id, final AdministratorDTO administrator, final ConsultationDTO consultation,
                      final Integer code, final Date date, final BigDecimal totalCoast) {
        setId(id);
        setAdministrator(administrator);
        setConsultation(consultation);
        setCode(code);
        setDate(date);
        setTotalCoast(totalCoast);
    }

    static ReceiptDTO getDefaultValue() {
        return new ReceiptDTO();
    }

    static ReceiptDTO getDefaultValue(final ReceiptDTO receipt) {
        return ObjectHelper.getDefault(receipt, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(final AdministratorDTO administrator) {
        this.administrator = ObjectHelper.getDefault(administrator, AdministratorDTO.getDefaultValue());
    }

    public ConsultationDTO getConsultation() {
        return consultation;
    }

    public void setConsultation(final ConsultationDTO consultation) {
        this.consultation = ObjectHelper.getDefault(consultation, ConsultationDTO.getDefaultValue());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = ObjectHelper.getDefault(code, Integer.valueOf(0));
    }

    public Date getDate() {
        return date;
    }

    public void setDate(final Date date) {
        this.date = DateHelper.getDefault(date);
    }

    public BigDecimal getTotalCoast() {
        return totalCoast;
    }

    public void setTotalCoast(final BigDecimal totalCoast) {
        this.totalCoast = ObjectHelper.getDefault(totalCoast, BigDecimal.ZERO);
    }
}