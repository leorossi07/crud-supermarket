package br.ufscar.dc.dsw.domain;

public class Compra {

	private Long id;
	private String data;
	private Float valor;
	private Produto produto;
	private Usuario usuario;

	public Compra(Long id, String data, Float valor, Produto produto, Usuario usuario) {
		this.id = id;
		this.data = data;
		this.valor = valor;
		this.produto = produto;
		this.usuario = usuario;
	}

	public Compra(String data, Float valor, Produto produto, Usuario usuario) {
		super();
		this.data = data;
		this.valor = valor;
		this.produto = produto;
		this.usuario = usuario;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Float getValor() {
		return valor;
	}

	public void setValor(Float valor) {
		this.valor = valor;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}