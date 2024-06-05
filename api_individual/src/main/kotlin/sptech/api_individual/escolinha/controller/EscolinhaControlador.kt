package sptech.api_individual.escolinha.controller

import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import sptech.api_individual.escolinha.EscolinhaService
import sptech.api_individual.escolinha.domain.Escolinha
import sptech.api_individual.escolinha.dto.*
import java.util.Optional

@RestController
@RequestMapping("/escolinhas")
class EscolinhaControlador(
    val escolinhaServico:EscolinhaService
) {
    @PostMapping
    fun cadastrar(@RequestBody escolinhaCadastro:EscolinhaCadastroDTO):ResponseEntity<Escolinha>{
        val escolinha = escolinhaServico.cadastrar(escolinhaCadastro)
        return ResponseEntity.status(201).body(escolinha)
    }

    @GetMapping("/{idEscolinha}")
    fun buscarPorId(@PathVariable idEscolinha:Int):ResponseEntity<Escolinha>{
        val escolinha = escolinhaServico.buscarPorId(idEscolinha)
        return if (escolinha != null){
            ResponseEntity.status(200).body(escolinha)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @GetMapping
    fun buscarTodas():ResponseEntity<List<Escolinha>>{
        val lista = escolinhaServico.buscarTodas()
        return if(lista.isNotEmpty()){
            ResponseEntity.status(200).body(lista)
        } else {
            ResponseEntity.status(204).build()
        }
    }

    @PatchMapping("/{idEscolinha}/nome")
    fun atualizarNome(@PathVariable idEscolinha:Int, @RequestBody atualizarNome:AtualizarNomeDTO):ResponseEntity<Escolinha>{
        val escolinhaAtualizada = escolinhaServico.atualizarNome(idEscolinha, atualizarNome)
        return if (escolinhaAtualizada != null){
            ResponseEntity.status(200).body(escolinhaAtualizada)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idEscolinha}/email")
    fun atualizarEmail(@PathVariable idEscolinha:Int, @RequestBody atualizarEmail:AtualizarEmailDTO):ResponseEntity<Escolinha>{
        val escolinhaAtualizada = escolinhaServico.atualizarEmail(idEscolinha, atualizarEmail)
        return if (escolinhaAtualizada != null){
            ResponseEntity.status(200).body(escolinhaAtualizada)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idEscolinha}/endereco")
    fun atualizarEndereco(@PathVariable idEscolinha: Int, @RequestBody atualizarEndereco: AtualizarEnderecoDTO): ResponseEntity<Escolinha> {
        val escolinhaAtualizada = escolinhaServico.atualizarEndereco(idEscolinha, atualizarEndereco)
        return if (escolinhaAtualizada != null) {
            ResponseEntity.status(200).body(escolinhaAtualizada)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idEscolinha}/numero")
    fun atualizarNumero(@PathVariable idEscolinha: Int, @RequestBody atualizarNumero:AtualizarNumeroDTO): ResponseEntity<Escolinha> {
        val escolinhaAtualizada = escolinhaServico.atualizarNumero(idEscolinha, atualizarNumero)
        return if (escolinhaAtualizada != null) {
            ResponseEntity.status(200).body(escolinhaAtualizada)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @PatchMapping("/{idEscolinha}/complemento")
    fun atualizarComplemento(@PathVariable idEscolinha: Int, @RequestBody atualizarComplemento: AtualizarComplementoDTO): ResponseEntity<Escolinha> {
        val escolinhaAtualizada = escolinhaServico.atualizarComplemento(idEscolinha, atualizarComplemento)
        return if (escolinhaAtualizada != null) {
            ResponseEntity.status(200).body(escolinhaAtualizada)
        } else {
            ResponseEntity.status(404).build()
        }
    }

    @DeleteMapping("/{idEscolinha}")
    fun deletar(@PathVariable idEscolinha: Int): ResponseEntity<Void> {
        return if (escolinhaServico.buscarPorId(idEscolinha) != null) {
            escolinhaServico.deletar(idEscolinha)
            ResponseEntity.status(204).build()
        } else {
            ResponseEntity.status(404).build()
        }
    }
}