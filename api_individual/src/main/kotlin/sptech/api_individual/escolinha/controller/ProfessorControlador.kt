package sptech.api_individual.escolinha.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sptech.api_individual.escolinha.domain.Professor
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.service.ProfessorService

@RestController
@RequestMapping("/professores")
class ProfessorController(val professorService: ProfessorService) {

    @PostMapping
    fun cadastrarProfessor(@RequestBody professorCadastroDTO: ProfessorCadastroDTO): ResponseEntity<Professor> {
        val professor = professorService.cadastrarProfessor(professorCadastroDTO)
        return ResponseEntity.status(201).body(professor)
    }

    @GetMapping("/escolinha/{idEscolinha}")
    fun listarProfessoresPorEscolinha(@PathVariable idEscolinha: Int): ResponseEntity<List<Professor>> {
        val lista = professorService.listarProfessoresPorEscolinha(idEscolinha)
        return if(lista.isNotEmpty()){
            ResponseEntity.status(200).body(lista)
        } else {
            ResponseEntity.status(204).build()
        }
    }

    @GetMapping
    fun listarProfessores(): ResponseEntity<List<Professor>> {
        val lista = professorService.listarProfessores()
        return if(lista.isNotEmpty()){
            ResponseEntity.status(200).body(lista)
        } else {
            ResponseEntity.status(204).build()
        }
    }

    @GetMapping("/{idProfessor}")
    fun buscarProfessorPorId(@PathVariable idProfessor: Int): ResponseEntity<Professor> {
        val professor = professorService.buscarProfessorPorId(idProfessor)
        return if(professor != null){
            ResponseEntity.status(200).body(professor)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idProfessor}/email")
    fun atualizarEmailProfessor(
        @PathVariable idProfessor: Int,
        @RequestBody atualizarEmailDTO: AtualizarEmailDTO
    ): ResponseEntity<Professor> {
        val professor = professorService.atualizarEmailProfessor(idProfessor, atualizarEmailDTO)
        return if (professor != null){
            ResponseEntity.status(200).body(professor)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idProfessor}/telefone")
    fun atualizarTelefoneProfessor(
        @PathVariable idProfessor: Int,
        @RequestBody atualizarTelefoneDTO: AtualizarTelefoneDTO
    ): ResponseEntity<Professor> {
        val professor = professorService.atualizarTelefoneProfessor(idProfessor, atualizarTelefoneDTO)
        return if (professor != null){
            ResponseEntity.status(200).body(professor)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idProfessor}/nome")
    fun atualizarNomeProfessor(
        @PathVariable idProfessor: Int,
        @RequestBody atualizarNomeDTO: AtualizarNomeDTO
    ): ResponseEntity<Professor> {
        val professor = professorService.atualizarNomeProfessor(idProfessor, atualizarNomeDTO)
        return if (professor != null){
            ResponseEntity.status(200).body(professor)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idProfessor}/senha")
    fun atualizarSenhaProfessor(
        @PathVariable idProfessor: Int,
        @RequestBody atualizarSenhaDTO: AtualizarSenhaDTO
    ): ResponseEntity<Professor> {
        val professor = professorService.atualizarSenhaProfessor(idProfessor, atualizarSenhaDTO)
        return if (professor != null){
            ResponseEntity.status(200).body(professor)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{idProfessor}")
    fun deletarProfessor(@PathVariable idProfessor: Int): ResponseEntity<Void> {
        professorService.deletarProfessor(idProfessor)
        return ResponseEntity.status(204).build()
    }
}
