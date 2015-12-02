package br.edu.unoesc.passagens.model;

import java.time.LocalDate;

public class Passagem {

	private Cliente cliente;
	private Vendedor vendedor;
	private Linha linha;
	private Cidade origem;
	private Cidade destino;
	private LocalDate data_venda;
	private LocalDate data_viagem;
	private Integer poltrona;
	private Integer numero;

	public Passagem() {
	}

	public Passagem(Cliente cliente, Vendedor vendedor, Linha linha, Cidade origem, Cidade destino,
			LocalDate data_venda, LocalDate data_viagem, Integer poltrona) {
		this.cliente = cliente;
		this.vendedor = vendedor;
		this.linha = linha;
		this.origem = origem;
		this.destino = destino;
		this.data_venda = data_venda;
		this.data_viagem = data_viagem;
		this.poltrona = poltrona;
	}
	
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Vendedor getVendedor() {
		return vendedor;
	}

	public void setVendedor(Vendedor vendedor) {
		this.vendedor = vendedor;
	}

	public Linha getLinha() {
		return linha;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	public Cidade getOrigem() {
		return origem;
	}

	public void setOrigem(Cidade origem) {
		this.origem = origem;
	}

	public Cidade getDestino() {
		return destino;
	}

	public void setDestino(Cidade destino) {
		this.destino = destino;
	}

	public LocalDate getData_venda() {
		return data_venda;
	}

	public void setData_venda(LocalDate data_venda) {
		this.data_venda = data_venda;
	}

	public LocalDate getData_viagem() {
		return data_viagem;
	}

	public void setData_viagem(LocalDate data_viagem) {
		this.data_viagem = data_viagem;
	}

	public Integer getPoltrona() {
		return poltrona;
	}

	public void setPoltrona(Integer poltrona) {
		this.poltrona = poltrona;
	}
}
