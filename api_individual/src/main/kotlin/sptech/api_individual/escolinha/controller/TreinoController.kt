package sptech.api_individual.escolinha.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sptech.api_individual.escolinha.domain.Treino
import sptech.api_individual.escolinha.dto.AtualizarNomeDTO
import sptech.api_individual.escolinha.dto.TreinoCadastroDTO
import sptech.api_individual.escolinha.service.TreinoService

@RestController
@RequestMapping("/treinos")
class TreinoController(val treinoService: TreinoService) {

    @PostMapping
    fun cadastrarTreino(@RequestBody treinoCadastroDTO: TreinoCadastroDTO): ResponseEntity<Treino> {
        val treino = treinoService.cadastrarTreino(treinoCadastroDTO)
        return ResponseEntity.status(201).body(treino)
    }

    @GetMapping
    fun listarTreinos(): ResponseEntity<List<Treino>> {
        val lista = treinoService.listarTreinos()
        return if (lista.isNotEmpty()) {
            ResponseEntity.status(200).body(lista)
        } else {
            ResponseEntity.status(204).build()
        }
    }

    @GetMapping("/{idTreino}")
    fun buscarTreinoPorId(@PathVariable idTreino: Int): ResponseEntity<Treino> {
        val treino = treinoService.buscarTreinoPorId(idTreino)
        return if (treino != null) {
            ResponseEntity.status(200).body(treino)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idTreino}/descricao")
    fun atualizarDescricaoTreino(
        @PathVariable idTreino: Int,
        @RequestBody atualizarDescricaoDTO: AtualizarNomeDTO
    ): ResponseEntity<Treino> {
        val treino = treinoService.atualizarDescricaoTreino(idTreino, atualizarDescricaoDTO)
        return if (treino != null) {
            ResponseEntity.status(200).body(treino)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{idTreino}")
    fun deletarTreino(@PathVariable idTreino: Int): ResponseEntity<Void> {
        treinoService.deletarTreino(idTreino)
        return ResponseEntity.status(204).build()
    }
}
