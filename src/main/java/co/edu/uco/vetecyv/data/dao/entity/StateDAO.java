package co.edu.uco.vetecyv.data.dao.entity;

import co.edu.uco.vetecyv.data.dao.RetrieveDAO;
import co.edu.uco.vetecyv.data.dao.CreateDAO;
import co.edu.uco.vetecyv.data.dao.DeleteDAO;
import co.edu.uco.vetecyv.data.dao.UpdateDAO;
import co.edu.uco.vetecyv.entity.StateEntity;

import java.util.UUID;

public interface StateDAO extends CreateDAO<StateEntity>,
        RetrieveDAO<StateEntity, UUID>, UpdateDAO<StateEntity>, DeleteDAO<UUID> {
}