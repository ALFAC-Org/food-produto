package br.com.alfac.food.infra.item.dto;

import br.com.alfac.food.core.domain.item.CategoriaItem;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class ItemRequest {

    @Schema(example = "Hamburguer")
    @NotEmpty(message = "Nome é obrigatório.")
    private String nome;

    @Schema(example = "11.50")
    @NotNull(message = "Preço é obrigatório.")
    private Double preco;

    @Schema(example = "LANCHE")
    @NotNull(message = "Categoria é obrigatória.")
    private CategoriaItem categoria;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public CategoriaItem getCategoria() {
        return categoria;
    }

    public void setCategoria(CategoriaItem categoria) {
        this.categoria = categoria;
    }
}