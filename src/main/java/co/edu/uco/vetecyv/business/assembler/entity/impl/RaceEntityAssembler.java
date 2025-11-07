package co.edu.uco.vetecyv.business.assembler.entity.impl;

import static co.edu.uco.vetecyv.business.assembler.entity.impl.PetTypeEntityAssembler.getPetTypeEntityAssembler;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.RaceDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.RaceEntity;

import java.util.ArrayList;
import java.util.List;

public final class RaceEntityAssembler implements EntityAssembler<RaceEntity, RaceDomain> {

    private static final EntityAssembler<RaceEntity, RaceDomain> instance = new RaceEntityAssembler();

    private RaceEntityAssembler() {
    }

    public static EntityAssembler<RaceEntity, RaceDomain> getRaceEntityAssembler() {
        return instance;
    }

    @Override
    public RaceEntity toEntity(final RaceDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new RaceDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var petTypeEntity = getPetTypeEntityAssembler().toEntity(domainTmp.getPetType());

        return new RaceEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                petTypeEntity,
                domainTmp.getName()
        );
    }

    @Override
    public RaceDomain toDomain(final RaceEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new RaceEntity());
        var petTypeDomain = getPetTypeEntityAssembler().toDomain(entityTmp.getPetType());

        return new RaceDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                petTypeDomain,
                entityTmp.getName()
        );
    }

    @Override
    public List<RaceDomain> toDomainList(final List<RaceEntity> entityList) {
        var raceDomainList = new ArrayList<RaceDomain>();

        for (var raceEntity : entityList) {
            raceDomainList.add(toDomain(raceEntity));
        }
        return raceDomainList;
    }
}