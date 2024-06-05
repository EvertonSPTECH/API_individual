package sptech.api_individual.escolinha.domain

import jakarta.persistence.Embeddable
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

@Embeddable
data class Endereco(
    @field:Size(min = 8, max = 8)
    var cep: String? = null,

    @field:NotBlank
    var bairro: String? = null,

    @field:NotBlank
    var cidade: String? = null,

    @field:NotBlank
    var rua: String? = null,

    @field:NotBlank
    var numero: String? = null,

    var complemento: String? = null
) {
}