package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank

data class AtualizarPosicaoDTO (
    @field:NotBlank
    var posicao:String
)