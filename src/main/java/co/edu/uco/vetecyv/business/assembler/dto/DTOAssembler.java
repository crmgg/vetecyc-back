package co.edu.uco.vetecyv.business.assembler.dto;


import co.edu.uco.vetecyv.business.domain.AppointmentDomain;
import co.edu.uco.vetecyv.dto.AppointmentDTO;

import java.util.List;

public interface DTOAssembler <T, D> {

    T toDTO(D domain);

    D toDomain(T dto);

    List<T> toDTO(List<D> domainList);

}

