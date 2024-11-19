package br.com.alfac.foodproduto.infra.config.beans;

import br.com.alfac.foodproduto.core.application.adapters.controller.ControladorItem;
import br.com.alfac.foodproduto.core.application.adapters.gateways.RepositorioItemGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfiguration {

    @Bean
    public ControladorItem controladoItem(final RepositorioItemGateway repositorioItemGateway) {
        return new ControladorItem(repositorioItemGateway);
    }

}
