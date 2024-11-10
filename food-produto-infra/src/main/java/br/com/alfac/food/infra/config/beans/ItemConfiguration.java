package br.com.alfac.food.infra.config.beans;

import br.com.alfac.food.core.application.item.adapters.controller.ControladorItem;
import br.com.alfac.food.core.application.item.adapters.gateways.RepositorioItemGateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ItemConfiguration {

    @Bean
    public ControladorItem controladoItem(final RepositorioItemGateway repositorioItemGateway) {
        return new ControladorItem(repositorioItemGateway);
    }

}
