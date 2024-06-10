package sptech.api_individual.escolinha.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sptech.api_individual.escolinha.domain.SessaoTreino
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.service.SessaoTreinoService

@RestController
@RequestMapping("/sessoes-treino")
class SessaoTreinoController(val sessaoTreinoService: SessaoTreinoService) {

    @PostMapping
    fun cadastrarSessaoTreino(@RequestBody sessaoTreinoCadastroDTO: SessaoTreinoCadastroDTO): ResponseEntity<SessaoTreino> {
        val sessaoTreino = sessaoTreinoService.cadastrarSessaoTreino(sessaoTreinoCadastroDTO)
        return ResponseEntity.status(201).body(sessaoTreino)
    }

    @GetMapping
    fun listarSessoesTreino(): ResponseEntity<List<SessaoTreino>> {
        val lista = sessaoTreinoService.listarSessoesTreino()
        return if (lista.isNotEmpty()) {
            ResponseEntity.status(200).body(lista)
        } else {
            ResponseEntity.status(204).build()
        }
    }

    @GetMapping("/{idSessao}")
    fun buscarSessaoTreinoPorId(@PathVariable idSessao: Int): ResponseEntity<SessaoTreino> {
        val sessaoTreino = sessaoTreinoService.buscarSessaoTreinoPorId(idSessao)
        return if (sessaoTreino != null) {
            ResponseEntity.status(200).body(sessaoTreino)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idSessao}/data")
    fun atualizarDataSessao(@PathVariable idSessao: Int, @RequestBody atualizarDataSessaoDTO: AtualizarDataSessaoDTO): ResponseEntity<SessaoTreino> {
        val sessaoTreino = sessaoTreinoService.atualizarDataSessao(idSessao, atualizarDataSessaoDTO)
        return if (sessaoTreino != null) {
            ResponseEntity.status(200).body(sessaoTreino)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idSessao}/nota")
    fun atualizarNotaSessao(@PathVariable idSessao: Int, @RequestBody atualizarNotaSessaoDTO: AtualizarNotaSessaoDTO): ResponseEntity<SessaoTreino> {
        val sessaoTreino = sessaoTreinoService.atualizarNotaSessao(idSessao, atualizarNotaSessaoDTO)
        return if (sessaoTreino != null) {
            ResponseEntity.status(200).body(sessaoTreino)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{idSessao}")
    fun deletarSessaoTreino(@PathVariable idSessao: Int): ResponseEntity<Void> {
        sessaoTreinoService.deletarSessaoTreino(idSessao)
        return ResponseEntity.status(204).build()
    }
}
