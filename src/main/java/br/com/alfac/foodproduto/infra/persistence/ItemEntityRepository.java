package br.com.alfac.foodproduto.infra.persistence;

import br.com.alfac.foodproduto.core.domain.item.CategoriaItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemEntityRepository extends JpaRepository<ItemEntity, Long> {

    List<ItemEntity> findByCategoria(CategoriaItem categoria);

}