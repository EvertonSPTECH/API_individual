package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class AtualizarEmailDTO(
    @field:NotBlank
    @field:Email
    val email: String
)