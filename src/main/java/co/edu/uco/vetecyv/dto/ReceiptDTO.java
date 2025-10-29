package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.dto.AdministratorDTO;
import co.edu.uco.vetecyv.dto.ConsultationDTO;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

public class ReceiptDTO extends DTO {

    private AdministratorDTO administrator;
    private ConsultationDTO consultation;
    private Integer code;
    private Date date;
    private BigDecimal totalCoast;

    public ReceiptDTO(){
        super(UUIDHelper.getUUIDHelper().getDefault());
        setAdministrator(AdministratorDTO.createDefault());
        setConsultation(ConsultationDTO.createDefault());
        setCode(Integer.valueOf(0));
        setDate(new Date());
        setTotalCoast(BigDecimal.ZERO);
    }

    public ReceiptDTO(final UUID id){
        super(id);
        setAdministrator(AdministratorDTO.createDefault());
        setConsultation(ConsultationDTO.createDefault());
        setCode(Integer.valueOf(0));
        setDate(new Date());
        setTotalCoast(BigDecimal.ZERO);
    }

    public ReceiptDTO(final UUID id, final AdministratorDTO administrator, final ConsultationDTO consultation,
                         final Integer code, final LocalDate date, final BigDecimal totalCoast) {
        super(id);
        setAdministrator(administrator);
        setConsultation(consultation);
        setCode(code);
        setDate(new Date());
        setTotalCoast(totalCoast);
    }

    public AdministratorDTO getAdministrator() {
        return administrator;
    }

    public void setAdministrator(AdministratorDTO administrator) {
        this.administrator = administrator;
    }

    public ConsultationDTO getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationDTO consultation) {
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

