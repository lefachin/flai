/**
 * 
 */
package br.edu.unoesc.passagens.model;

import java.time.LocalDate;

/**
 * @author Fachin
 *
 */
public class Cliente {

	private String cpf, rg, passaporte, nome;
	private LocalDate data_nascimento;
	private Integer id, idade;

	/**
	 * 
	 */
	public Cliente() {
	}

	public Cliente(String cpf, String rg, String passaporte, String nome, LocalDate data_nascimento) {
		this.cpf = cpf;
		this.rg = rg;
		this.passaporte = passaporte;
		this.nome = nome;
		this.data_nascimento = data_nascimento;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getRg() {
		return rg;
	}

	public void setRg(String rg) {
		this.rg = rg;
	}

	public String getPassaporte() {
		return passaporte;
	}

	public void setPassaporte(String passaporte) {
		this.passaporte = passaporte;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public LocalDate getData_nascimento() {
		return data_nascimento;
	}

	public void setData_nascimento(LocalDate data_nascimento) {
		this.data_nascimento = data_nascimento;
	}
}
