package br.com.alfac.food.infra.item.persistence;

import br.com.alfac.food.core.domain.item.CategoriaItem;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "item")
public class ItemEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty(message = "Nome do item é obrigatório")
    private String nome;

    @NotNull(message = "Preço do item é obrigatório")
    private BigDecimal preco;

    @Enumerated(EnumType.STRING)
    @NotNull(message = "Categoria do item é obrigatório")
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

    public void setNome(final String nome) {
        this.nome = nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(final BigDecimal preco) {
        this.preco = preco;
    }

    public CategoriaItem getCategoria() {
        return categoria;
    }

    public void setCategoria(final CategoriaItem categoria) {
        this.categoria = categoria;
    }
}