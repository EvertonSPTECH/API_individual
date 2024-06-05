package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank

data class AtualizarNumeroDTO(
    @field:NotBlank
    val numero: String
)