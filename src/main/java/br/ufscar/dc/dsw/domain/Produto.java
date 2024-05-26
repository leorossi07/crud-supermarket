package br.ufscar.dc.dsw.domain;

public class Produto {

    private Long id;
    private String titulo;
    private String categoria;
    private Integer quantidade;
    private Float preco;
    private Fornecedor fornecedor;

    public Produto(Long id) {
        this.id = id;
    }

    public Produto(String titulo, String categoria, Integer quantidade, Float preco,
            Fornecedor fornecedor) {
        this.titulo = titulo;
        this.categoria = categoria;
        this.quantidade = quantidade;
        this.preco = preco;
        this.fornecedor = fornecedor;
    }

    public Produto(Long id, String titulo, String categoria, Integer quantidade, 
            Float preco, Fornecedor fornecedor) {
        this(titulo, categoria, quantidade, preco, fornecedor);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Float getPreco() {
        return preco;
    }

    public void setPreco(Float preco) {
        this.preco = preco;
    }

    public Fornecedor getFornecedor() {
        return fornecedor;
    }

    public void setFornecedor(Fornecedor fornecedor) {
        this.fornecedor = fornecedor;
    }
    
    @Override
    public String toString() {
    	return titulo + ", " + categoria + "(" + preco + ")"; 
    }
}