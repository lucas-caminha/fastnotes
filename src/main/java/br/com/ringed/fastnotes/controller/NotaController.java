package br.com.ringed.fastnotes.controller;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.ringed.fastnotes.entity.Nota;
import br.com.ringed.fastnotes.repository.NotaRepository;

// A anotação @RestController permite definir um controller com características REST;
@RestController
public class NotaController {

	// A anotação @Autowired delega ao Spring Boot a inicialização do objeto;
	@Autowired
	private NotaRepository notaRepository;
	
	// A anotação @RequestMapping permite definir uma rota. Caso não seja informado o método HTTP da rota,
	// ela será definida para todos os métodos.
	@RequestMapping(value = "notas", method = RequestMethod.GET)
	public List<Nota> getNotas(){
		return notaRepository.findAll();
	}
	
	// A anotação @PathVariable indica que o valor da variável virá de uma informação da rota;
	@RequestMapping(value = "notas/{id}", method = RequestMethod.GET)
	public ResponseEntity<Nota> getNotaById(@PathVariable(value = "id") long id){
		
		Optional<Nota> nota = notaRepository.findById(id);
		if (nota.isPresent()) {
			return new ResponseEntity<Nota>(nota.get(), HttpStatus.OK);
		}
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
	// A anotação @RequestBody indica que o valor do objeto virá do corpo da requisição;
	// A anotação @Valid indica que os dados recebidos devem ser validados.
	@RequestMapping(value = "notas/add", method = RequestMethod.POST)
	public Nota add(@Valid @RequestBody Nota nota) {
		nota.setDataDeCriacao(LocalDate.now());
		return notaRepository.save(nota);
	}
	
	@RequestMapping(value = "notas/atualiza/{id}", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Nota> atualizaNota(@PathVariable("id") long id, @Valid @RequestBody Nota novaNota){
		Optional<Nota> notaEncontrada = notaRepository.findById(id);
		
		if (notaEncontrada.isPresent()) {
			Nota nota = notaEncontrada.get();
			nota.setNome(novaNota.getNome());
			nota.setDescricao(novaNota.getDescricao());
			notaRepository.save(nota);
			return new ResponseEntity<Nota>(nota, HttpStatus.OK);
		} 
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	@RequestMapping(value = "/notas/deleta/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Nota> deletaNota(@PathVariable("id") long id){
		
		Optional<Nota> notaEncontrada = notaRepository.findById(id);
		
		if (notaEncontrada.isPresent()) {
			notaRepository.delete(notaEncontrada.get());
			return new ResponseEntity<Nota>(HttpStatus.OK);
		}
		
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}
	
	
}
