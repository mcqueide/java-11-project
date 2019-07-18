package br.com.arqdev.autenticacao.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PerfilDto {

    private Integer id;

    private String nome;

    private List<UsuarioPerfilDto> usuarioPerfil;
}
