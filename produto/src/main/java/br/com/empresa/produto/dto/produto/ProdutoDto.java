package br.com.empresa.produto.dto.produto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProdutoDto {

    @NotBlank
    @NotEmpty(message = "nome não pode ser vazio")
    private String nome;

    @NotBlank
    private String descricao;

    @NotNull
    @Positive(message = "preço não pode ser negativo")
    @JsonProperty("preço")
    private Integer valor;
}
