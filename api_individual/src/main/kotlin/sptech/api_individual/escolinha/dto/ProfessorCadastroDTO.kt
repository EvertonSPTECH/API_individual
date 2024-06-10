package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import sptech.api_individual.escolinha.domain.Escolinha

data class ProfessorCadastroDTO (
    @field:NotBlank
    var nome:String,

    @field:NotBlank
    var email:String,

    @field:NotBlank
    var telefoneCelular:String,

    @field:NotBlank
    var senha:String,

    @field:NotNull
    var escolinha:Escolinha
)