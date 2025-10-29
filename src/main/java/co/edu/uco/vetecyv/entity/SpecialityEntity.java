package co.edu.uco.vetecyv.entity;

import co.edu.uco.vetecyv.crosscuting.helper.TextHelper;

import java.util.Date;
import java.util.UUID;

public class SpecialityEntity extends Entity {

    private Integer code;
    private Date dateTime;
    private String name;

    public SpecialityEntity(){
        super(UUID.randomUUID());
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setDateTime(new Date());
    }

    public SpecialityEntity(final UUID id){
        super(id);
        setCode(Integer.valueOf(0));
        setName(TextHelper.getDefault());
        setDateTime(new Date());
    }

    public SpecialityEntity(final UUID id, final Integer code, final String name, final Date dateTime) {
        super(id);
        setCode(code);
        setName(name);
        setDateTime(dateTime);
    }

    public static SpecialityEntity createDefault() {
        return new SpecialityEntity();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public Date getDateTime() {
        return dateTime;
    }

    public void setDateTime(Date dateTime) {
        this.dateTime = dateTime;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = TextHelper.getDefaultWithTrim(name);
    }

}
