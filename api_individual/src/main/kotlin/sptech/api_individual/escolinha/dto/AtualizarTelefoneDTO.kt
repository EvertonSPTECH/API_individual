package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank

data class AtualizarTelefoneDTO (
    @field:NotBlank
    var telefoneCelular:String
)