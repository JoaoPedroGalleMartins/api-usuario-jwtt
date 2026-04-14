package Login.usuario.service;

import Login.usuario.model.Usuario;
import Login.usuario.repository.UsuarioRepository;
import Login.usuario.requestDTO.LoginRequestDTO;
import Login.usuario.requestDTO.UsuarioRequestDTO;
import Login.usuario.responseDTO.LoginResponseDTO;
import Login.usuario.responseDTO.UsuarioResponseDTO;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.Arrays.stream;

@Service
public class UsuarioService {
    public final UsuarioRepository usuarioRepository;

    public final PasswordEncoder passwordEncoder;

    public UsuarioService(UsuarioRepository usuarioRepository, PasswordEncoder passwordEncoder) {
        this.usuarioRepository = usuarioRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public UsuarioResponseDTO toDTO(Usuario usuario){
        UsuarioResponseDTO usuarioReponseDTO = new UsuarioResponseDTO();

        usuarioReponseDTO.setId(usuario.getId());
        usuarioReponseDTO.setEmail(usuario.getEmail());
        usuarioReponseDTO.setName(usuario.getName());

        return usuarioReponseDTO;
    }

    public Usuario toEntity(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = new Usuario();
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setName(usuarioRequestDTO.getName());
        usuario.setPassword(passwordEncoder.encode(usuarioRequestDTO.getPassword()));
        return usuario;

    }

    public void updateEntity( Usuario usuario, UsuarioRequestDTO usuarioRequestDTO){
        usuario.setName(usuarioRequestDTO.getName());
        usuario.setEmail(usuarioRequestDTO.getEmail());
        usuario.setPassword(passwordEncoder.encode(usuarioRequestDTO.getPassword()));
    }

    public UsuarioResponseDTO criarusuario(UsuarioRequestDTO usuarioRequestDTO){
        Usuario usuario = toEntity(usuarioRequestDTO);

        Usuario salvo = usuarioRepository.save(usuario);

        return toDTO(salvo);
    }

    public UsuarioResponseDTO modificarusuario(UsuarioRequestDTO usuarioRequestDTO, Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

            updateEntity(usuario, usuarioRequestDTO);

            Usuario salvo = usuarioRepository.save(usuario);

            return toDTO(salvo);
    }

    public List<UsuarioResponseDTO> listarusuario(){
        return usuarioRepository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }

    public UsuarioResponseDTO buscarporid(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        return toDTO(usuario);
    }

    public void deletarusuario(Long id){
        Usuario usuario = usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        usuarioRepository.delete(usuario);
    }

    public LoginResponseDTO login(LoginRequestDTO loginRequestDTO){
        Usuario usuario = usuarioRepository.findByEmail(loginRequestDTO.getEmail())
                .orElseThrow(() -> new RuntimeException("Usuario nao encontrado"));

        if (!passwordEncoder.matches(loginRequestDTO.getPassword(), usuario.getPassword())){
            throw new RuntimeException("Senha inválida");
        }

        String token = new tokenService().gerarToken(usuario.getEmail());


        return new LoginResponseDTO(usuario.getEmail(), token);
    }
}
