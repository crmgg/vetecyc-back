package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Optional;
import java.util.UUID;

public final class DetailEntity {

    private UUID id;
    private ConsultationEntity consultation;
    private MedicalRecordEntity medicalRecord;
    private Integer code;
    private String annotations;

    public DetailEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setConsultation(ConsultationEntity.createDefault());
        setMedicalRecord(MedicalRecordEntity.createDefault());
        setCode(Integer.valueOf(0));
        setAnnotations(TextHelper.getDefault());
    }

    public DetailEntity(final UUID id) {
        setId(id);
        setConsultation(ConsultationEntity.createDefault());
        setMedicalRecord(MedicalRecordEntity.createDefault());
        setCode(Integer.valueOf(0));
        setAnnotations(TextHelper.getDefault());
    }

    public DetailEntity(final UUID id, final ConsultationEntity consultation,
                        final MedicalRecordEntity medicalRecord, final Integer code,
                        final String annotations) {
        setId(id);
        setConsultation(consultation);
        setMedicalRecord(medicalRecord);
        setCode(code);
        setAnnotations(annotations);
    }

    public static DetailEntity createDefault() {
        return new DetailEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(final ConsultationEntity consultation) {
        this.consultation = Optional.ofNullable(consultation).orElse(ConsultationEntity.createDefault());
    }

    public MedicalRecordEntity getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(final MedicalRecordEntity medicalRecord) {
        this.medicalRecord = Optional.ofNullable(medicalRecord).orElse(MedicalRecordEntity.createDefault());
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = Optional.ofNullable(code).orElse(Integer.valueOf(0));
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(final String annotations) {
        this.annotations = TextHelper.getDefaultWithTrim(annotations);
    }
}