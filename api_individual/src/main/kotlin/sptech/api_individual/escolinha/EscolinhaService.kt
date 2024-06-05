package sptech.api_individual.escolinha

import jakarta.transaction.Transactional
import org.modelmapper.ModelMapper
import org.springframework.stereotype.Service
import sptech.api_individual.escolinha.domain.Endereco
import sptech.api_individual.escolinha.domain.Escolinha
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.repository.EscolinhaRepositorio

@Service
class EscolinhaService (
    val escolinhaRepositorio: EscolinhaRepositorio,
    val modelMapper:ModelMapper = ModelMapper()
){
    @Transactional
    fun cadastrarEscolinha(escolinhaCadastroDTO: EscolinhaCadastroDTO): Escolinha {
        val escolinha = modelMapper.map(escolinhaCadastroDTO, Escolinha::class.java)
        escolinhaRepositorio.save(escolinha)
        return escolinha
    }

    fun buscarPorId(idEscolinha: Int): Escolinha? {
        return escolinhaRepositorio.findById(idEscolinha).orElse(null)
    }

    fun buscarTodas():List<Escolinha>{
        return escolinhaRepositorio.findAll()
    }

    @Transactional
    fun atualizarNome(idEscolinha: Int, atualizarNomeDTO: AtualizarNomeDTO):Escolinha?{
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null)?:return null
        escolinha.nomeEscolinha = atualizarNomeDTO.nomeEscolinha
        return escolinhaRepositorio.save(escolinha)
    }

    @Transactional
    fun atualizarEmail(idEscolinha: Int, atualizarEmailDTO: AtualizarEmailDTO):Escolinha?{
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null)?:return null
        escolinha.email = atualizarEmailDTO.email
        return escolinhaRepositorio.save(escolinha)
    }

    @Transactional
    fun atualizarEndereco(idEscolinha: Int, atualizarEnderecoDTO: AtualizarEnderecoDTO): Escolinha? {
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null) ?: return null
        val endereco = Endereco(
            cep = atualizarEnderecoDTO.cep,
            bairro = atualizarEnderecoDTO.bairro,
            cidade = atualizarEnderecoDTO.cidade,
            rua = atualizarEnderecoDTO.rua,
            numero = atualizarEnderecoDTO.numero,
            complemento = atualizarEnderecoDTO.complemento
        )
        escolinha.endereco = endereco
        return escolinhaRepositorio.save(escolinha)
    }

    @Transactional
    fun atualizarNumero(idEscolinha: Int, atualizarNumeroDTO: AtualizarNumeroDTO): Escolinha? {
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null) ?: return null
        escolinha.endereco?.numero = atualizarNumeroDTO.numero
        return escolinhaRepositorio.save(escolinha)
    }

    @Transactional
    fun atualizarComplemento(idEscolinha: Int, atualizarComplementoDTO: AtualizarComplementoDTO): Escolinha? {
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null) ?: return null
        escolinha.endereco?.complemento = atualizarComplementoDTO.complemento
        return escolinhaRepositorio.save(escolinha)
    }

    @Transactional
    fun deletar(idEscolinha: Int) {
        escolinhaRepositorio.deleteById(idEscolinha)
    }
}