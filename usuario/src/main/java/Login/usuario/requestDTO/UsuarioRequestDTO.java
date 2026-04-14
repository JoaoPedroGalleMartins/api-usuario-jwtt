package Login.usuario.requestDTO;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UsuarioRequestDTO {

    @NotBlank(message = "nao pode estar em branco")
    private String name;

    @NotBlank(message = "nao pode estar em branco")
    @NotEmpty(message = "email nao pode estar em branco")
    @Email(message = "digite o email corretamente")
    private String email;

    @NotBlank(message = "nao pode estar em branco")
    private String password;
}
