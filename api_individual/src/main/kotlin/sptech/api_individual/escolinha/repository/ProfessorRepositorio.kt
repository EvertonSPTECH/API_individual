package sptech.api_individual.escolinha.repository

import org.springframework.data.jpa.repository.JpaRepository
import sptech.api_individual.escolinha.domain.Professor

interface ProfessorRepositorio:JpaRepository<Professor, Int> {

    fun findByEscolinhaId(idEscolinha:Int):List<Professor>

}