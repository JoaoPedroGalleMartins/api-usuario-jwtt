package Login.usuario.controller;


import Login.usuario.requestDTO.LoginRequestDTO;
import Login.usuario.requestDTO.UsuarioRequestDTO;
import Login.usuario.responseDTO.LoginResponseDTO;
import Login.usuario.responseDTO.UsuarioResponseDTO;
import Login.usuario.service.UsuarioService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UsuarioResponseDTO> criar(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO){
        UsuarioResponseDTO usuarioResponseDTO = usuarioService.criarusuario(usuarioRequestDTO);
        return ResponseEntity.status(201).body(usuarioResponseDTO);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponseDTO> login(@RequestBody @Valid LoginRequestDTO loginRequestDTO){
        LoginResponseDTO loginResponseDTO = usuarioService.login(loginRequestDTO);
        return ResponseEntity.ok(loginResponseDTO);
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO modificarusuario(@RequestBody @Valid UsuarioRequestDTO usuarioRequestDTO, @PathVariable Long id)
    {return usuarioService.modificarusuario(usuarioRequestDTO, id);}

    @GetMapping
    public List<UsuarioResponseDTO> list(){return usuarioService.listarusuario();}

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioResponseDTO> buscarporid(@PathVariable Long id){return ResponseEntity.ok(usuarioService.buscarporid(id));}

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarusuario(@PathVariable Long id){
        usuarioService.deletarusuario(id);
        return ResponseEntity.noContent().build();}
}
