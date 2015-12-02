/**
 * 
 */
package br.edu.unoesc.passagens.model;

/**
 * Classe responsável pelo funcionário que irá realizar as operações no sistema (essencialmente vendas).
 * @author Fachin
 *
 */
public class Vendedor {

	private String nome, usuario, senha;
	private Integer id;
	
	/**
	 * Contrutor vazio.
	 */
	public Vendedor() {
	}

	/**
	 * Contrutor recebendo como parâmetros o nome do vendedor, um usuário e uma senha para acesso ao sistema.
	 * @param nome Nome do funcionário.
	 * @param usuario Nome usado para o acesso ao sistema.
	 * @param senha Senha usada para o acesso ao sistema.
	 * @author Fachin
	 */
	public Vendedor(String nome, String usuario, String senha) {
		this.nome = nome;
		this.usuario = usuario;
		this.senha = senha;
	}

	public Vendedor(String nome) {
		this.nome = nome;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
}
