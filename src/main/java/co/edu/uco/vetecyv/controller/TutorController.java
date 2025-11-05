// java
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

import co.edu.uco.vetecyv.business.facade.impl.TutorFacadeImpl;
import co.edu.uco.vetecyv.controller.dto.Response;
import co.edu.uco.vetecyv.crosscuting.exception.VetecyvException;
import co.edu.uco.vetecyv.crosscuting.messagescatalog.MessagesEnum;
import co.edu.uco.vetecyv.dto.TutorDTO;

@RestController
@RequestMapping("/vetecyv/v1/tutors")
public class TutorController {

    @GetMapping
    public ResponseEntity<Response<TutorDTO>> findAllTutors() {

        Response<TutorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            TutorFacadeImpl facade = new TutorFacadeImpl();
            responseObjectData.setData(facade.findAllTutors());
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
    public ResponseEntity<Response<TutorDTO>> registerNewTutor(@RequestBody TutorDTO tutor) {

        Response<TutorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            TutorFacadeImpl facade = new TutorFacadeImpl();
            facade.registerNewInformation(tutor);
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
    public ResponseEntity<Response<TutorDTO>> updateTutor(@PathVariable UUID id, @RequestBody TutorDTO tutor) {

        Response<TutorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            TutorFacadeImpl facade = new TutorFacadeImpl();
            facade.updateTutorInformation(id, tutor);
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
    public ResponseEntity<Response<TutorDTO>> dropTutor(@PathVariable UUID id) {

        Response<TutorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            TutorFacadeImpl facade = new TutorFacadeImpl();
            facade.dropTutorInformation(id);
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
    public ResponseEntity<Response<TutorDTO>> findTutorById(@PathVariable UUID id) {

        Response<TutorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            TutorFacadeImpl facade = new TutorFacadeImpl();
            responseObjectData.setData(List.of(facade.findTutorById(id)));
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
    public ResponseEntity<Response<TutorDTO>> findTutorsByFilter(@RequestBody TutorDTO tutorFilters) {

        Response<TutorDTO> responseObjectData = Response.createSuccededResponse();
        HttpStatusCode responseStatusCode = HttpStatus.OK;

        try {
            TutorFacadeImpl facade = new TutorFacadeImpl();
            responseObjectData.setData(facade.findTutorsByFilter(tutorFilters));
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