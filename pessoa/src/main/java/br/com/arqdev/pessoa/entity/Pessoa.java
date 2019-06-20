package br.com.arqdev.pessoa.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pessoa")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Pessoa {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	
	private String nome;
	
	@Column(name="data_nascimento")
	private LocalDate dataNascimento;
	
	@OneToMany(mappedBy="pessoa", cascade=CascadeType.ALL, fetch = FetchType.EAGER)
	private List<Telefone> telefones;
	
}
