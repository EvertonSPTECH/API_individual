package sptech.api_individual.escolinha.service

import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import sptech.api_individual.escolinha.domain.TipoTreino
import sptech.api_individual.escolinha.dto.AtualizarNomeDTO
import sptech.api_individual.escolinha.dto.TipoTreinoCadastroDTO
import sptech.api_individual.escolinha.repository.TipoTreinoRepositorio

class TipoTreinoService (
    val tipoTreinoRepositorio: TipoTreinoRepositorio,
    val modelMapper: ModelMapper = ModelMapper()
){
    @Transactional
    fun cadastrarTipoTreino(tipoTreinoCadastroDTO: TipoTreinoCadastroDTO): TipoTreino {
        val tipoTreino = modelMapper.map(tipoTreinoCadastroDTO, TipoTreino::class.java)

        tipoTreinoRepositorio.save(tipoTreino)

        return tipoTreino
    }

    fun listarTiposTreino():List<TipoTreino> {
        return tipoTreinoRepositorio.findAll()
    }

    fun buscarTipoTreinoPorId(idTipoTreino:Int): TipoTreino? {
        return tipoTreinoRepositorio.findById(idTipoTreino).orElse(null)
    }

    fun atualizarNomeTipoTreino(idTipoTreino: Int, atualizarNomeDTO: AtualizarNomeDTO): TipoTreino? {
        val tipoTreino = tipoTreinoRepositorio.findById(idTipoTreino).orElse(null) ?: return null
        tipoTreino.nome = atualizarNomeDTO.nome
        return tipoTreinoRepositorio.save(tipoTreino)
    }

    fun deletarTipoTreino(idTipoTreino:Int) {
        tipoTreinoRepositorio.deleteById(idTipoTreino)
    }
}