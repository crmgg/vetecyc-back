package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.NumericHelper;
import java.util.Date;
import java.util.UUID;

public final class ReceiptDTO {

    private UUID id;
    private AdministratorDTO administrator;
    private ConsultationDTO consultation;
    private String code;
    private Date dateTime;
    private Double totalCoast;

    public ReceiptDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setAdministrator(AdministratorDTO.getDefaultValue());
        setConsultation(ConsultationDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setDateTime(DateHelper.getDefault());
        setTotalCoast(NumericHelper.getDefaultWithZero());
    }

    public ReceiptDTO(final UUID id) {
        setId(id);
        setAdministrator(AdministratorDTO.getDefaultValue());
        setConsultation(ConsultationDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setDateTime(DateHelper.getDefault());
        setTotalCoast(NumericHelper.getDefaultWithZero());
    }

    public ReceiptDTO(final UUID id, final AdministratorDTO administrator, final ConsultationDTO consultation,
                      final String code, final Date dateTime, final Double totalCoast) {
        setId(id);
        setAdministrator(administrator);
        setConsultation(consultation);
        setCode(code);
        setDateTime(dateTime);
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

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = code;
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