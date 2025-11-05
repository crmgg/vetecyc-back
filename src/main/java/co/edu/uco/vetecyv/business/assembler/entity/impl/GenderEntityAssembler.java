package co.edu.uco.vetecyv.business.assembler.entity.impl;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.GenderDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.GenderEntity;

import java.util.ArrayList;
import java.util.List;

public final class GenderEntityAssembler implements EntityAssembler<GenderEntity, GenderDomain> {

    private static final EntityAssembler<GenderEntity, GenderDomain> instance = new GenderEntityAssembler();

    private GenderEntityAssembler() {
    }

    public static EntityAssembler<GenderEntity, GenderDomain> getGenderEntityAssembler() {
        return instance;
    }

    @Override
    public GenderEntity toEntity(final GenderDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new GenderDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new GenderEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                domainTmp.getName()
        );
    }

    @Override
    public GenderDomain toDomain(final GenderEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new GenderEntity());
        return new GenderDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                entityTmp.getName()
        );
    }

    @Override
    public List<GenderEntity> toEntityList(List<GenderDomain> domainList) {
        var safeList = ObjectHelper.getDefault(domainList, new ArrayList<GenderDomain>());
        var entityList = new ArrayList<GenderEntity>();

        for (var domain : safeList) {
            entityList.add(toEntity(domain));
        }
        return entityList;
    }

    @Override
    public List<GenderDomain> toDomainList(final List<GenderEntity> entityList) {
        var safeList = ObjectHelper.getDefault(entityList, new ArrayList<GenderEntity>());
        var domainList = new ArrayList<GenderDomain>();

        for (var entity : safeList) {
            domainList.add(toDomain(entity));
        }
        return domainList;
    }
}