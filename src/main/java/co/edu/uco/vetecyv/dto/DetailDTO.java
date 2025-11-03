package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public final class DetailDTO {

    private UUID id;
    private ConsultationDTO consultation;
    private MedicalRecordDTO medicalRecord;
    private Integer code;
    private String annotations;

    public DetailDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setConsultation(ConsultationDTO.getDefaultValue());
        setMedicalRecord(MedicalRecordDTO.getDefaultValue());
        setCode(Integer.valueOf(0));
        setAnnotations(TextHelper.getDefault());
    }

    public DetailDTO(final UUID id) {
        setId(id);
        setConsultation(ConsultationDTO.getDefaultValue());
        setMedicalRecord(MedicalRecordDTO.getDefaultValue());
        setCode(Integer.valueOf(0));
        setAnnotations(TextHelper.getDefault());
    }

    public DetailDTO(final UUID id, final ConsultationDTO consultation,
                     final MedicalRecordDTO medicalRecord, final Integer code,
                     final String annotations) {
        setId(id);
        setConsultation(consultation);
        setMedicalRecord(medicalRecord);
        setCode(code);
        setAnnotations(annotations);
    }


    static DetailDTO getDefaultValue() {
        return new DetailDTO();
    }

    static DetailDTO getDefaultValue(final DetailDTO detail) {
        return ObjectHelper.getDefault(detail, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public ConsultationDTO getConsultation() {
        return consultation;
    }

    public void setConsultation(final ConsultationDTO consultation) {
        this.consultation = ObjectHelper.getDefault(consultation, ConsultationDTO.getDefaultValue());
    }

    public MedicalRecordDTO getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(final MedicalRecordDTO medicalRecord) {
        this.medicalRecord = ObjectHelper.getDefault(medicalRecord, MedicalRecordDTO.getDefaultValue());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = ObjectHelper.getDefault(code, Integer.valueOf(0));
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(final String annotations) {
        this.annotations = TextHelper.getDefaultWithTrim(annotations);
    }
}