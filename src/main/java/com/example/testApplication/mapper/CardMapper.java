package com.example.testApplication.mapper;

import com.example.testApplication.dto.CardDto;
import com.example.testApplication.model.Card;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface CardMapper {

    CardDto entityToDto(Card card);
}
