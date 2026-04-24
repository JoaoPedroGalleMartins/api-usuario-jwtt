package Login.usuario.exception;

import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsuarioNaoEncontrado.class)
    public ResponseEntity<String> usuarionotfound(UsuarioNaoEncontrado usuarioNaoEncontrado){
        return ResponseEntity.status(404).body(usuarioNaoEncontrado.getMessage());
    }

    @ExceptionHandler(SenhaInvalidaException.class)
    public ResponseEntity<String> senhainvalida(SenhaInvalidaException senhaInvalidaException){
        return ResponseEntity.status(400).body(senhaInvalidaException.getMessage());
    }
}
