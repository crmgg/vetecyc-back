package co.edu.uco.vetecyv.business.facade.impl;

import co.edu.uco.vetecyv.business.facade.AdministratorFacade;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.data.dao.factory.DAOFactory;
import co.edu.uco.vetecyv.dto.AdministratorDTO;

public class AdministratorFacadeImpl implements AdministratorFacade {

    @Override
    public void registerNewInformation(final AdministratorDTO administratorDTO){

        var daoFactory = DAOFactory.getFactory();
        var business = new AdministratorBusinessImpl(daoFactory);

        try {
            daoFactory.initTransaction();

            var domain = AdministratorAssembler.getInstance().toDomainFromDTO(administratorDTO);
            business.registerNewInformation(domain);

            daoFactory.commitTransaction();
        } catch (final VetecyvException)

    }

}
