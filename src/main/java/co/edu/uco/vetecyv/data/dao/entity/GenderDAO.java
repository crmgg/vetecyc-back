package co.edu.uco.vetecyv.data.dao.entity;

import java.util.UUID;

import co.edu.uco.vetecyv.data.dao.RetrieveDAO;
import co.edu.uco.vetecyv.data.dao.UpdateDAO;
import co.edu.uco.vetecyv.entity.GenderEntity;

public interface GenderDAO extends RetrieveDAO<GenderEntity, UUID>,
                                   UpdateDAO<GenderEntity> {
}