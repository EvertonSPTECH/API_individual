package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.FutureOrPresent
import jakarta.validation.constraints.NotNull
import java.time.LocalDateTime

data class SessaoTreinoCadastroDTO(
    @field:NotNull
    @field:FutureOrPresent
    var data: LocalDateTime,

    @field:NotNull
    var notaAluno: Int,

    @field:NotNull
    var idAluno: Int,

    @field:NotNull
    var idTreino: Int
)
