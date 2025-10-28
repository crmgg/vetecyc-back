package co.edu.uco.vetecyv.business.business;

import java.util.List;
import java.util.UUID;

import co.edu.uco.vetecyv.business.domain.AdministratorDomain;

public interface AdministratorBusiness {

    void registerNewAdministratorInformation (AdministratorDomain administratorDomain);

    void dropAdministratorInformation (UUID id);

    void updateAdministratorInformation (UUID id, AdministratorDomain administratorDomain);

    List<AdministratorDomain> findAllAdministrators ();

    List <AdministratorDomain> findAdministratorsByFilter (AdministratorDomain administratorFilters);

    AdministratorDomain findSpecificAdministrator(UUID id);

    void confirmMobileNumber(UUID id, int confirmationCode);

    void confirmEmail (UUID id, int confirmationCode);

    void accountState (UUID id, int accountStateCode);

    void sendMobileNumberConfirmation (UUID id);

    void sendEmailConfirmation (UUID id);

    void sendAccountState(UUID id);

}