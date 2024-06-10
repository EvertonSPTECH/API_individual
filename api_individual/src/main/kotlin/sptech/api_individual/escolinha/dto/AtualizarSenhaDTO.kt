package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank

data class AtualizarSenhaDTO (
    @field:NotBlank
    var senha:String
)