package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.UUID;

public class DetailEntity extends Entity {

    private ConsultationEntity consultation;
    private MedicalRecordEntity medicalRecord;
    private Integer code;
    private String annotations;

    public DetailEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setConsultation(ConsultationEntity.createDefault());
        setMedicalRecord(MedicalRecordEntity.createDefault());
        setCode(Integer.valueOf(0));
        setAnnotations(TextHelper.getDefault());
    }

    public DetailEntity(final UUID id) {
        super(id);
        setConsultation(ConsultationEntity.createDefault());
        setMedicalRecord(MedicalRecordEntity.createDefault());
        setCode(Integer.valueOf(0));
        setAnnotations(TextHelper.getDefault());
    }

    public DetailEntity(final UUID id, final ConsultationEntity consultation,
                        final MedicalRecordEntity medicalRecord, final Integer code,
                        final String annotations) {
        super(id);
        setConsultation(consultation);
        setMedicalRecord(medicalRecord);
        setCode(code);
        setAnnotations(annotations);
    }

    public ConsultationEntity getConsultation() {
        return consultation;
    }

    public void setConsultation(ConsultationEntity consultation) {
        this.consultation = consultation;
    }

    public MedicalRecordEntity getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecordEntity medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getAnnotations() {
        return annotations;
    }

    public void setAnnotations(String annotations) {
        this.annotations = TextHelper.getDefaultWithTrim(annotations);
    }

    public static DetailEntity createDefault() {
        return new DetailEntity();
    }


}
