package co.edu.uco.vetecyv.business.facade;

import co.edu.uco.vetecyv.dto.AdministratorDTO;

import java.util.List;
import java.util.UUID;

public interface AdministratorFacade {

    void registerNewInformation(AdministratorDTO administratorDTO);

    void dropAdministratorInformation(UUID id);

    void updateAdministratorInformation(UUID id, AdministratorDTO administratorDTO);

    List<AdministratorDTO> findAllAdministrators();

    List<AdministratorDTO> findAdministratorsByFilter(AdministratorDTO filter);

    AdministratorDTO findAdministratorById(UUID id);

    void sendPhoneNumberConfirmationCode(UUID id, int confirmationCode);

    void sendEmailConfirmationCode(UUID id, int confirmationCode);

    void confirAccountStatus(UUID id, int confirmationCode);


}
