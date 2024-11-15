package br.com.alfac.foodproduto.core.application.dto;

import br.com.alfac.foodproduto.core.domain.item.CategoriaItem;

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

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemDTO other = (ItemDTO) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (nome == null) {
            if (other.nome != null)
                return false;
        } else if (!nome.equals(other.nome))
            return false;
        if (preco == null) {
            if (other.preco != null)
                return false;
        } else if (!preco.equals(other.preco))
            return false;
        if (categoria != other.categoria)
            return false;
        return true;
    }
    
}