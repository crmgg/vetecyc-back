package co.edu.uco.vetecyv.business.assembler.entity.impl;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.SpecialityDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.SpecialityEntity;

public final class SpecialityEntityAssembler implements EntityAssembler<SpecialityEntity, SpecialityDomain> {

    private static final EntityAssembler<SpecialityEntity, SpecialityDomain> instance = new SpecialityEntityAssembler();

    private SpecialityEntityAssembler() {
    }

    public static EntityAssembler<SpecialityEntity, SpecialityDomain> getSpecialityEntityAssembler() {
        return instance;
    }

    @Override
    public SpecialityEntity toEntity(final SpecialityDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new SpecialityDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new SpecialityEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                domainTmp.getCode(),
                domainTmp.getName()
        );
    }

    @Override
    public SpecialityDomain toDomain(final SpecialityEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new SpecialityEntity());
        return new SpecialityDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                entityTmp.getName(),
                entityTmp.getCode()
        );
    }

    @Override
    public List<SpecialityEntity> toEntityList(final List<SpecialityDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<SpecialityDomain>());
        var entityList = new ArrayList<SpecialityEntity>();

        for (var domain : safeList) {
            entityList.add(toEntity(domain));
        }
        return entityList;
    }

    @Override
    public List<SpecialityDomain> toDomainList(final List<SpecialityEntity> entityList) {
        var safeList = ObjectHelper.getDefault(entityList, new ArrayList<SpecialityEntity>());
        var domainList = new ArrayList<SpecialityDomain>();

        for (var entity : safeList) {
            domainList.add(toDomain(entity));
        }
        return domainList;
    }
}