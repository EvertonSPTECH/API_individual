package sptech.api_individual.escolinha.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sptech.api_individual.escolinha.domain.Aluno
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.service.AlunoService

@RestController
@RequestMapping("/alunos")
class AlunoController(val alunoService: AlunoService) {

    @PostMapping
    fun cadastrarAluno(@RequestBody alunoCadastroDTO: AlunoCadastroDTO): ResponseEntity<Aluno> {
        val aluno = alunoService.cadastrarAluno(alunoCadastroDTO)
        return ResponseEntity.status(201).body(aluno)
    }

    @GetMapping
    fun listarAlunos(): ResponseEntity<List<Aluno>> {
        val lista = alunoService.listarAlunos()
        return if (lista.isNotEmpty()) {
            ResponseEntity.status(200).body(lista)
        } else {
            ResponseEntity.status(204).build()
        }
    }

    @GetMapping("/{idAluno}")
    fun buscarAlunoPorId(@PathVariable idAluno: Int): ResponseEntity<Aluno> {
        val aluno = alunoService.buscarAlunoPorId(idAluno)
        return if (aluno != null) {
            ResponseEntity.status(200).body(aluno)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idAluno}/nome")
    fun atualizarNomeAluno(@PathVariable idAluno: Int, @RequestBody atualizarNomeDTO: AtualizarNomeDTO): ResponseEntity<Aluno> {
        val aluno = alunoService.atualizarNomeAluno(idAluno, atualizarNomeDTO)
        return if (aluno != null) {
            ResponseEntity.status(200).body(aluno)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idAluno}/email")
    fun atualizarEmailAluno(@PathVariable idAluno: Int, @RequestBody atualizarEmailDTO: AtualizarEmailDTO): ResponseEntity<Aluno> {
        val aluno = alunoService.atualizarEmailAluno(idAluno, atualizarEmailDTO)
        return if (aluno != null) {
            ResponseEntity.status(200).body(aluno)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idAluno}/telefone")
    fun atualizarTelefoneAluno(@PathVariable idAluno: Int, @RequestBody atualizarTelefoneDTO: AtualizarTelefoneDTO): ResponseEntity<Aluno> {
        val aluno = alunoService.atualizarTelefoneAluno(idAluno, atualizarTelefoneDTO)
        return if (aluno != null) {
            ResponseEntity.status(200).body(aluno)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idAluno}/posicao")
    fun atualizarPosicaoAluno(@PathVariable idAluno: Int, @RequestBody atualizarPosicaoDTO: AtualizarPosicaoDTO): ResponseEntity<Aluno> {
        val aluno = alunoService.atualizarPosicaoAluno(idAluno, atualizarPosicaoDTO)
        return if (aluno != null) {
            ResponseEntity.status(200).body(aluno)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idAluno}/senha")
    fun atualizarSenhaAluno(@PathVariable idAluno: Int, @RequestBody atualizarSenhaDTO: AtualizarSenhaDTO): ResponseEntity<Aluno> {
        val aluno = alunoService.atualizarSenhaAluno(idAluno, atualizarSenhaDTO)
        return if (aluno != null) {
            ResponseEntity.status(200).body(aluno)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{idAluno}")
    fun deletarAluno(@PathVariable idAluno: Int): ResponseEntity<Void> {
        alunoService.deletarAluno(idAluno)
        return ResponseEntity.status(204).build()
    }
}
