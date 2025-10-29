package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

public class MedicalRecordEntity extends Entity{

    private PetEntity pet;
    private Integer code;
    private Date creationDate;

    public MedicalRecordEntity() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setPet(PetEntity.createDefault());
        setCode(Integer.valueOf(0));
        setCreationDate(new Date());
    }

    public MedicalRecordEntity(final UUID id){
        super(id);
        setPet(PetEntity.createDefault());
        setCode(Integer.valueOf(0));
        setCreationDate(new Date());
    }

    public MedicalRecordEntity(final UUID id, final PetEntity pet,
                               final Integer code, final Date creationDate) {
        super(id);
        setPet(pet);
        setCode(code);
        setCreationDate(creationDate);
    }

    public static MedicalRecordEntity createDefault() {
        return new MedicalRecordEntity();
    }

    public PetEntity getPet() {
        return pet;
    }

    public void setPet(PetEntity pet) {
        this.pet = pet;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }


}
