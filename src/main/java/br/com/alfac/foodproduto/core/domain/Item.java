package br.com.alfac.foodproduto.core.domain;

import java.math.BigDecimal;
import java.util.Objects;
import java.math.RoundingMode;

public class Item {
    private String nome;
    private BigDecimal preco;
    private CategoriaItem categoria;
    private Long id;

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
        BigDecimal preco = null;
        if (Objects.nonNull(this.preco)) {
            preco = this.preco.setScale(2, RoundingMode.HALF_UP);
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