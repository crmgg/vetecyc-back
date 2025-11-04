package co.edu.uco.vetecyv.data.dao.entity;

import java.util.UUID;

import co.edu.uco.vetecyv.data.dao.CreateDAO;
import co.edu.uco.vetecyv.data.dao.DeleteDAO;
import co.edu.uco.vetecyv.data.dao.RetrieveDAO;
import co.edu.uco.vetecyv.data.dao.UpdateDAO;
import co.edu.uco.vetecyv.entity.GenderEntity;

public interface GenderDAO extends CreateDAO<GenderEntity>,
        RetrieveDAO<GenderEntity, UUID>, UpdateDAO<GenderEntity>, DeleteDAO<UUID> {
}