package co.edu.uco.vetecyv.business.domain;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

public final class DetailDomain extends Domain {

    private ConsultationDomain consultation;
    private MedicalRecordDomain medicalRecord;
    private String code;
    private String annotation;

    DetailDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setConsultation(ConsultationDomain.getDefaultValue());
        setMedicalRecord(MedicalRecordDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setAnnotation(TextHelper.getDefault());
    }

    public DetailDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setConsultation(ConsultationDomain.getDefaultValue());
        setMedicalRecord(MedicalRecordDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setAnnotation(TextHelper.getDefault());
    }

    public DetailDomain(final UUID id, final ConsultationDomain consultation, final MedicalRecordDomain medicalRecord,
                        final String code, final String annotation) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setConsultation(consultation);
        setMedicalRecord(medicalRecord);
        setCode(code);
        setAnnotation(annotation);
    }

    protected static DetailDomain getDefaultValue() {
        return new DetailDomain();
    }

    static DetailDomain getDefaultValue(final DetailDomain detail) {
        return ObjectHelper.getDefault(detail, getDefaultValue());
    }

    public ConsultationDomain getConsultation() {
        return consultation;
    }

    public void setConsultation(final ConsultationDomain consultation) {
        this.consultation = ObjectHelper.getDefault(consultation, ConsultationDomain.getDefaultValue());
    }

    public MedicalRecordDomain getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(final MedicalRecordDomain medicalRecord) {
        this.medicalRecord = ObjectHelper.getDefault(medicalRecord, MedicalRecordDomain.getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }

    public String getAnnotation() {
        return annotation;
    }

    public void setAnnotation(final String annotation) {
        this.annotation = TextHelper.getDefaultWithTrim(annotation);
    }
}