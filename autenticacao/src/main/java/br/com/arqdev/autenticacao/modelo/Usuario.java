package br.com.arqdev.autenticacao.modelo;

import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import br.com.arqdev.pessoa.entity.Pessoa;

@Entity
@Table(name = "usuario")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Usuario {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    private String login;

    private String email;

    private String senha;

    @OneToOne(mappedBy = "usuario")
    private Pessoa pessoa;

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioPerfil> usuarioPerfis;

    public List<Perfil> getPerfis() {
        return usuarioPerfis.stream().map(up -> up.getPerfil()).collect(Collectors.toList());
    }
}
