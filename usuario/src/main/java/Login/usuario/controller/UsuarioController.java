package Login.usuario.controller;

import Login.usuario.repository.UsuarioRepository;
import Login.usuario.requestDTO.LoginRequestDTO;
import Login.usuario.requestDTO.UsuarioRequestDTO;
import Login.usuario.responseDTO.LoginResponseDTO;
import Login.usuario.responseDTO.UsuarioResponseDTO;
import Login.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {

    private final UsuarioService usuarioService;

    public UsuarioController(UsuarioService usuarioService) {
        this.usuarioService = usuarioService;
    }

    @PostMapping
    public UsuarioResponseDTO criar(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO){return usuarioService.criarusuario(usuarioRequestDTO);}

    @PostMapping("/login")
    public LoginResponseDTO login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        return usuarioService.login(loginRequestDTO);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO modificarusuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO, @PathVariable Long id)
    {return usuarioService.modificarusuario(usuarioRequestDTO, id);}

    @GetMapping
    public List<UsuarioResponseDTO> list(){return usuarioService.listarusuario();}
    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarporid(@PathVariable Long id){return usuarioService.buscarporid(id);}

    @DeleteMapping("/{id}")
    public void deletarusuario(@PathVariable Long id){usuarioService.deletarusuario(id);}
}
