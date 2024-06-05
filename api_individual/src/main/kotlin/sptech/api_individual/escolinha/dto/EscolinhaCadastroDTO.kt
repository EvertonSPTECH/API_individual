package sptech.api_individual.escolinha.dto

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class EscolinhaCadastroDTO (
    @field:NotBlank
    var nomeEscolinha:String,

    @field:NotBlank
    var email:String,

    @field:NotBlank
    var telefoneFixo:String,

    @field:NotBlank
    var telefoneCelular:String,

    @field:NotBlank
    @field:Size(min = 8, max = 8)
    val cep: String,

    @field:NotBlank
    val bairro: String,

    @field:NotBlank
    val cidade: String,

    @field:NotBlank
    val rua: String,

    @field:NotBlank
    val numero: String,

    val complemento: String?
){
}