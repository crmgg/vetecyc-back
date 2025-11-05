package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

import java.util.Date;
import java.util.UUID;

public final class MedicalRecordDTO {

    private UUID id;
    private PetDTO pet;
    private String code;
    private Date creationDate;

    public MedicalRecordDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setPet(PetDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setCreationDate(DateHelper.getDefault());
    }

    public MedicalRecordDTO(final UUID id) {
        setId(id);
        setPet(PetDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setCreationDate(DateHelper.getDefault());
    }

    public MedicalRecordDTO(final UUID id, final PetDTO pet,
                            final String code, final Date creationDate) {
        setId(id);
        setPet(pet);
        setCode(code);
        setCreationDate(creationDate);
    }

    static MedicalRecordDTO getDefaultValue() {
        return new MedicalRecordDTO();
    }

    static MedicalRecordDTO getDefaultValue(final MedicalRecordDTO medicalRecord) {
        return ObjectHelper.getDefault(medicalRecord, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public PetDTO getPet() {
        return pet;
    }

    public void setPet(final PetDTO pet) {
        this.pet = ObjectHelper.getDefault(pet, PetDTO.getDefaultValue());
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
        this.creationDate = DateHelper.getDefault(creationDate);
    }
}