package com.android.claudinei.crudandroidflavio;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    private ProdutosAdapter prodAdap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView lista = (ListView)
                findViewById(R.id.listView);
        ProdutosDAO db = new ProdutosDAO(this);
        prodAdap = new ProdutosAdapter(
                this, db.getTodosProdutos());
        lista.setAdapter(prodAdap);
        ImageButton botaoAdd = (ImageButton)
                findViewById(R.id.imageButton);
        botaoAdd.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ProdutosDAO db = new ProdutosDAO(
                                getApplicationContext());
                        db.addProduto(
                                new Produto("Arroz", 90, 2.70));
                        prodAdap.clear();
                        prodAdap.addAll(db.getTodosProdutos());
                    }
                });
    }
}
