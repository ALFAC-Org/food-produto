package br.com.alfac.food.core.application.item.dto;

import br.com.alfac.food.core.domain.item.CategoriaItem;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Objects;


public class ItemDTO {
    private Long id;
    private String nome;
    private BigDecimal preco;
    private CategoriaItem categoria;

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {

        if (Objects.nonNull(preco)) {
            return preco.setScale(2, RoundingMode.HALF_UP);
        }
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public CategoriaItem getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaItem categoria) {
        this.categoria = categoria;
    }
}