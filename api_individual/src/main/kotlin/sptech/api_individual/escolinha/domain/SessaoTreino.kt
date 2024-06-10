package sptech.api_individual.escolinha.domain

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
data class SessaoTreino(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idSessao: Int? = null,

    var data: LocalDateTime? = null,

    var notaAluno: Int? = null,

    @field:ManyToOne
    var aluno: Aluno? = null,

    @field:ManyToOne
    var treino: Treino? = null
)
