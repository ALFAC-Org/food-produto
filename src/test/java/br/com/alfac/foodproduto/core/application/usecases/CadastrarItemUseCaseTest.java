package br.com.alfac.foodproduto.core.application.usecases;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;
import br.com.alfac.foodproduto.core.domain.item.Item;
import br.com.alfac.foodproduto.core.exception.FoodProdutoException;
import utils.ItemHelper;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CadastrarItemUseCaseTest {

    @Mock
    private RepositorioItemGateway repositorioItemGateway;

    @Test
    void devePermitirCadastrarItem() throws FoodProdutoException {

    }

}