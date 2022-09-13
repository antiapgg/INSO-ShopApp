package com.example.shopapp.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.shopapp.modelo.Comprador;
import com.example.shopapp.sqlite.Contrato.Compradores;
import com.example.shopapp.sqlite.MiBaseDatos.Tablas;

import java.io.IOException;


/*********************************************************************
 * Clase que establece las operaciones que realiza la base de datos. *
 *********************************************************************/
public final class OpsBD {


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												DECLARACIONES  											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private static MiBaseDatos baseDatos;   //DBOpenHelper
    private static OpsBD instancia = new OpsBD();


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //													CONSTRUCTOR												   //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private OpsBD(){

    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												BASE DE DATOS   										       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /****************************************** OBTENER INSTANCIA *******************************************/

    public static OpsBD obtenerInstancia(Context contexto) {

        if (baseDatos == null) {
            baseDatos = new MiBaseDatos(contexto);
        }
        return instancia;
    }


    /******************************************* GET BASE DE DATOS ******************************************/

    public SQLiteDatabase getDb() {

        return baseDatos.getWritableDatabase();
    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												COMPRADORES   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /***************************************** OBTENER COMPRADORES ******************************************/

    public Cursor obtenerCompradores() {

        SQLiteDatabase db = baseDatos.getReadableDatabase();

        String sql = String.format("SELECT * FROM %s", Tablas.COMPRADOR);

        return db.rawQuery(sql, null);
    }

    /******************************************* BUSCAR COMPRADOR *******************************************/

    public Cursor buscarComprador(String email, String pho){

        SQLiteDatabase db = baseDatos.getReadableDatabase();

       // String sql = String.format("SELECT %s AND %s FROM %s WHERE email = %s AND password = %s",
       //         Compradores.EMAIL, Compradores.PASSWORD, Tablas.COMPRADOR, email, password);

        return db.rawQuery(String.format("SELECT  * FROM " + Tablas.COMPRADOR + " WHERE email=\'"
                + email+"\'" +" AND phone=\'"+pho+"\'"), null);

      //  return db.rawQuery(sql, null);
    }

    /****************************************** INSERTAR COMPRADOR ******************************************/

    public void insertarComprador(Comprador comprador) {  //String , SQLiteDatabase base

       /** SQLiteDatabase db = baseDatos.getWritableDatabase(); //baseDatos

        // Generar Pk       //compradores. -> CONTRATO  //comprador. -> clase comprador
        String idComprador = Compradores.generarIdComprador();

        ContentValues valores = new ContentValues();
        valores.put(Compradores.ID, idComprador);
        valores.put(Compradores.NOMBRE, comprador.nombre);
        valores.put(Compradores.EMAIL, comprador.email);
        valores.put(Compradores.TELEFONO, comprador.phone);
       // valores.put(Compradores.PASSWORD, comprador.password);

       // long aux = db.insertOrThrow(Tablas.COMPRADOR, null, valores);

        return db.insertOrThrow(Tablas.COMPRADOR, null, valores) > 0 ? idComprador : null;*/
        String idComprador = Compradores.generarIdComprador(); //(?)

        ContentValues valores = new ContentValues();
        valores.put(Compradores.ID, idComprador);
        valores.put(Compradores.NOMBRE, comprador.getCompradorName());
        valores.put(Compradores.EMAIL, comprador.getCompradorMail());
        valores.put(Compradores.TELEFONO, comprador.getCompradorPhone());
        // valores.put(Compradores.PASSWORD, comprador.getCompradorPass());
        SQLiteDatabase db = baseDatos.getWritableDatabase();
        db.insert(Tablas.COMPRADOR, null, valores);
        db.close();
    }


    /***************************************** ACTUALIZAR COMPRADOR *****************************************/

    public boolean actualizarComprador(Comprador comprador) {

       /** SQLiteDatabase db = baseDatos.getWritableDatabase();

        ContentValues valores = new ContentValues();
        valores.put(Compradores.NOMBRE, comprador.nombre);
        valores.put(Compradores.EMAIL, comprador.email);
        valores.put(Compradores.TELEFONO, comprador.phone);
        //valores.put(Compradores.PASSWORD, comprador.password);

        String whereClause = String.format("%s=?", Compradores.ID);
        final String[] whereArgs = {comprador.idComprador};

        int resultado = db.update(Tablas.COMPRADOR, valores, whereClause, whereArgs);

        return resultado > 0;*/

       /* SQLiteDatabase db = baseDatos.getWritableDatabase();
        ContentValues args = new ContentValues();
        args.put(Compradores.ID, comprador.get);
        args.put(COLUMN_NAME, name);
        return db.update(TABLE_NAME, args, COLUMN_ID + "=" + ID, null) > 0;*/
       return false;
    }

    /****************************************** ELIMINAR COMPRADOR ******************************************/

    public boolean eliminarComprador(String idComprador) {
        SQLiteDatabase db = baseDatos.getWritableDatabase();

        String whereClause = String.format("%s=?", Compradores.ID);
        final String[] whereArgs = {idComprador};

        int resultado = db.delete(Tablas.COMPRADOR, whereClause, whereArgs);

        return resultado > 0;
    }


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												VENDEDORES   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////


}
