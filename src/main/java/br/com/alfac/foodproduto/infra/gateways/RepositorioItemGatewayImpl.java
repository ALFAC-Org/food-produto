package br.com.alfac.foodproduto.infra.gateways;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.application.dto.ItemDTO;
import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import br.com.alfac.foodproduto.core.domain.Item;
import br.com.alfac.foodproduto.infra.mapper.ItemEntityMapper;
import br.com.alfac.foodproduto.infra.persistence.ItemEntity;
import br.com.alfac.foodproduto.infra.persistence.ItemEntityRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class RepositorioItemGatewayImpl implements RepositorioItemGateway {

    private final ItemEntityRepository itemEntityRepository;
    private final ItemEntityMapper itemEntityMapper;

    @Autowired
    public RepositorioItemGatewayImpl(final ItemEntityRepository itemEntityRepository, final ItemEntityMapper itemMapper) {
        this.itemEntityRepository = itemEntityRepository;
        this.itemEntityMapper = itemMapper;
    }

    @Override
    public List<Item> consultarItens() {
        var itemEntities = itemEntityRepository.findAll();

        List<Item> items = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntities) {
            Item item = itemEntityMapper.toDomain(itemEntity);
            items.add(item);
        }

        return items;
    }

    @Override
    public List<Item> consultarItensPorCategoria(CategoriaItem categoria) {
        List<ItemEntity> itemEntities = itemEntityRepository.findByCategoria(categoria);

        List<Item> items = new ArrayList<>();

        for (ItemEntity itemEntity : itemEntities) {
            Item item = itemEntityMapper.toDomain(itemEntity);
            items.add(item);
        }

        return items;
    }

    @Override
    public Optional<Item> consultarItemPorId(Long id) {
        Optional<Item> itemOpt = Optional.empty();

        Optional<ItemEntity> itemEntityOpt = itemEntityRepository.findById(id);

        if (itemEntityOpt.isPresent()) {
            ItemEntity itemEntity = itemEntityOpt.get();

            Item item = itemEntityMapper.toDomain(itemEntity);

            itemOpt = Optional.of(item);
        }

        return itemOpt;
    }

    @Override
    public Item atualizarItem(Long id, ItemDTO item) {
        ItemEntity managedItemEntity = itemEntityRepository.findById(id).orElse(null);

        if (managedItemEntity != null) {
            managedItemEntity.setNome(item.getNome());
            managedItemEntity.setPreco(item.getPreco());
            managedItemEntity.setCategoria(item.getCategoria());

            itemEntityRepository.save(managedItemEntity);

            return itemEntityMapper.toDomain(managedItemEntity);
        } else {
            throw new EntityNotFoundException("Item não encontrado para o id informado");
        }
    }

    @Override
    public Item cadastrarItem(Item item) {
        ItemEntity itemEntity = itemEntityMapper.toEntity(item);

        ItemEntity itemCriado = itemEntityRepository.save(itemEntity);

        return itemEntityMapper.toDomain(itemCriado);
    }

    @Transactional
    @Override
    public Item excluirItem(Long id) {
        ItemEntity managedItemEntity = itemEntityRepository.findById(id).orElse(null);

        if (managedItemEntity != null) {
            itemEntityRepository.delete(managedItemEntity);
            return itemEntityMapper.toDomain(managedItemEntity);
        } else {
            throw new EntityNotFoundException("Item não encontrado para o id informado");
        }
    }
}
