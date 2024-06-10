package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotNull

data class AtualizarNotaSessaoDTO(
    @field:NotNull
    var notaAluno: Int
)
