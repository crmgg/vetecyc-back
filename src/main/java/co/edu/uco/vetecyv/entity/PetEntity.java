package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;


public class PetEntity extends Entity {

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
        super(UUID.randomUUID());
        setTutor(TutorEntity.createDefault());
        setGender(GenderEntity.createDefault());
        setRace(RaceEntity.createDefault());
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(new Date());
        setState(true);
        setColor(TextHelper.getDefault());
    }

    public PetEntity(final UUID id) {
        super(id);
        setTutor(TutorEntity.createDefault());
        setGender(GenderEntity.createDefault());
        setRace(RaceEntity.createDefault());
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(new Date());
        setState(true);
        setColor(TextHelper.getDefault());
    }

    public PetEntity(final UUID id, final TutorEntity tutor, final GenderEntity gender,
                     final RaceEntity race, final Integer code, final String name, final String size,
                     final Date dateBirth, final Boolean state, final String color) {
        super(id);
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

    public TutorEntity getTutor() {
        return tutor;
    }

    public void setTutor(TutorEntity tutor) {
        this.tutor = tutor;
    }

    public GenderEntity getGender() {
        return gender;
    }

    public void setGender(GenderEntity gender) {
        this.gender = gender;
    }

    public RaceEntity getRace() {
        return race;
    }

    public void setRace(RaceEntity race) {
        this.race = race;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = TextHelper.getDefaultWithTrim(size);
    }

    public Date getDateBirth() {
        return dateBirth;
    }

    public void setDateBirth(Date dateBirth) {
        this.dateBirth = dateBirth;
    }

    public Boolean getState() {
        return state;
    }

    public void setState(Boolean state) {
        this.state = state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = TextHelper.getDefaultWithTrim(color);
    }

    public static PetEntity createDefault() {
        return new PetEntity();
    }

}
