package co.edu.uco.vetecyv.business.assembler.entity.impl;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.PetTypeDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.PetTypeEntity;

import java.util.ArrayList;
import java.util.List;

public final class PetTypeEntityAssembler implements EntityAssembler<PetTypeEntity, PetTypeDomain> {

    private static final EntityAssembler<PetTypeEntity, PetTypeDomain> instance = new PetTypeEntityAssembler();

    private PetTypeEntityAssembler() {
    }

    public static EntityAssembler<PetTypeEntity, PetTypeDomain> getPetTypeEntityAssembler() {
        return instance;
    }

    @Override
    public PetTypeEntity toEntity(final PetTypeDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new PetTypeDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new PetTypeEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                domainTmp.getName()
        );
    }

    @Override
    public PetTypeDomain toDomain(final PetTypeEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new PetTypeEntity());
        return new PetTypeDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                entityTmp.getName()
        );
    }

    @Override
    public List<PetTypeEntity> toEntityList(final List<PetTypeDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<PetTypeDomain>());
        var entityList = new ArrayList<PetTypeEntity>();
        for (var domain : safeList) {
            entityList.add(toEntity(domain));
        }
        return entityList;
    }

    @Override
    public List<PetTypeDomain> toDomainList(final List<PetTypeEntity> entityList) {
        var safeList = ObjectHelper.getDefault(entityList, new ArrayList<PetTypeEntity>());
        var domainList = new ArrayList<PetTypeDomain>();
        for (var entity : safeList) {
            domainList.add(toDomain(entity));
        }
        return domainList;
    }
}