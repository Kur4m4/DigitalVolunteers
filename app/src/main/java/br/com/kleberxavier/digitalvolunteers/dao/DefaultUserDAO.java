package br.com.kleberxavier.digitalvolunteers.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import br.com.kleberxavier.digitalvolunteers.model.DefaultUser;

/**
 * Created by Kai on 15/09/2017.
 */

public class DefaultUserDAO {

    private SQLiteDatabase db;
    private DBOpenHelper banco;

    public DefaultUserDAO(Context context) {
        banco = new DBOpenHelper(context);
    }

    private static final String TABELA_DEFAULT_USER = "defaultUser";

    private static final String COLUNA_USERNAME = "username";
    private static final String COLUNA_PASSWORD = "password";

    public void add(DefaultUser defaultUser) {
        SQLiteDatabase db = banco.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(COLUNA_USERNAME, defaultUser.getUsuario());
        values.put(COLUNA_PASSWORD, defaultUser.getSenha());

        db.insert(TABELA_DEFAULT_USER, null, values);

        db.close();
    }

    public boolean defaultUserExists(DefaultUser defaultUser) {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = {COLUNA_USERNAME, COLUNA_PASSWORD};
        String where = "username = '" + defaultUser.getUsuario() + "'";
        Cursor cursor = db.query(true, TABELA_DEFAULT_USER, colunas, where, null, null, null, null, null);

        boolean usuarioExiste = false;
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                usuarioExiste = cursor.getString(cursor.getColumnIndex(COLUNA_USERNAME)).equals(defaultUser.getUsuario());
            }
        }
        cursor.close();
        return usuarioExiste;
    }

    public DefaultUser getDefaultUser() {
        SQLiteDatabase db = banco.getReadableDatabase();
        String colunas[] = {COLUNA_USERNAME, COLUNA_PASSWORD};
        Cursor cursor = db.query(TABELA_DEFAULT_USER, colunas, null, null, null, null, null);

        DefaultUser defaultUser = new DefaultUser();
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                defaultUser.setUsuario(cursor.getString(cursor.getColumnIndex(COLUNA_USERNAME)));
                defaultUser.setSenha(cursor.getString(cursor.getColumnIndex(COLUNA_PASSWORD)));
            }
        }
        cursor.close();
        return defaultUser;
    }
}
