package br.com.arqdev.pessoa.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "telefone")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Telefone {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Integer id;

    private String numero;

    @ManyToOne
    @JoinColumn(name="pessoa_id", referencedColumnName="id")
    private Pessoa pessoa;
}
