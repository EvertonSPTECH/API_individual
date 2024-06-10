package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class AtualizarDataSessaoDTO(
    @field:NotNull
    @field:FutureOrPresent
    var data: LocalDateTime
)
