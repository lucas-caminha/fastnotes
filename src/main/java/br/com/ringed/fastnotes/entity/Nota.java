package br.com.ringed.fastnotes.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.springframework.lang.Nullable;

@Entity
public class Nota {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(nullable = false)
	private String nome;
	private String descricao;
	@Nullable
	private LocalDate dataDeCriacao;	
	
	public Nota(Long id, String nome, String descricao, LocalDate dataDeCriacao) {
		this.id = id;
		this.nome = nome;
		this.descricao = descricao;
		this.dataDeCriacao = dataDeCriacao;
	}
		
	public Nota() {
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getDescricao() {
		return descricao;
	}
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	public LocalDate getDataDeCriacao() {
		return dataDeCriacao;
	}
	public void setDataDeCriacao(LocalDate dataDeCriacao) {
		this.dataDeCriacao = dataDeCriacao;
	}
	
	
	
	
}
