package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

public final class MedicalRecordEntity {

    private UUID id;
    private PetEntity pet;
    private Integer code;
    private Date creationDate;

    public MedicalRecordEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setPet(PetEntity.createDefault());
        setCode(Integer.valueOf(0));
        setCreationDate(new Date());
    }

    public MedicalRecordEntity(final UUID id){
        setId(id);
        setPet(PetEntity.createDefault());
        setCode(Integer.valueOf(0));
        setCreationDate(new Date());
    }

    public MedicalRecordEntity(final UUID id, final PetEntity pet,
                               final Integer code, final Date creationDate) {
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

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = Optional.ofNullable(code).orElse(Integer.valueOf(0));
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(final Date creationDate) {
        this.creationDate = Optional.ofNullable(creationDate).orElse(new Date());
    }
}