package sptech.api_individual.escolinha.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Past
import java.util.Date

@Entity
data class Aluno(
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idAluno: Int? = null,

    var nomeAluno: String? = null,

    @field:Email
    var emailAluno: String? = null,

    var telefoneAluno: String? = null,

    @field:Past
    var dataNasc: Date? = null,

    var posicao: String? = null,

    var senha: String? = null,

    @field:ManyToOne
    var escolinha: Escolinha? = null
)
