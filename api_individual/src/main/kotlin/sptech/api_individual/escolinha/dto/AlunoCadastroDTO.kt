package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.util.Date

data class AlunoCadastroDTO(
    @field:NotBlank
    var nomeAluno: String,

    @field:NotBlank
    var emailAluno: String,

    @field:NotBlank
    @field:Size(min= 11, max = 11)
    var telefoneAluno: String,

    @field:NotNull
    var dataNasc: Date,

    @field:NotBlank
    @field:Size(max = 45)
    var posicao: String,

    @field:NotBlank
    @field:Size(min = 8, max = 8)
    var senha: String,

    var fkEscolinha: Int
)
