package co.edu.uco.vetecyv.dto;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.dto.GenderDTO;
import co.edu.uco.vetecyv.dto.PetDTO;
import co.edu.uco.vetecyv.dto.RaceDTO;
import co.edu.uco.vetecyv.dto.TutorDTO;

import java.util.Date;
import java.util.UUID;

public class PetDTO extends DTO{

    private TutorDTO tutor;
    private GenderDTO gender;
    private RaceDTO race;
    private Integer code;
    private String name;
    private String size;
    private Date dateBirth;
    private Boolean state;
    private String color;

    public PetDTO() {
        super(UUID.randomUUID());
        setTutor(TutorDTO.createDefault());
        setGender(GenderDTO.createDefault());
        setRace(RaceDTO.createDefault());
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(new Date());
        setState(true);
        setColor(TextHelper.getDefault());
    }

    public PetDTO(final UUID id) {
        super(id);
        setTutor(TutorDTO.createDefault());
        setGender(GenderDTO.createDefault());
        setRace(RaceDTO.createDefault());
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(new Date());
        setState(true);
        setColor(TextHelper.getDefault());
    }

    public PetDTO(final UUID id, final TutorDTO tutor, final GenderDTO gender,
                     final RaceDTO race, final Integer code, final String name, final String size,
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

    public TutorDTO getTutor() {
        return tutor;
    }

    public void setTutor(TutorDTO tutor) {
        this.tutor = tutor;
    }

    public GenderDTO getGender() {
        return gender;
    }

    public void setGender(GenderDTO gender) {
        this.gender = gender;
    }

    public RaceDTO getRace() {
        return race;
    }

    public void setRace(RaceDTO race) {
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

    public static PetDTO createDefault() {
        return new PetDTO();
    }

}

