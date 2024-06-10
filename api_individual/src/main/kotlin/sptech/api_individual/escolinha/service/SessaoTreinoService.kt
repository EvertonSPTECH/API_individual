package sptech.api_individual.escolinha.service

import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import sptech.api_individual.escolinha.domain.SessaoTreino
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.repository.AlunoRepositorio
import sptech.api_individual.escolinha.repository.SessaoTreinoRepositorio
import sptech.api_individual.escolinha.repository.TreinoRepositorio

@Service
class SessaoTreinoService(
    val sessaoTreinoRepositorio: SessaoTreinoRepositorio,
    val alunoRepositorio: AlunoRepositorio,
    val treinoRepositorio: TreinoRepositorio,
    val modelMapper: ModelMapper = ModelMapper()
) {
    @Transactional
    fun cadastrarSessaoTreino(sessaoTreinoCadastroDTO: SessaoTreinoCadastroDTO): SessaoTreino {
        val aluno = alunoRepositorio.findById(sessaoTreinoCadastroDTO.idAluno).orElseThrow {
            RuntimeException("Aluno não encontrado")
        }
        val treino = treinoRepositorio.findById(sessaoTreinoCadastroDTO.idTreino).orElseThrow {
            RuntimeException("Treino não encontrado")
        }

        val sessaoTreino = modelMapper.map(sessaoTreinoCadastroDTO, SessaoTreino::class.java)
        sessaoTreino.aluno = aluno
        sessaoTreino.treino = treino

        sessaoTreinoRepositorio.save(sessaoTreino)
        return sessaoTreino
    }

    fun listarSessoesTreino(): List<SessaoTreino> {
        return sessaoTreinoRepositorio.findAll()
    }

    fun buscarSessaoTreinoPorId(idSessao: Int): SessaoTreino? {
        return sessaoTreinoRepositorio.findById(idSessao).orElse(null)
    }

    fun atualizarDataSessao(idSessao: Int, atualizarDataSessaoDTO: AtualizarDataSessaoDTO): SessaoTreino? {
        val sessaoTreino = sessaoTreinoRepositorio.findById(idSessao).orElse(null) ?: return null
        sessaoTreino.data = atualizarDataSessaoDTO.data
        return sessaoTreinoRepositorio.save(sessaoTreino)
    }

    fun atualizarNotaSessao(idSessao: Int, atualizarNotaSessaoDTO: AtualizarNotaSessaoDTO): SessaoTreino? {
        val sessaoTreino = sessaoTreinoRepositorio.findById(idSessao).orElse(null) ?: return null
        sessaoTreino.notaAluno = atualizarNotaSessaoDTO.notaAluno
        return sessaoTreinoRepositorio.save(sessaoTreino)
    }

    fun deletarSessaoTreino(idSessao: Int) {
        sessaoTreinoRepositorio.deleteById(idSessao)
    }
}
