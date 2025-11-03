package co.edu.uco.vetecyv.business.facade;

import co.edu.uco.vetecyv.dto.AdministratorDTO;

import java.util.List;
import java.util.UUID;

public interface AdministratorFacade {

    void registerNewInformation(AdministratorDTO administratorDTO);

    void dropAdministrtorInformation(UUID id);

    void updateAdministratorInformation(UUID id);

    List<AdministratorDTO> findAllAdministrators();

    List<AdministratorDTO> findAdministratorsByFilter(AdministratorDTO filter);

    AdministratorDTO findAdministratorById(UUID id);

    void confirmPhoneNumber(UUID id, int confirmationCode);

    void confirEmal(UUID id, int confirmationCode);

    void confirAccountStatus(UUID id, int confirmationCode);

    void sendPhoneNumberConfirmationCode(UUID id);

    void sendEmailConfirmationCode(UUID id);

    void sendAccountStatusConfirmationCode(UUID id);

}
