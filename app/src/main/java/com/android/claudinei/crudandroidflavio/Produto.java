package com.android.claudinei.crudandroidflavio;

/**
 * Created by Flávio on 06/05/2016.
 */
public class Produto {
    private int id;
    private String descricao;
    private int estoque;
    private double preco;

    public Produto() {
    }

    public Produto(String descricao, int estoque, double preco) {
        this.descricao = descricao;
        this.estoque = estoque;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public int getEstoque() {
        return estoque;
    }

    public void setEstoque(int estoque) {
        this.estoque = estoque;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }
}
