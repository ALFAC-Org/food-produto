package br.com.alfac.foodproduto.infra.mapper;

import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.infra.persistence.ItemEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemEntityMapper {

    ItemEntity toEntity(Item item);

    Item toDomain(ItemEntity Item);

}
