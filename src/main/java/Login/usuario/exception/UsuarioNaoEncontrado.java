package Login.usuario.exception;

public class UsuarioNaoEncontrado extends RuntimeException {
    public UsuarioNaoEncontrado(){
        super("Usuario nao encontrado");
    }
}
