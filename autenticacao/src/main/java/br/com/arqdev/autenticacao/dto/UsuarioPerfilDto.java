package br.com.arqdev.autenticacao.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioPerfilDto {

    private Integer id;

    private UsuarioDto usuario;

    private PerfilDto perfil;
}
