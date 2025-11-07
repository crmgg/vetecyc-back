package co.edu.uco.vetecyv.business.assembler.entity.impl;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.StateDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.StateEntity;

import java.util.ArrayList;
import java.util.List;

public final class StateEntityAssembler implements EntityAssembler<StateEntity, StateDomain> {

    private static final EntityAssembler<StateEntity, StateDomain> instance = new StateEntityAssembler();

    private StateEntityAssembler() {
    }

    public static EntityAssembler<StateEntity, StateDomain> getStateEntityAssembler() {
        return instance;
    }

    @Override
    public StateEntity toEntity(final StateDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new StateDomain(UUIDHelper.getUUIDHelper().getDefault()));
        return new StateEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                domainTmp.getName()
        );
    }

    @Override
    public StateDomain toDomain(final StateEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new StateEntity());
        return new StateDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                entityTmp.getName()
        );
    }

    @Override
    public List<StateDomain> toDomainList(final List<StateEntity> entityList) {
        var stateDomainList = new ArrayList<StateDomain>();

        for (var stateEntity : entityList) {
            stateDomainList.add(toDomain(stateEntity));
        }
        return stateDomainList;
    }
}