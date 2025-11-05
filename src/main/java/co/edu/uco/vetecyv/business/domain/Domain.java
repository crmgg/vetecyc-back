package co.edu.uco.vetecyv.business.domain;

import java.util.UUID;

import co.edu.uco.vetecyv.crosscuting.helper.UUIDHelper;

// anti nulos
// no instanciable
abstract class Domain {

    private UUID id;

    protected Domain(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

    public UUID getId() {
        return id;
    }

    public void setId(final UUID id) {
        this.id = UUIDHelper.getUUIDHelper().getDefault(id);
    }

}