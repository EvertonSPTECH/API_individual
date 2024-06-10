package sptech.api_individual.escolinha.service

import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import sptech.api_individual.escolinha.domain.Professor
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.repository.EscolinhaRepositorio
import sptech.api_individual.escolinha.repository.ProfessorRepositorio
import java.util.*

class ProfessorService(
    val professorRepositorio: ProfessorRepositorio,
    val modelMapper: ModelMapper = ModelMapper()
) {
    @Transactional
    fun cadastrarProfessor(professorCadastroDTO: ProfessorCadastroDTO): Professor{
        val professor = modelMapper.map(professorCadastroDTO, Professor::class.java)

        professorRepositorio.save(professor)

        return professor
    }

    fun listarProfessoresPorEscolinha(idEscolinha:Int):List<Professor>{
        val lista = professorRepositorio.findByEscolinhaId(idEscolinha)

        return lista
    }

    fun listarProfessores():List<Professor>{
        val lista = professorRepositorio.findAll()

        return lista
    }

    fun buscarProfessorPorId(idProfessor:Int): Professor? {
        val professor = professorRepositorio.findById(idProfessor).orElse(null)

        return professor
    }

    fun atualizarEmailProfessor(idProfessor: Int, atualizarEmailDTO: AtualizarEmailDTO): Professor? {

        val professor = professorRepositorio.findById(idProfessor).orElse(null) ?: return null

        professor.email = atualizarEmailDTO.email

        return professorRepositorio.save(professor)
    }

    fun atualizarTelefoneProfessor(idProfessor: Int, atualizarTelefoneDTO: AtualizarTelefoneDTO): Professor? {

        val professor = professorRepositorio.findById(idProfessor).orElse(null) ?: return null

        professor.telefoneCelular = atualizarTelefoneDTO.telefoneCelular

        return professorRepositorio.save(professor)
    }

    fun atualizarNomeProfessor(idProfessor: Int, atualizarNomeDTO: AtualizarNomeDTO):Professor?{
        val professor = professorRepositorio.findById(idProfessor).orElse(null) ?: return null

        professor.nome = atualizarNomeDTO.nome

        return professorRepositorio.save(professor)
    }

    fun atualizarSenhaProfessor(idProfessor: Int, atualizarSenhaDTO: AtualizarSenhaDTO):Professor?{
        val professor = professorRepositorio.findById(idProfessor).orElse(null) ?: return null

        professor.senha = atualizarSenhaDTO.senha

        return professorRepositorio.save(professor)
    }

    fun deletarProfessor(idProfessor:Int){
        professorRepositorio.deleteById(idProfessor)
    }
}