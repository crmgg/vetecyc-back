// java
package co.edu.uco.vetecyv.data.dao.entity;

import java.util.UUID;

import co.edu.uco.vetecyv.data.dao.CreateDAO;
import co.edu.uco.vetecyv.data.dao.RetrieveDAO;
import co.edu.uco.vetecyv.data.dao.UpdateDAO;
import co.edu.uco.vetecyv.data.dao.DeleteDAO;
import co.edu.uco.vetecyv.entity.AdministratorEntity;

public interface AdministratorDAO extends CreateDAO<AdministratorEntity>,
        RetrieveDAO<AdministratorEntity, UUID>, UpdateDAO<AdministratorEntity>, DeleteDAO<UUID> {
}