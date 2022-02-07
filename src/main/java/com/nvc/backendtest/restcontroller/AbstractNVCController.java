package com.nvc.backendtest.restcontroller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractNVCController<E, T> {

    protected ModelMapper modelMapper;

    protected E toEntity(T dto, Class<E> objectClass) {
        E result = modelMapper.map(dto, objectClass);
        return result;
    }

    protected T toDTO(E entity, Class<T> objectClass) {
        T result = modelMapper.map(entity, objectClass);
        return result;
    }

    @Autowired
    protected void setModelMapper(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }
}
