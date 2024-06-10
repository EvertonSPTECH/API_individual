package sptech.api_individual.escolinha.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sptech.api_individual.escolinha.domain.TipoTreino
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.service.TipoTreinoService

@RestController
@RequestMapping("/tiposTreino")
class TipoTreinoController(val tipoTreinoService: TipoTreinoService) {

    @PostMapping
    fun cadastrarTipoTreino(@RequestBody tipoTreinoCadastroDTO: TipoTreinoCadastroDTO): ResponseEntity<TipoTreino> {
        val tipoTreino = tipoTreinoService.cadastrarTipoTreino(tipoTreinoCadastroDTO)
        return ResponseEntity.status(201).body(tipoTreino)
    }

    @GetMapping
    fun listarTiposTreino(): ResponseEntity<List<TipoTreino>> {
        val lista = tipoTreinoService.listarTiposTreino()
        return if (lista.isNotEmpty()) {
            ResponseEntity.status(200).body(lista)
        } else {
            ResponseEntity.status(204).build()
        }
    }

    @GetMapping("/{idTipoTreino}")
    fun buscarTipoTreinoPorId(@PathVariable idTipoTreino: Int): ResponseEntity<TipoTreino> {
        val tipoTreino = tipoTreinoService.buscarTipoTreinoPorId(idTipoTreino)
        return tipoTreino?.let { ResponseEntity.status(200).body(it) } ?: ResponseEntity.status(404).build()
    }

    @PatchMapping("/{idTipoTreino}/nome")
    fun atualizarNomeTipoTreino(
        @PathVariable idTipoTreino: Int,
        @RequestBody atualizarNomeDTO: AtualizarNomeDTO
    ): ResponseEntity<TipoTreino> {
        val tipoTreino = tipoTreinoService.atualizarNomeTipoTreino(idTipoTreino, atualizarNomeDTO)
        return tipoTreino?.let { ResponseEntity.status(200).body(it) } ?: ResponseEntity.status(404).build()
    }

    @DeleteMapping("/{idTipoTreino}")
    fun deletarTipoTreino(@PathVariable idTipoTreino: Int): ResponseEntity<Void> {
        tipoTreinoService.deletarTipoTreino(idTipoTreino)
        return ResponseEntity.status(204).build()
    }
}