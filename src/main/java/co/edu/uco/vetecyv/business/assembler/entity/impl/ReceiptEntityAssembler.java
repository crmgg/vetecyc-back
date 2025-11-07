package co.edu.uco.vetecyv.business.assembler.entity.impl;

import static co.edu.uco.vetecyv.business.assembler.entity.impl.AdministratorEntityAssembler.getAdministratorEntityAssembler;
import static co.edu.uco.vetecyv.business.assembler.entity.impl.ConsultationEntityAssembler.getConsultationEntityAssembler;

import java.util.ArrayList;
import java.util.List;

import co.edu.uco.vetecyv.business.assembler.entity.EntityAssembler;
import co.edu.uco.vetecyv.business.domain.ReceiptDomain;
import co.edu.uco.vetecyv.crosscuting.helper.ObjectHelper;
import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;
import co.edu.uco.vetecyv.entity.ReceiptEntity;

public final class ReceiptEntityAssembler implements EntityAssembler<ReceiptEntity, ReceiptDomain> {

    private static final EntityAssembler<ReceiptEntity, ReceiptDomain> instance = new ReceiptEntityAssembler();

    private ReceiptEntityAssembler() {
    }

    public static EntityAssembler<ReceiptEntity, ReceiptDomain> getReceiptEntityAssembler() {
        return instance;
    }

    @Override
    public ReceiptEntity toEntity(final ReceiptDomain domain) {
        var domainTmp = ObjectHelper.getDefault(domain, new ReceiptDomain(UUIDHelper.getUUIDHelper().getDefault()));
        var administratorEntity = getAdministratorEntityAssembler().toEntity(domainTmp.getAdministrator());
        var consultationEntity = getConsultationEntityAssembler().toEntity(domainTmp.getConsultation());

        return new ReceiptEntity(
                UUIDHelper.getUUIDHelper().getDefault(domainTmp.getId()),
                administratorEntity,
                consultationEntity,
                domainTmp.getCode(),
                domainTmp.getDateTime(),
                domainTmp.getTotalCoast()
        );
    }

    @Override
    public ReceiptDomain toDomain(final ReceiptEntity entity) {
        var entityTmp = ObjectHelper.getDefault(entity, new ReceiptEntity());
        var administratorDomain = getAdministratorEntityAssembler().toDomain(entityTmp.getAdministrator());
        var consultationDomain = getConsultationEntityAssembler().toDomain(entityTmp.getConsultation());

        return new ReceiptDomain(
                UUIDHelper.getUUIDHelper().getDefault(entityTmp.getId()),
                administratorDomain,
                consultationDomain,
                entityTmp.getCode(),
                entityTmp.getDateTime(),
                entityTmp.getTotalCoast()
        );
    }

    @Override
    public List<ReceiptDomain> toDomainList(final List<ReceiptEntity> entityList) {
        var receiptDomainList = new ArrayList<ReceiptDomain>();

        for (var receiptEntity : entityList) {
            receiptDomainList.add(toDomain(receiptEntity));
        }
        return receiptDomainList;
    }
}