package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank

data class TreinoCadastroDTO (
    @field:NotBlank
    var descricao:String
)