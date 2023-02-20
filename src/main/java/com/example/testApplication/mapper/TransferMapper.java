package com.example.testApplication.mapper;

import com.example.testApplication.dto.TransferDto;
import com.example.testApplication.model.Transfer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransferMapper {

    Transfer dtoToEntity(TransferDto dto);
}
