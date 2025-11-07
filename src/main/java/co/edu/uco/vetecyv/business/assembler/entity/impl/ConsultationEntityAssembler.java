package co.edu.uco.vetecyv.business.assembler.entity.impl;

import static co.edu.uco.vetecyv.business.assembler.entity.impl.AppointmentEntityAssembler.getAppointmentEntityAssembler;
import static co.edu.uco.vetecyv.business.assembler.entity.impl.DetailEntityAssembler.getDetailEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.ConsultationDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.ConsultationEntity;

public final class ConsultationEntityAssembler implements EntityAssembler<ConsultationEntity, ConsultationDomain> {

    private static final EntityAssembler<ConsultationEntity, ConsultationDomain> instance = new ConsultationEntityAssembler();

    private ConsultationEntityAssembler() {
    }

    public static EntityAssembler<ConsultationEntity, ConsultationDomain> getConsultationEntityAssembler() {
        return instance;
    }

    @Override
    public ConsultationEntity toEntity(final ConsultationDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new ConsultationDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var appointmentEntity = getAppointmentEntityAssembler().toEntity(domainTmp.getAppointment());
        var detailEntity = getDetailEntityAssembler().toEntity(domainTmp.getDetail());

        return new ConsultationEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                appointmentEntity,
                detailEntity,
                domainTmp.getCode(),
                domainTmp.getConsultationPrice()
        );
    }

    @Override
    public ConsultationDomain toDomain(final ConsultationEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new ConsultationEntity());
        var appointmentDomain = getAppointmentEntityAssembler().toDomain(entityTmp.getAppointment());
        var detailDomain = getDetailEntityAssembler().toDomain(entityTmp.getDetail());

        return new ConsultationDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                appointmentDomain,
                detailDomain,
                entityTmp.getCode(),
                entityTmp.getConsultationPrice()
        );
    }

    @Override
    public List<ConsultationDomain> toDomainList(final List<ConsultationEntity> entityList) {
        var consultationDomainList = new ArrayList<ConsultationDomain>();

        for (var consultationEntity : entityList) {
            consultationDomainList.add(toDomain(consultationEntity));
        }
        return consultationDomainList;
    }
}