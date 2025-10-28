package co.edu.uco.vetecyv.business.assembler.dto;


import java.util.List;

public interface DTOAssembler <T, D> {

    T toDTO(D domain);

    D toDomain(T dto);

    List<T> toDTO(List<D> domainList);
}

