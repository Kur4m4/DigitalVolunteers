package br.com.kleberxavier.digitalvolunteers.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.kleberxavier.digitalvolunteers.model.ONG;

/**
 * Created by Kai on 09/02/2018.
 */

public class ONGDAO {

    private SQLiteDatabase db;
    private DBOpenHelper banco;

    public ONGDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    public void addONG(ONG ong) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put("nome", ong.getNome());
        values.put("endereco", ong.getEndereco());
        values.put("telefone", ong.getTelefone());
        values.put("email", ong.getEmail());

        db.insert("ong", null, values);

        db.close();
    }

    public List<ONG> getONGs() {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = {"nome", "endereco", "telefone", "email"};
        Cursor cursor = db.query("ong", colunas, null, null, null, null, null);

        List<ONG> ongs = new ArrayList<ONG>();
        if (cursor != null) {
            while (cursor.moveToNext()) {
                ONG ong = new ONG();
                ong.setNome(cursor.getString(cursor.getColumnIndex("nome")));
                ong.setEndereco(cursor.getString(cursor.getColumnIndex("endereco")));
                ong.setTelefone(cursor.getString(cursor.getColumnIndex("telefone")));
                ong.setEmail(cursor.getString(cursor.getColumnIndex("email")));
                ongs.add(ong);
            }
        }
        cursor.close();
        return ongs;
    }
}
