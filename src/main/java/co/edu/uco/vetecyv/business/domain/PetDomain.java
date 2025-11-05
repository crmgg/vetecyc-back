package co.edu.uco.vetecyv.business.domain;

import java.util.Date;
import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;
import co.edu.uco.vetecyv.crosscuting.helper.DateHelper;

public final class PetDomain extends Domain {

    private TutorDomain tutor;
    private GenderDomain gender;
    private RaceDomain race;
    private String code;
    private String name;
    private String size;
    private Date dateBirth;
    private boolean state;
    private String color;

    PetDomain() {
        super(UUIDHelper.getUUIDHelper().getDefault());
        setTutor(TutorDomain.getDefaultValue());
        setGender(GenderDomain.getDefaultValue());
        setRace(RaceDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(DateHelper.getDefault());
        setState(false);
        setColor(TextHelper.getDefault());
    }

    public PetDomain(final UUID id) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
        setTutor(TutorDomain.getDefaultValue());
        setGender(GenderDomain.getDefaultValue());
        setRace(RaceDomain.getDefaultValue());
        setCode(TextHelper.getDefault());
        setName(TextHelper.getDefault());
        setSize(TextHelper.getDefault());
        setDateBirth(DateHelper.getDefault());
        setState(false);
        setColor(TextHelper.getDefault());
    }

    public PetDomain(final UUID id, final TutorDomain tutor, final GenderDomain gender, final RaceDomain race,
                     final String code, final String name, final String size, final Date dateBirth,
                     final boolean state, final String color) {
        super(ObjectHelper.getDefault(id, UUIDHelper.getUUIDHelper().getDefault()));
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

    protected static PetDomain getDefaultValue() {
        return new PetDomain();
    }

    static PetDomain getDefaultValue(final PetDomain pet) {
        return ObjectHelper.getDefault(pet, getDefaultValue());
    }

    public TutorDomain getTutor() {
        return tutor;
    }

    public void setTutor(final TutorDomain tutor) {
        this.tutor = ObjectHelper.getDefault(tutor, TutorDomain.getDefaultValue());
    }

    public GenderDomain getGender() {
        return gender;
    }

    public void setGender(final GenderDomain gender) {
        this.gender = ObjectHelper.getDefault(gender, GenderDomain.getDefaultValue());
    }

    public RaceDomain getRace() {
        return race;
    }

    public void setRace(final RaceDomain race) {
        this.race = ObjectHelper.getDefault(race, RaceDomain.getDefaultValue());
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
        this.dateBirth = ObjectHelper.getDefault(dateBirth, DateHelper.getDefault());
    }

    public boolean isState() {
        return state;
    }

    public void setState(final boolean state) {
        this.state = state;
    }

    public String getColor() {
        return color;
    }

    public void setColor(final String color) {
        this.color = TextHelper.getDefaultWithTrim(color);
    }
}