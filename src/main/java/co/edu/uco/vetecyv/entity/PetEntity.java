package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public final class PetEntity {

    private UUID id;
    private TutorEntity tutor;
    private GenderEntity gender;
    private RaceEntity race;
    private Integer code;
    private String name;
    private String size;
    private Date dateBirth;
    private Boolean state;
    private String color;

    public PetEntity() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setTutor(TutorEntity.createDefault());
        setGender(GenderEntity.createDefault());
        setRace(RaceEntity.createDefault());
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(new Date());
        setState(Boolean.TRUE);
        setColor(TextHelper.getDefault());
    }

    public PetEntity(final UUID id) {
        setId(id);
        setTutor(TutorEntity.createDefault());
        setGender(GenderEntity.createDefault());
        setRace(RaceEntity.createDefault());
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(new Date());
        setState(Boolean.TRUE);
        setColor(TextHelper.getDefault());
    }

    public PetEntity(final UUID id, final TutorEntity tutor, final GenderEntity gender,
                     final RaceEntity race, final Integer code, final String name, final String size,
                     final Date dateBirth, final Boolean state, final String color) {
        setId(id);
        setTutor(tutor);
        setGender(gender);
        setRace(race);
        setCode(code);
        setName(name);
        setSize(size);
        setDateBirth(dateBirth);
        setState(state);
        setColor(color);
    }

    public static PetEntity createDefault() {
        return new PetEntity();
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public TutorEntity getTutor() {
        return tutor;
    }

    public void setTutor(final TutorEntity tutor) {
        this.tutor = tutor == null ? TutorEntity.createDefault() : tutor;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public void setGender(final GenderEntity gender) {
        this.gender = gender == null ? GenderEntity.createDefault() : gender;
    }

    public RaceEntity getRace() {
        return race;
    }

    public void setRace(final RaceEntity race) {
        this.race = race == null ? RaceEntity.createDefault() : race;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(final Integer code) {
        this.code = code == null ? Integer.valueOf(0) : code;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }

    public String getSize() {
        return size;
    }

    public void setSize(final String size) {
        this.size = TextHelper.getDefaultWithTrim(size);
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(final Date dateBirth) {
        this.dateBirth = dateBirth == null ? new Date() : dateBirth;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(final Boolean state) {
        this.state = state == null ? Boolean.TRUE : state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = TextHelper.getDefaultWithTrim(color);
    }
}