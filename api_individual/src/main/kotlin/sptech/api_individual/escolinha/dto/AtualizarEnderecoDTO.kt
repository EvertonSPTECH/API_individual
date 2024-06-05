package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class AtualizarEnderecoDTO(
    @field:NotBlank
    @field:Size(min = 8, max = 8)
    val cep: String,

    @field:NotBlank
    val bairro: String,

    @field:NotBlank
    val cidade: String,

    @field:NotBlank
    val rua: String,

    @field:NotBlank
    val numero: String,

    val complemento: String?
)