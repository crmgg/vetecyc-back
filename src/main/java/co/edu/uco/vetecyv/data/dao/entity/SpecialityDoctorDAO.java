package co.edu.uco.vetecyv.data.dao.entity;

import java.util.UUID;

import co.edu.uco.vetecyv.data.dao.CreateDAO;
import co.edu.uco.vetecyv.data.dao.RetrieveDAO;
import co.edu.uco.vetecyv.data.dao.UpdateDAO;
import co.edu.uco.vetecyv.data.dao.DeleteDAO;
import co.edu.uco.vetecyv.entity.SpecialityDoctorEntity;

public interface SpecialityDoctorDAO extends CreateDAO<SpecialityDoctorEntity>,
        RetrieveDAO<SpecialityDoctorEntity, UUID>, UpdateDAO<SpecialityDoctorEntity>, DeleteDAO<SpecialityDoctorEntity> {
}