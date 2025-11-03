package co.edu.uco.vetecyv.data.dao.entity;

import java.util.UUID;

import co.edu.uco.vetecyv.data.dao.RetrieveDAO;
import co.edu.uco.vetecyv.data.dao.CreateDAO;
import co.edu.uco.vetecyv.data.dao.DeleteDAO;
import co.edu.uco.vetecyv.data.dao.UpdateDAO;
import co.edu.uco.vetecyv.entity.TutorEntity;

public interface TutorDAO extends CreateDAO<TutorEntity>, RetrieveDAO<TutorEntity, UUID>, UpdateDAO<TutorEntity>, DeleteDAO<TutorEntity> {
}