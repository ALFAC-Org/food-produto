package br.com.alfac.foodproduto.infra.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.alfac.foodproduto.core.domain.CategoriaItem;

import java.util.List;

@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByCategoria(CategoriaItem categoria);

}