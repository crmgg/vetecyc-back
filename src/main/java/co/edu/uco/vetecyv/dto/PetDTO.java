package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

import java.util.Date;
import java.util.UUID;

public final class PetDTO {

    private UUID id;
    private TutorDTO tutor;
    private GenderDTO gender;
    private RaceDTO race;
    private String code;
    private String name;
    private String size;
    private Date dateBirth;
    private Boolean state;
    private String color;

    public PetDTO() {
        setId(UUIDHelper.getUUIDHelper().getDefault());
        setTutor(TutorDTO.getDefaultValue());
        setGender(GenderDTO.getDefaultValue());
        setRace(RaceDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(DateHelper.getDefault());
        setState(false);
        setColor(TextHelper.getDefault());
    }

    public PetDTO(final UUID id) {
        setId(id);
        setTutor(TutorDTO.getDefaultValue());
        setGender(GenderDTO.getDefaultValue());
        setRace(RaceDTO.getDefaultValue());
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(DateHelper.getDefault());
        setState(false);
        setColor(TextHelper.getDefault());
    }

    public PetDTO(final UUID id, final TutorDTO tutor, final GenderDTO gender,
                  final RaceDTO race, final String code, final String name, final String size,
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

    static PetDTO getDefaultValue() {
        return new PetDTO();
    }

    static PetDTO getDefaultValue(final PetDTO pet) {
        return ObjectHelper.getDefault(pet, getDefaultValue());
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public TutorDTO getTutor() {
        return tutor;
    }

    public void setTutor(final TutorDTO tutor) {
        this.tutor = ObjectHelper.getDefault(tutor, TutorDTO.getDefaultValue());
    }

    public GenderDTO getGender() {
        return gender;
    }

    public void setGender(final GenderDTO gender) {
        this.gender = ObjectHelper.getDefault(gender, GenderDTO.getDefaultValue());
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(final RaceDTO race) {
        this.race = ObjectHelper.getDefault(race, RaceDTO.getDefaultValue());
    }

    public String getCode() {
        return code;
    }

    public void setCode(final String code) {
        this.code = TextHelper.getDefaultWithTrim(code);
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
        this.dateBirth = DateHelper.getDefault(dateBirth);
    }

    public Boolean getState() {
        return state;
    }

    public void setState(final Boolean state) {
        this.state = ObjectHelper.getDefault(state, Boolean.TRUE);
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = TextHelper.getDefaultWithTrim(color);
    }
}