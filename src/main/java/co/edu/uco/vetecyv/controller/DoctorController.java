package co.edu.uco.vetecyv.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.uco.vetecyv.business.facade.impl.DoctorFacadeImpl;
import co.edu.uco.vetecyv.controller.dto.Response;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.dto.DoctorDTO;

@RestController
@RequestMapping("/vetecyv/v1/doctors")
public class DoctorController{

    @GetMapping
    public ResponseEntity<Response<DoctorDTO>> findAllDoctors() {

        Response<DoctorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new DoctorFacadeImpl();
            responseObjectData.setData(facade.findAllDoctors());
            responseObjectData.addMessage(MessagesEnum.SUCCESS_FILTERED.getTitle());

        } catch (final VetecyvException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED.getTitle();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }

    @PostMapping
    public ResponseEntity<Response<DoctorDTO>> registerNewDoctor(@RequestBody DoctorDTO doctor) {

        Response<DoctorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new DoctorFacadeImpl();
            facade.registerNewDoctorInformation(doctor);
            responseObjectData.addMessage(MessagesEnum.SUCCESS_REGISTERED.getTitle());

        } catch (final VetecyvException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED.getTitle();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Response<DoctorDTO>> updateDoctor(@PathVariable UUID id, @RequestBody DoctorDTO doctor) {

        Response<DoctorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new DoctorFacadeImpl();
            facade.updateDoctorInformation(id, doctor);
            responseObjectData.addMessage(MessagesEnum.SUCCESS_UPDATED.getTitle());

        } catch (final VetecyvException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED.getTitle();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Response<DoctorDTO>> dropDoctor(@PathVariable UUID id) {

        Response<DoctorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new DoctorFacadeImpl();
            facade.dropDoctorInformation(id);
            responseObjectData.addMessage(MessagesEnum.SUCCESS_DELETED.getTitle());

        } catch (final VetecyvException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED.getTitle();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Response<DoctorDTO>> findDoctorById(@PathVariable UUID id) {

        Response<DoctorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new DoctorFacadeImpl();
            responseObjectData.setData(List.of(facade.findDoctorById(id)));
            responseObjectData.addMessage(MessagesEnum.SUCCESS_FILTERED.getTitle());

        } catch (final VetecyvException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED.getTitle();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }

    @PostMapping("/filter")
    public ResponseEntity<Response<DoctorDTO>> findDoctorsByFilter(@RequestBody DoctorDTO doctorFilters) {

        Response<DoctorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            var facade = new DoctorFacadeImpl();
            responseObjectData.setData(facade.findDoctorsByFilter(doctorFilters));
            responseObjectData.addMessage(MessagesEnum.SUCCESS_FILTERED.getTitle());

        } catch (final VetecyvException exception) {
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(exception.getUserMessage());
            responseStatusCode = HttpStatus.BAD_REQUEST;
            exception.printStackTrace();
        } catch (final Exception exception) {
            var userMessage = MessagesEnum.USER_ERROR_UNEXPECTED.getTitle();
            responseObjectData = Response.createFailedResponse();
            responseObjectData.addMessage(userMessage);
            responseStatusCode = HttpStatus.INTERNAL_SERVER_ERROR;
            exception.printStackTrace();
        }

        return new ResponseEntity<>(responseObjectData, responseStatusCode);
    }
}
