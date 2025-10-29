package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public class DetailDTO extends DTO {

    private ConsultationDTO consultation;
    private MedicalRecordDTO medicalRecord;
    private Integer code;
    private String annotations;

    public DetailDTO() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setConsultation(ConsultationDTO.createDefault());
        setMedicalRecord(MedicalRecordDTO.createDefault());
        setCode(Integer.valueOf(0));
        setAnnotations(TextHelper.getDefault());
    }

    public DetailDTO(final UUID id) {
        super(id);
        setConsultation(ConsultationDTO.createDefault());
        setMedicalRecord(MedicalRecordDTO.createDefault());
        setCode(Integer.valueOf(0));
        setAnnotations(TextHelper.getDefault());
    }

    public DetailDTO(final UUID id, final ConsultationDTO consultation,
                        final MedicalRecordDTO medicalRecord, final Integer code,
                        final String annotations) {
        super(id);
        setConsultation(consultation);
        setMedicalRecord(medicalRecord);
        setCode(code);
        setAnnotations(annotations);
    }

    public ConsultationDTO getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationDTO consultation) {
        this.consultation = consultation == null ? ConsultationDTO.createDefault() : consultation;
    }

    public MedicalRecordDTO getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecordDTO medicalRecord) {
        this.medicalRecord = medicalRecord == null ? MedicalRecordDTO.createDefault() : medicalRecord;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code == null ? Integer.valueOf(0) : code;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = TextHelper.getDefaultWithTrim(annotations);
    }

    public static DetailDTO createDefault() {
        return new DetailDTO();
    }

}