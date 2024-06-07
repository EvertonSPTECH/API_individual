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
    // Marca o método como transacional, garantindo que todas as operações de banco de dados sejam realizadas em uma única transação
    @Transactional
    fun cadastrarEscolinha(escolinhaCadastroDTO: EscolinhaCadastroDTO): Escolinha {
        // Mapeia os dados do DTO para a entidade Escolinha usando o ModelMapper
        val escolinha = modelMapper.map(escolinhaCadastroDTO, Escolinha::class.java)
        // Salva a escolinha no repositório
        escolinhaRepositorio.save(escolinha)
        // Retorna a escolinha que foi cadastrada
        return escolinha
    }

    // Busca uma escolinha pelo seu ID. Retorna a escolinha encontrada ou null se não encontrar
    fun buscarPorId(idEscolinha: Int): Escolinha? {
        return escolinhaRepositorio.findById(idEscolinha).orElse(null)
    }

    // Busca todas as escolinhas e retorna uma lista com todas as escolinhas encontradas
    fun buscarTodas(): List<Escolinha> {
        return escolinhaRepositorio.findAll()
    }

    // Marca o método como transacional, garantindo que todas as operações de banco de dados sejam realizadas em uma única transação
    @Transactional
    fun atualizarNome(idEscolinha: Int, atualizarNomeDTO: AtualizarNomeDTO): Escolinha? {
        // Busca a escolinha pelo seu ID, se não encontrar retorna null
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null) ?: return null
        // Atualiza o nome da escolinha com o nome fornecido no DTO
        escolinha.nomeEscolinha = atualizarNomeDTO.nomeEscolinha
        // Salva a escolinha atualizada no repositório
        return escolinhaRepositorio.save(escolinha)
    }

    // Marca o método como transacional, garantindo que todas as operações de banco de dados sejam realizadas em uma única transação
    @Transactional
    fun atualizarEmail(idEscolinha: Int, atualizarEmailDTO: AtualizarEmailDTO): Escolinha? {
        // Busca a escolinha pelo seu ID, se não encontrar retorna null
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null) ?: return null
        // Atualiza o email da escolinha com o email fornecido no DTO
        escolinha.email = atualizarEmailDTO.email
        // Salva a escolinha atualizada no repositório
        return escolinhaRepositorio.save(escolinha)
    }

    // Marca o método como transacional, garantindo que todas as operações de banco de dados sejam realizadas em uma única transação
    @Transactional
    fun atualizarEndereco(idEscolinha: Int, atualizarEnderecoDTO: AtualizarEnderecoDTO): Escolinha? {
        // Busca a escolinha pelo seu ID, se não encontrar retorna null
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null) ?: return null
        // Mapeia o DTO para a entidade Endereco usando o ModelMapper
        val endereco = modelMapper.map(atualizarEnderecoDTO, Endereco::class.java)
        // Atualiza o endereço da escolinha com o endereço fornecido no DTO
        escolinha.endereco = endereco
        // Salva a escolinha atualizada no repositório
        return escolinhaRepositorio.save(escolinha)
    }

    // Marca o método como transacional, garantindo que todas as operações de banco de dados sejam realizadas em uma única transação
    @Transactional
    fun atualizarNumero(idEscolinha: Int, atualizarNumeroDTO: AtualizarNumeroDTO): Escolinha? {
        // Busca a escolinha pelo seu ID, se não encontrar retorna null
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null) ?: return null
        // Atualiza o número do endereço da escolinha com o número fornecido no DTO
        escolinha.endereco?.numero = atualizarNumeroDTO.numero
        // Salva a escolinha atualizada no repositório
        return escolinhaRepositorio.save(escolinha)
    }

    // Marca o método como transacional, garantindo que todas as operações de banco de dados sejam realizadas em uma única transação
    @Transactional
    fun atualizarComplemento(idEscolinha: Int, atualizarComplementoDTO: AtualizarComplementoDTO): Escolinha? {
        // Busca a escolinha pelo seu ID, se não encontrar retorna null
        val escolinha = escolinhaRepositorio.findById(idEscolinha).orElse(null) ?: return null
        // Atualiza o complemento do endereço da escolinha com o complemento fornecido no DTO
        escolinha.endereco?.complemento = atualizarComplementoDTO.complemento
        // Salva a escolinha atualizada no repositório
        return escolinhaRepositorio.save(escolinha)
    }

    // Marca o método como transacional, garantindo que todas as operações de banco de dados sejam realizadas em uma única transação
    @Transactional
    fun deletar(idEscolinha: Int) {
        // Deleta a escolinha pelo seu ID
        escolinhaRepositorio.deleteById(idEscolinha)
    }}