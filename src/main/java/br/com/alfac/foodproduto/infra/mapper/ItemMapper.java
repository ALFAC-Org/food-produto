package br.com.alfac.foodproduto.infra.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.alfac.foodproduto.infra.dto.ItemRequest;
import br.com.alfac.foodproduto.core.application.dto.ItemDTO;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    ItemMapper INSTANCE = Mappers.getMapper(ItemMapper.class);
    ItemDTO toDTO(ItemRequest itemRequest);
    
}
