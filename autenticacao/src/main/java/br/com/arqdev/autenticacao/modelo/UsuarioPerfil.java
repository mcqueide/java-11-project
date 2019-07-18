package br.com.arqdev.autenticacao.modelo;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "usuario_perfil")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioPerfil {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Perfil perfil;
}
