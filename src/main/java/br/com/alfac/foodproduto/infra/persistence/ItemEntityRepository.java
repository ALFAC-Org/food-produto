package br.com.alfac.foodproduto.infra.persistence;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.com.alfac.foodproduto.core.domain.CategoriaItem;

// @Repository
@EnableScan
public interface ItemEntityRepository extends CrudRepository<ItemEntity, Long> {

    List<ItemEntity> findByCategoria(CategoriaItem categoria);

}