package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank

data class AtualizarNomeDTO(
    @field:NotBlank
    val nomeEscolinha: String
)