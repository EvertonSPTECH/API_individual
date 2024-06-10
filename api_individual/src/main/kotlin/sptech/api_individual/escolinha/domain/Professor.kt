package sptech.api_individual.escolinha.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.Size

@Entity
data class Professor (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Int? = null,

    var nome:String? = null,

    @field:Email
    var email:String? = null,

    @field:Size(min = 11, max = 11)
    var telefoneCelular:String? = null,

    @field:Size(min = 8)
    var senha:String? = null,

    @field:ManyToOne
    var escolinha:Escolinha? = null
)