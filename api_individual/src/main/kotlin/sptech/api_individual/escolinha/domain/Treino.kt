package sptech.api_individual.escolinha.domain

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id

@Entity
data class Treino (
    @field:Id @field:GeneratedValue(strategy = GenerationType.IDENTITY)
    var id:Int? = null,

    var descricao:String? = null
)