package co.edu.uco.vetecyv.business.assembler.entity.impl;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.AgendaDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.AgendaEntity;

import java.util.ArrayList;
import java.util.List;

public final class AgendaEntityAssembler implements EntityAssembler<AgendaEntity, AgendaDomain> {

    private static final EntityAssembler<AgendaEntity, AgendaDomain> instance = new AgendaEntityAssembler();

    private AgendaEntityAssembler() {
    }

    public static EntityAssembler<AgendaEntity, AgendaDomain> getAgendaEntityAssembler() {
        return instance;
    }

    @Override
    public AgendaEntity toEntity(final AgendaDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new AgendaDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var specialityDoctorEntity = SpecialityDoctorEntityAssembler.getSpecialityDoctorEntityAssembler().toEntity(domainTmp.getSpecialityDoctor());
        return new AgendaEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                domainTmp.getCode(),
                specialityDoctorEntity,
                domainTmp.getDate(),
                domainTmp.getEndDateTime()
        );
    }

    @Override
    public AgendaDomain toDomain(final AgendaEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new AgendaEntity());
        var specialityDoctorDomain = SpecialityDoctorEntityAssembler.getSpecialityDoctorEntityAssembler().toDomain(entityTmp.getSpecialityDoctor());
        return new AgendaDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                entityTmp.getCode(),
                specialityDoctorDomain,
                entityTmp.getDateTime(),
                entityTmp.getEndDateTime()
        );
    }

    @Override
    public List<AgendaDomain> toDomainList(final List<AgendaEntity> entityList) {
        var safeList = ObjectHelper.getDefault(entityList, new ArrayList<AgendaEntity>());
        var domainList = new ArrayList<AgendaDomain>();
        for (var entity : safeList) {
            domainList.add(toDomain(entity));
        }
        return domainList;
    }

    @Override
    public List<AgendaEntity> toEntityList(final List<AgendaDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<AgendaDomain>());
        var entityList = new ArrayList<AgendaEntity>();
        for (var domain : safeList) {
            entityList.add(toEntity(domain));
        }
        return entityList;
    }
}