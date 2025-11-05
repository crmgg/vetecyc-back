package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public final class MedicalRecordEntity {

    private UUID id;
    private PetEntity pet;
    private String code;
    private Date creationDate;

    public MedicalRecordEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setPet(PetEntity.createDefault());
        setCode(TextHelper.getDefault());
        setCreationDate(DateHelper.getDefault());
    }

    public MedicalRecordEntity(final UUID id){
        setId(id);
        setPet(PetEntity.createDefault());
        setCode(TextHelper.getDefault());
        setCreationDate(DateHelper.getDefault());
    }

    public MedicalRecordEntity(final UUID id, final PetEntity pet,
                               final String code, final Date creationDate) {
        setId(id);
        setPet(pet);
        setCode(code);
        setCreationDate(creationDate);
    }

    public static MedicalRecordEntity createDefault() {
        return new MedicalRecordEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(final PetEntity pet) {
        this.pet = Optional.ofNullable(pet).orElse(PetEntity.createDefault());
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
        this.creationDate = Optional.ofNullable(creationDate).orElse(new Date());
    }
}