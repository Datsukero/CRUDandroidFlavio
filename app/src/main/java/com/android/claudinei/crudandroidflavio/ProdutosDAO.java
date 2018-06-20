package com.android.claudinei.crudandroidflavio;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by Flávio on 06/05/2016.
 */
public class ProdutosDAO
        extends SQLiteOpenHelper {
    // Versão do banco
    private static final int DATABASE_VERSION = 1;
    // Nome do banco
    private static final String
            DATABASE_NAME = "Sistema";

    public ProdutosDAO(Context context) {
        // Métodos construtor da superclasse
        super(context, DATABASE_NAME,
                null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db) {
        String CREATE_PRODUTOS =
                "CREATE TABLE produtos ( " +
                        "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        "descricao TEXT, " +
                        "estoque INTEGER, " +
                        "preco DOUBLE )";
        db.execSQL(CREATE_PRODUTOS);
    }

    public void onUpgrade(SQLiteDatabase db,
                          int oldVersion, int newVersion) {

    }

    private static final String TABELA_PRODUTOS
            = "produtos";
    private static final String ID = "id";
    private static final String DESCRICAO = "descricao";
    private static final String ESTOQUE = "estoque";
    private static final String PRECO = "preco";
    private static final String[] COLUNAS =
            {ID, DESCRICAO, ESTOQUE, PRECO};

    public void addProduto(Produto p) {
        // Abrindo o banco no modo de escrita
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DESCRICAO, p.getDescricao());
        valores.put(ESTOQUE, p.getEstoque());
        valores.put(PRECO, p.getPreco());
        db.insert(TABELA_PRODUTOS, null, valores);
        db.close();
    }

    // Retorna um produto usando seu id
    public Produto getProduto(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor registros = db.query(
                TABELA_PRODUTOS, // Nome da tabela
                COLUNAS, // Vetor c/ nomes dos campos
                "id = ?", // Condição do WHERE
                new String[] {String.valueOf(id)},
                null, // group by
                null, // having
                null, // order by
                null); //limit

        Produto p = new Produto();
        if (registros != null) {
            registros.moveToFirst();

            p.setId(registros.getInt(0));
            p.setDescricao(registros.getString(1));
            p.setEstoque(registros.getInt(2));
            p.setPreco(registros.getDouble(3));
        }
        return p;
    }

    public List<Produto> getTodosProdutos() {
        // Lista temporária que será preenchida
        List<Produto> produtos =
                new LinkedList<Produto>();
        String query = "SELECT * FROM " + TABELA_PRODUTOS;
        SQLiteDatabase db = this.getReadableDatabase();
        // Executa o SELECT e guarda o resultado
        Cursor registros = db.rawQuery(query, null);
        Produto p = null;
        if (registros.moveToFirst()) {
            do {
                p = new Produto();
                p.setId(registros.getInt(0));
                p.setDescricao(registros.getString(1));
                p.setEstoque(registros.getInt(2));
                p.setPreco(registros.getDouble(3));
                produtos.add(p);
            } while (registros.moveToNext());
        }
        return produtos;
    }

    public int updateProduto(Produto p) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put(DESCRICAO, p.getDescricao());
        valores.put(ESTOQUE, p.getEstoque());
        valores.put(PRECO, p.getPreco());
        // Atualiza a tabela
        int i = db.update(TABELA_PRODUTOS,
                valores,
                "id = ?",
                new String[] {String.valueOf(p.getId())});
        db.close();
        return i;
    }

    public int deleteProduto(Produto p) {
        SQLiteDatabase db = this.getWritableDatabase();
        int i = db.delete(TABELA_PRODUTOS,
                "id = ?",
                new String[] {String.valueOf(p.getId())});
        db.close();
        return i;
    }

}
