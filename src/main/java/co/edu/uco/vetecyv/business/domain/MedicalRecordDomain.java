package co.edu.uco.vetecyv.business.domain;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;

public final class MedicalRecordDomain extends Domain {

    private PetDomain pet;
    private String code;
    private Date creationDate;

    MedicalRecordDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setPet(PetDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setCreationDate(DateHelper.getDefault());
    }

    public MedicalRecordDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setPet(PetDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setCreationDate(DateHelper.getDefault());
    }

    public MedicalRecordDomain(final UUID id, final PetDomain pet, final String code, final Date creationDate) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setPet(pet);
        setCode(code);
        setCreationDate(creationDate);
    }

    protected static MedicalRecordDomain getDefaultValue() {
        return new MedicalRecordDomain();
    }

    static MedicalRecordDomain getDefaultValue(final MedicalRecordDomain record) {
        return ObjectHelper.getDefault(record, getDefaultValue());
    }

    public PetDomain getPet() {
        return pet;
    }

    public void setPet(final PetDomain pet) {
        this.pet = ObjectHelper.getDefault(pet, PetDomain.getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = ObjectHelper.getDefault(creationDate, DateHelper.getDefault());
    }
}