package com.android.claudinei.crudandroidflavio;

import android.app.Activity;
import android.media.Image;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Flávio on 20/05/2016.
 */

public class ProdutosAdapter extends ArrayAdapter {
    // Lista de produtos
    private List<Produto> dados;
    private Activity contexto;
    // Posição de um elemento no ListView
    private int posicao;

    public ProdutosAdapter(Activity contexto,
                           List<Produto> dados) {
        super(contexto, R.layout.activity_main, dados);
        this.contexto = contexto;
        this.dados = dados;
    }

    // Desenha cada linha do ListView
    public View getView(int posicao, View convertView, ViewGroup parent) {
        this.posicao = posicao;
        // LayouInflater lê um XML e trás para o java
        LayoutInflater inflater = this.contexto.getLayoutInflater();
        View view = inflater.inflate(R.layout.linha_lista, null);
        // Carrega os elementos da tela
        TextView descricao = (TextView)view.findViewById(R.id.textView);
        descricao.setText(dados.get(posicao).getDescricao());
        // Botão Editar
        ImageButton imageButtonEdit = (ImageButton) view.findViewById(R.id.imageButton2);
        imageButtonEdit.setTag(dados.get(posicao).getId());
        // Botão Apagar
        ImageButton imageButtonDelete = (ImageButton) view.findViewById(R.id.imageButton3);
        imageButtonDelete.setTag(dados.get(posicao).getId());
        return view;
    }
}




