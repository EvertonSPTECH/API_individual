package sptech.api_individual.escolinha.repository

import org.springframework.data.jpa.repository.JpaRepository
import sptech.api_individual.escolinha.domain.Aluno

interface AlunoRepositorio:JpaRepository<Aluno, Int> {
}