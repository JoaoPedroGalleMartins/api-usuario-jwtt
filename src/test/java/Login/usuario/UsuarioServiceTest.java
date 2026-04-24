package Login.usuario;

import Login.usuario.exception.UsuarioNaoEncontrado;
import Login.usuario.model.Usuario;
import Login.usuario.repository.UsuarioRepository;
import Login.usuario.requestDTO.UsuarioRequestDTO;
import Login.usuario.responseDTO.UsuarioResponseDTO;
import Login.usuario.service.UsuarioService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*;

import org.mockito.Mock;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setup(){
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void criarUsuario(){
        UsuarioRequestDTO request = new UsuarioRequestDTO();
        request.setEmail("joao@email.com");
        request.setName("Joao");
        request.setPassword("123");

        Usuario usuariosalvo = new Usuario();
        usuariosalvo.setEmail("joao@email.com");
        usuariosalvo.setName("Joao");
        usuariosalvo.setId(1L);

        when(passwordEncoder.encode("123")).thenReturn("Senha cripto");
        when(usuarioRepository.save(any(Usuario.class))).thenReturn(usuariosalvo);

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.criarusuario(request);

        assertNotNull(usuarioResponseDTO);
        assertEquals("joao@email.com", usuarioResponseDTO.getEmail());
        verify(usuarioRepository).save(any());
    }

    @Test
    void BuscarPorUsuario(){
        Usuario usuario = new Usuario();
        usuario.setEmail("joao@email.com");
        usuario.setName("Joao");
        usuario.setId(1L);

        when(usuarioRepository.findById(1L))
                .thenReturn(java.util.Optional.of(usuario));

        UsuarioResponseDTO usuarioResponseDTO = usuarioService.buscarporid(1L);

        assertNotNull(usuarioResponseDTO);
        assertEquals(1L, usuarioResponseDTO.getId());
        assertEquals("joao@email.com", usuarioResponseDTO.getEmail());
        assertEquals("Joao", usuarioResponseDTO.getName());

    }

    @Test
    void LancarUsuarionaoEncontradoException(){
        when(usuarioRepository.findById(1L))
                .thenReturn(java.util.Optional.empty());

        assertThrows(UsuarioNaoEncontrado.class, () -> {
            usuarioService.buscarporid(1L);
        });

        verify(usuarioRepository).findById(1L);
    }
}
