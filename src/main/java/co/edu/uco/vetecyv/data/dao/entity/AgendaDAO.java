package co.edu.uco.vetecyv.data.dao.entity;

import java.util.UUID;

import co.edu.uco.vetecyv.data.dao.CreateDAO;
import co.edu.uco.vetecyv.data.dao.RetrieveDAO;
import co.edu.uco.vetecyv.data.dao.UpdateDAO;
import co.edu.uco.vetecyv.data.dao.DeleteDAO;
import co.edu.uco.vetecyv.entity.AgendaEntity;

public interface AgendaDAO extends CreateDAO<AgendaEntity>,
        RetrieveDAO<AgendaEntity, UUID>, UpdateDAO<AgendaEntity>, DeleteDAO<UUID> {
}