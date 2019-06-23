package br.com.arqdev.repository;

import static junit.framework.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.arqdev.entity.Pessoa;
import br.com.arqdev.entity.Telefone;


@RunWith(SpringRunner.class)
@SpringBootTest
public class PessoaRepositoryTest {
	
	@Autowired
	private PessoaRepository repository;
	
	@Before
	public void setUp() {
		Telefone telefone = Telefone.builder()
				.numero("61934567654")
				.build();
		
		Pessoa pessoa = Pessoa.builder()
				.nome("fulano")
				.dataNascimento(LocalDate.of(1992, 9, 19))
				.telefones(Collections.singletonList(telefone))
				.build();
		
		telefone.setPessoa(pessoa);
		
		repository.save(pessoa);
	}
	
	@After
	public void tearDown() {
		repository.deleteAll();
	}
	
	@Test
	public void deveAcharPessoaFulano() {
		Optional<Pessoa> pessoaOptional = repository.findById(1);
		
		boolean isPresent = pessoaOptional.isPresent();
		
		assertTrue(isPresent);
		pessoaOptional.ifPresent(p -> {
			assertEquals("fulano", p.getNome());
			assertEquals(LocalDate.of(1992, 9, 19), p.getDataNascimento());
			assertEquals(1, p.getTelefones().size());
			assertEquals("61934567654", p.getTelefones().get(0).getNumero());
		});
	}
	
	@Test
	public void deveAcharTodasPessoas() {
		List<Pessoa> pessoas = repository.findAll();
		
		assertEquals(1, pessoas.size());
		
		Pessoa p = pessoas.get(0);
		
		assertEquals("fulano", p.getNome());
		assertEquals(LocalDate.of(1992, 9, 19), p.getDataNascimento());
		assertEquals(1, p.getTelefones().size());
		assertEquals("61934567654", p.getTelefones().get(0).getNumero());
	}
}
