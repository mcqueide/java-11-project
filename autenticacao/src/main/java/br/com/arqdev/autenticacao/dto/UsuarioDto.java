package br.com.arqdev.autenticacao.dto;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioDto {

    private Integer id;

    private String login;

    private String email;

    private String senha;

    private Set<UsuarioPerfilDto> usuarioPerfis;

    public List<PerfilDto> getPerfis() {
        return usuarioPerfis.stream().map(up -> up.getPerfil()).collect(Collectors.toList());
    }
}
