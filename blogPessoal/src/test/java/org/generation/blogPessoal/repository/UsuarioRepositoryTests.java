package org.generation.blogPessoal.repository;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.generation.blogPessoal.model.Usuario;
import org.generation.blogPessoal.service.UsuarioService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;


@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestInstance(TestInstance.Lifecycle.PER_CLASS) // faz testes classe a classe, fica mais fácil de identificar erros

public class  UsuarioRepositoryTests {
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@BeforeAll
	void start(){

		usuarioRepository.save(new Usuario(0L, "Ramon Daniel Santos", "ramonzito@hotmail.com","1223456789", "https://i.imgur.com/FETvs2O.jpg"));

		usuarioRepository.save(new Usuario(0L, "Robson Santos Carmo", "robsonbruxinho@carmo.com","magia1234", "https://i.imgur.com/FETvs2O.jpg"));
		
		usuarioRepository.save(new Usuario(0L, "Paola Bracho Santos", "ataldausurpadora@yahoo.com","CarlosDaniel", "https://i.imgur.com/FETvs2O.jpg"));
   }
	
	@Test
	@DisplayName("Retorna apenas um usuário")
	public void deveRetornarUmUsuario() {
		
		Optional<Usuario> usuario = usuarioRepository.findByUsuario("ramonzito@hotmail.com");
		assertTrue(usuario.get().getUsuario().equals("ramonzito@hotmail.com"));
	}
	

	@Test
	@DisplayName("Deve retornar três usuários")
	public void deveRetornarTresUsuarios() {
		
		List<Usuario> listaDeUsuarios = usuarioRepository.findAllByNomeContainingIgnoreCase("Santos");
		
		assertEquals(3, listaDeUsuarios.size());
		assertTrue(listaDeUsuarios.get(0).getNome().equals("Ramon Daniel Santos"));
		assertTrue(listaDeUsuarios.get(1).getNome().equals("Robson Santos Carmo"));	
		assertTrue(listaDeUsuarios.get(2).getNome().equals("Paola Bracho Santos"));	
	}
	

	@AfterAll
	public void end() {
		usuarioRepository.deleteAll();
    }
	
}