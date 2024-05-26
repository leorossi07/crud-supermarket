package br.ufscar.dc.dsw.domain;

public class Fornecedor {

    private Long id;
    private String CNPJ;
    private String nome;
    private int qtdeProdutos;

    public Fornecedor(Long id) {
        this.id = id;
    }

    public Fornecedor(String CNPJ, String nome) {
        this.CNPJ = CNPJ;
        this.nome = nome;
    }

    public Fornecedor(Long id, String CNPJ, String nome) {
        this(CNPJ, nome);
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCNPJ() {
        return CNPJ;
    }

    public void setCNPJ(String CNPJ) {
        this.CNPJ = CNPJ;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQtdeProdutos() {
        return qtdeProdutos;
    }

    public void setQtdeProdutos(int qtdeProdutos) {
        this.qtdeProdutos = qtdeProdutos;
    }
}