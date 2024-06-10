package sptech.api_individual.escolinha.service

import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import sptech.api_individual.escolinha.domain.Treino
import sptech.api_individual.escolinha.dto.AtualizarNomeDTO
import sptech.api_individual.escolinha.dto.TreinoCadastroDTO
import sptech.api_individual.escolinha.repository.TipoTreinoRepositorio
import sptech.api_individual.escolinha.repository.TreinoRepositorio

@Service
class TreinoService(
    val treinoRepositorio: TreinoRepositorio,
    val modelMapper: ModelMapper = ModelMapper()
) {

    @Transactional
    fun cadastrarTreino(treinoCadastroDTO: TreinoCadastroDTO): Treino {
        val treino = modelMapper.map(treinoCadastroDTO, Treino::class.java)
        treinoRepositorio.save(treino)
        return treino
    }

    fun listarTreinos(): List<Treino> {
        return treinoRepositorio.findAll()
    }

    fun buscarTreinoPorId(idTreino: Int): Treino? {
        return treinoRepositorio.findById(idTreino).orElse(null)
    }

    fun atualizarDescricaoTreino(idTreino: Int, atualizarDescricaoDTO: AtualizarNomeDTO): Treino? {
        val treino = treinoRepositorio.findById(idTreino).orElse(null) ?: return null
        treino.descricao = atualizarDescricaoDTO.nome
        return treinoRepositorio.save(treino)
    }

    fun deletarTreino(idTreino: Int) {
        treinoRepositorio.deleteById(idTreino)
    }
}