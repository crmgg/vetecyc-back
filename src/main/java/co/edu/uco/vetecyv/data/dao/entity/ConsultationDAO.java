package co.edu.uco.vetecyv.data.dao.entity;

import java.util.UUID;

import co.edu.uco.vetecyv.data.dao.CreateDAO;
import co.edu.uco.vetecyv.data.dao.RetrieveDAO;
import co.edu.uco.vetecyv.data.dao.UpdateDAO;
import co.edu.uco.vetecyv.data.dao.DeleteDAO;
import co.edu.uco.vetecyv.entity.ConsultationEntity;

public interface ConsultationDAO extends CreateDAO<ConsultationEntity>,
                                         RetrieveDAO<ConsultationEntity, UUID>,
                                         UpdateDAO<ConsultationEntity>,
                                         DeleteDAO<ConsultationEntity> {
}