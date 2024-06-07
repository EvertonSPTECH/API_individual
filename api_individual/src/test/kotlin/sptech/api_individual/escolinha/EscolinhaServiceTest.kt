package sptech.api_individual.escolinha

import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DisplayName
import org.mockito.ArgumentMatchers.any
import org.mockito.Mockito.*
import org.modelmapper.ModelMapper
import org.springframework.boot.test.context.SpringBootTest
import sptech.api_individual.escolinha.domain.Endereco
import sptech.api_individual.escolinha.domain.Escolinha
import sptech.api_individual.escolinha.dto.*
import sptech.api_individual.escolinha.repository.EscolinhaRepositorio

@SpringBootTest
class EscolinhaServiceTest {

    lateinit var escolinhaServico: EscolinhaService
    lateinit var escolinhaRepositorio: EscolinhaRepositorio
    lateinit var modelMapper: ModelMapper

    @BeforeEach
    fun iniciar() {
        escolinhaRepositorio = mock(EscolinhaRepositorio::class.java)
        modelMapper = ModelMapper()
        escolinhaServico = EscolinhaService(escolinhaRepositorio, modelMapper)
    }

    @Test
    fun cadastrarEscolinha_deveCadastrarComSucesso() {
        // Dados do DTO para cadastrar uma escolinha
        val escolinhaCadastroDTO = EscolinhaCadastroDTO(
            nomeEscolinha = "Escola ABC",
            email = "escolaabc@example.com",
            telefoneFixo = "123456789",
            telefoneCelular = "987654321",
            cep = "12345678",
            bairro = "Centro",
            cidade = "Cidade",
            rua = "Rua A",
            numero = "123",
            complemento = "Bloco B"
        )

        // Mock do objeto Escolinha criado a partir do DTO
        val escolinha = modelMapper.map(escolinhaCadastroDTO, Escolinha::class.java)

        // Configura o comportamento do mock escolinhaRepositorio para o método save.
        // Quando o método save do escolinhaRepositorio for chamado com qualquer objeto do tipo Escolinha,
        // ele deve retornar o próprio objeto Escolinha que foi passado para ele.
        `when`(escolinhaRepositorio.save(any(Escolinha::class.java))).thenAnswer { it.getArgument(0) }

        // Chama a função cadastrarEscolinha do serviço com o DTO fornecido
        val escolinhaCadastrada = escolinhaServico.cadastrarEscolinha(escolinhaCadastroDTO)

        // Verifica se a escolinha retornada pelo serviço é a mesma que foi criada a partir do DTO
        assertEquals(escolinha, escolinhaCadastrada)
    }

    @Test
    @DisplayName("Deve encontrar uma escolinha pelo ID")
    fun buscarPorId_escolinhaEncontrada() {
        // Dados de exemplo de uma escolinha
        val idEscolinha = 1
        val escolinha = Escolinha(
            idEscolinha = idEscolinha,
            nomeEscolinha = "Escola ABC",
            email = "escola@example.com",
            telefoneFixo = "123456789",
            telefoneCelular = "987654321",
            Endereco(
                cep = "12345-678",
                bairro = "Centro",
                cidade = "São Paulo",
                rua = "Rua Principal",
                numero = "123",
                complemento = "Sala 101"
            )
        )

        // Simula o repositório retornando a escolinha com o ID especificado
        `when`(escolinhaRepositorio.findById(idEscolinha)).thenReturn(java.util.Optional.of(escolinha))

        // Chama a função para buscar a escolinha pelo ID
        val resultado = escolinhaServico.buscarPorId(idEscolinha)

        // Verifica se a escolinha retornada é a mesma que foi simulada no repositório
        assertEquals(escolinha, resultado)
    }

    @Test
    @DisplayName("Deve retornar null quando não encontrar uma escolinha pelo ID")
    fun buscarPorId_escolinhaNaoEncontrada() {
        // ID de uma escolinha que não existe
        val idEscolinha = 999

        // Simula o repositório não encontrando a escolinha com o ID especificado
        `when`(escolinhaRepositorio.findById(idEscolinha)).thenReturn(java.util.Optional.empty())

        // Chama a função para buscar a escolinha pelo ID
        val resultado = escolinhaServico.buscarPorId(idEscolinha)

        // Verifica se o resultado é nulo, indicando que a escolinha não foi encontrada
        assertEquals(null, resultado)
    }

    @Test
    @DisplayName("Deve listar todas as escolinhas corretamente")
    fun listarTodasEscolinhas_sucesso() {
        // Dados de exemplo de duas escolinhas
        val escolinha1 = Escolinha(
            idEscolinha = 1,
            nomeEscolinha = "Escola ABC",
            email = "escola@example.com",
            telefoneFixo = "123456789",
            telefoneCelular = "987654321",
            Endereco(
                cep = "12345-678",
                bairro = "Centro",
                cidade = "São Paulo",
                rua = "Rua Principal",
                numero = "123",
                complemento = "Sala 101"
            )
        )
        val escolinha2 = Escolinha(
            idEscolinha = 2,
            nomeEscolinha = "Escola XYZ",
            email = "escola2@example.com",
            telefoneFixo = "987654321",
            telefoneCelular = "123456789",
            Endereco(
                cep = "54321-876",
                bairro = "Bela Vista",
                cidade = "Rio de Janeiro",
                rua = "Avenida Secundária",
                numero = "456",
                complemento = null
            )
        )
        val listaEscolinhas = listOf(escolinha1, escolinha2)

        // Simula o repositório retornando todas as escolinhas
        `when`(escolinhaRepositorio.findAll()).thenReturn(listaEscolinhas)

        // Chama a função para listar todas as escolinhas
        val resultado = escolinhaServico.buscarTodas()

        // Verifica se a lista retornada contém as duas escolinhas de exemplo
        assertEquals(listaEscolinhas.size, resultado.size)
        assertEquals(listaEscolinhas, resultado)
    }

    @Test
    @DisplayName("Deve retornar uma lista vazia quando não houver escolinhas cadastradas")
    fun listarTodasEscolinhas_semEscolinhas() {
        // Simula o repositório retornando uma lista vazia
        `when`(escolinhaRepositorio.findAll()).thenReturn(emptyList())

        // Chama a função para listar todas as escolinhas
        val resultado = escolinhaServico.buscarTodas()

        // Verifica se a lista retornada está vazia
        assertEquals(0, resultado.size)
    }

    @Test
    @DisplayName("Deve atualizar o nome de uma escolinha corretamente")
    fun atualizarNome() {
        // Dados de exemplo
        val idEscolinha = 1
        val novoNome = "Nova Escola ABC"
        val atualizarNomeDTO = AtualizarNomeDTO(novoNome)

        // Escolinha existente
        val escolinha = Escolinha(idEscolinha = idEscolinha, nomeEscolinha = novoNome)

        // Simulação do repositório
        `when`(escolinhaRepositorio.findById(idEscolinha)).thenReturn(java.util.Optional.of(escolinha))
        `when`(escolinhaRepositorio.save(any())).thenAnswer { it.arguments[0] as Escolinha }

        // Chamada da função a ser testada
        val resultado = escolinhaServico.atualizarNome(idEscolinha, atualizarNomeDTO)

        // Verificações
        assertEquals(novoNome, resultado?.nomeEscolinha)
        verify(escolinhaRepositorio).findById(idEscolinha)
        verify(escolinhaRepositorio).save(any())
        verifyNoMoreInteractions(escolinhaRepositorio)
    }

    @Test
    @DisplayName("Deve atualizar o email de uma escolinha corretamente")
    fun atualizarEmail() {
        // Dados de exemplo
        val idEscolinha = 1
        val novoEmail = "nova_escola@example.com"
        val atualizarEmailDTO = AtualizarEmailDTO(novoEmail)

        // Escolinha existente
        val escolinha = Escolinha(idEscolinha = idEscolinha, email = "escola@example.com")

        // Simulação do repositório
        `when`(escolinhaRepositorio.findById(idEscolinha)).thenReturn(java.util.Optional.of(escolinha))
        `when`(escolinhaRepositorio.save(any())).thenAnswer { it.arguments[0] as Escolinha }

        // Chamada da função a ser testada
        val resultado = escolinhaServico.atualizarEmail(idEscolinha, atualizarEmailDTO)

        // Verificações
        assertEquals(novoEmail, resultado?.email)
        verify(escolinhaRepositorio).findById(idEscolinha)
        verify(escolinhaRepositorio).save(any())
        verifyNoMoreInteractions(escolinhaRepositorio)
    }

    @Test
    @DisplayName("Deve atualizar o endereço de uma escolinha corretamente")
    fun atualizarEndereco() {
        // Dados de exemplo
        val idEscolinha = 1
        val novoEnderecoDTO = AtualizarEnderecoDTO(
            cep = "12345-678",
            bairro = "Novo Bairro",
            cidade = "Nova Cidade",
            rua = "Nova Rua",
            numero = "1234",
            complemento = "Novo Complemento"
        )

        // Escolinha existente
        val escolinha = Escolinha(idEscolinha = idEscolinha, endereco = Endereco())

        // Simulação do repositório
        `when`(escolinhaRepositorio.findById(idEscolinha)).thenReturn(java.util.Optional.of(escolinha))
        `when`(escolinhaRepositorio.save(any())).thenAnswer { it.arguments[0] as Escolinha }

        // Chamada da função a ser testada
        val resultado = escolinhaServico.atualizarEndereco(idEscolinha, novoEnderecoDTO)

        // Verificações
        assertEquals(novoEnderecoDTO.cep, resultado?.endereco?.cep)
        assertEquals(novoEnderecoDTO.bairro, resultado?.endereco?.bairro)
        assertEquals(novoEnderecoDTO.cidade, resultado?.endereco?.cidade)
        assertEquals(novoEnderecoDTO.rua, resultado?.endereco?.rua)
        assertEquals(novoEnderecoDTO.numero, resultado?.endereco?.numero)
        assertEquals(novoEnderecoDTO.complemento, resultado?.endereco?.complemento)
        verify(escolinhaRepositorio).findById(idEscolinha)
        verify(escolinhaRepositorio).save(any())
        verifyNoMoreInteractions(escolinhaRepositorio)
    }

    @Test
    @DisplayName("Deve atualizar o número de uma escolinha corretamente")
    fun atualizarNumero() {
        // Dados de exemplo
        val idEscolinha = 1
        val novoNumeroDTO = AtualizarNumeroDTO(numero = "1234")

        // Escolinha existente
        val escolinha = Escolinha(idEscolinha = idEscolinha, endereco = Endereco(numero = "5678"))

        // Simulação do repositório
        `when`(escolinhaRepositorio.findById(idEscolinha)).thenReturn(java.util.Optional.of(escolinha))
        `when`(escolinhaRepositorio.save(any())).thenAnswer { it.arguments[0] as Escolinha }

        // Chamada da função a ser testada
        val resultado = escolinhaServico.atualizarNumero(idEscolinha, novoNumeroDTO)

        // Verificações
        assertEquals(novoNumeroDTO.numero, resultado?.endereco?.numero)
        verify(escolinhaRepositorio).findById(idEscolinha)
        verify(escolinhaRepositorio).save(any())
        verifyNoMoreInteractions(escolinhaRepositorio)
    }

    @Test
    @DisplayName("Deve atualizar o complemento de uma escolinha corretamente")
    fun atualizarComplemento() {
        // Dados de exemplo
        val idEscolinha = 1
        val novoComplementoDTO = AtualizarComplementoDTO(complemento = "Sala 101")

        // Escolinha existente
        val escolinha = Escolinha(idEscolinha = idEscolinha, endereco = Endereco(complemento = "Sala 102"))

        // Simulação do repositório
        `when`(escolinhaRepositorio.findById(idEscolinha)).thenReturn(java.util.Optional.of(escolinha))
        `when`(escolinhaRepositorio.save(any())).thenAnswer { it.arguments[0] as Escolinha }

        // Chamada da função a ser testada
        val resultado = escolinhaServico.atualizarComplemento(idEscolinha, novoComplementoDTO)

        // Verificações
        assertEquals(novoComplementoDTO.complemento, resultado?.endereco?.complemento)
        verify(escolinhaRepositorio).findById(idEscolinha)
        verify(escolinhaRepositorio).save(any())
        verifyNoMoreInteractions(escolinhaRepositorio)
    }

    // Teste para verificar a exclusão de uma escolinha
    @Test
    @DisplayName("Deve deletar uma escolinha corretamente")
    fun deletar() {
        // Dados de exemplo
        val idEscolinha = 1

        // Chamada da função a ser testada
        escolinhaServico.deletar(idEscolinha)

        // Verificações
        verify(escolinhaRepositorio).deleteById(idEscolinha)
        verifyNoMoreInteractions(escolinhaRepositorio)
    }
}