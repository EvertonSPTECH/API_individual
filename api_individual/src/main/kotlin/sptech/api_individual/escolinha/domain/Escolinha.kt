package sptech.api_individual.escolinha.domain

import jakarta.persistence.*
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

@Entity
data class Escolinha (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var idEscolinha:Int? = null,

    @field:Size(min = 3, max = 45)
    var nomeEscolinha:String? = null,

    @field:Email
    var email:String? = null,

    @field:Size(min = 10, max = 10)
    var telefoneFixo:String? = null,

    @field:Size(min = 11, max = 11)
    var telefoneCelular:String? = null,

    @field:Embedded
    var endereco: Endereco? = null
){
}