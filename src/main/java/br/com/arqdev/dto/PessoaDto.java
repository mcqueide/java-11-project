package br.com.arqdev.dto;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PessoaDto {

	private Integer id;
	private String nome;
	@JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataNascimento;
	private List<TelefoneDto> telefones;
}
