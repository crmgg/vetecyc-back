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
    public List<PetTypeDomain> toDomainList(final List<PetTypeEntity> entityList) {
        var petTypeDomainList = new ArrayList<PetTypeDomain>();
        for (var petTypeEntity : entityList) {
            petTypeDomainList.add(toDomain(petTypeEntity));
        }
        return petTypeDomainList;
    }
}