package br.com.alfac.foodproduto.infra.persistence;

import java.io.Serializable;
import java.math.BigDecimal;

import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBAttribute;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBHashKey;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTable;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBTypeConvertedEnum;

import br.com.alfac.foodproduto.core.domain.CategoriaItem;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@DynamoDBTable(tableName = "food_produto")
public class ItemEntity implements Serializable {

    @DynamoDBHashKey(attributeName = "id")
    private Long id;

    @DynamoDBAttribute(attributeName = "nome")
    private String nome;

    @DynamoDBAttribute(attributeName="preco")
    private BigDecimal preco;

    @DynamoDBAttribute(attributeName = "categoria")
    // @DynamoDBTypeConvertedJson(targetType = CategoriaItem.class)
    @DynamoDBTypeConvertedEnum
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