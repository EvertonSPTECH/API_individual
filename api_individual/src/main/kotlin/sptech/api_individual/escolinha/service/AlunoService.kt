package sptech.api_individual.escolinha.service

import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import sptech.api_individual.escolinha.domain.Aluno
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.repository.AlunoRepositorio
import sptech.api_individual.escolinha.repository.EscolinhaRepositorio

@Service
class AlunoService(
    val alunoRepositorio: AlunoRepositorio,
    val escolinhaRepositorio: EscolinhaRepositorio,
    val modelMapper: ModelMapper = ModelMapper()
) {
    @Transactional
    fun cadastrarAluno(alunoCadastroDTO: AlunoCadastroDTO): Aluno {
        val escolinha = escolinhaRepositorio.findById(alunoCadastroDTO.fkEscolinha).orElseThrow {
            RuntimeException("Escolinha não encontrada")
        }

        val aluno = modelMapper.map(alunoCadastroDTO, Aluno::class.java)
        aluno.escolinha = escolinha

        alunoRepositorio.save(aluno)
        return aluno
    }

    fun listarAlunos(): List<Aluno> {
        return alunoRepositorio.findAll()
    }

    fun buscarAlunoPorId(idAluno: Int): Aluno? {
        return alunoRepositorio.findById(idAluno).orElse(null)
    }

    fun atualizarNomeAluno(idAluno: Int, atualizarNomeDTO: AtualizarNomeDTO): Aluno? {
        val aluno = alunoRepositorio.findById(idAluno).orElse(null) ?: return null
        aluno.nomeAluno = atualizarNomeDTO.nome
        return alunoRepositorio.save(aluno)
    }

    fun atualizarEmailAluno(idAluno: Int, atualizarEmailDTO: AtualizarEmailDTO): Aluno? {
        val aluno = alunoRepositorio.findById(idAluno).orElse(null) ?: return null
        aluno.emailAluno = atualizarEmailDTO.email
        return alunoRepositorio.save(aluno)
    }

    fun atualizarTelefoneAluno(idAluno: Int, atualizarTelefoneDTO: AtualizarTelefoneDTO): Aluno? {
        val aluno = alunoRepositorio.findById(idAluno).orElse(null) ?: return null
        aluno.telefoneAluno = atualizarTelefoneDTO.telefoneCelular
        return alunoRepositorio.save(aluno)
    }

    fun atualizarPosicaoAluno(idAluno: Int, atualizarPosicaoDTO: AtualizarPosicaoDTO): Aluno? {
        val aluno = alunoRepositorio.findById(idAluno).orElse(null) ?: return null
        aluno.posicao = atualizarPosicaoDTO.posicao
        return alunoRepositorio.save(aluno)
    }

    fun atualizarSenhaAluno(idAluno: Int, atualizarSenhaDTO: AtualizarSenhaDTO): Aluno? {
        val aluno = alunoRepositorio.findById(idAluno).orElse(null) ?: return null
        aluno.senha = atualizarSenhaDTO.senha
        return alunoRepositorio.save(aluno)
    }

    fun deletarAluno(idAluno: Int) {
        alunoRepositorio.deleteById(idAluno)
    }
}
