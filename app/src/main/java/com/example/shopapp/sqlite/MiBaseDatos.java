package com.example.shopapp.sqlite;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.provider.BaseColumns;

import com.example.shopapp.sqlite.Contrato.Compradores;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;


/*****************************************************************************
 * Clase que administra la conexión de la base de datos y su estructuración. *
 *****************************************************************************/
public class MiBaseDatos extends SQLiteOpenHelper {


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												DECLARACIONES  											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    //private static String DB_PATH = "/data/data/com.example.shopapp/databases/";
    private static final String NOMBRE_BASE_DATOS = "baseDatosApp.sqlite";
    private static final int VERSION_ACTUAL = 1;
    private SQLiteDatabase mydatabase;
    private final Context contexto;


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												INTERFACE   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /***************************************** TABLAS BASE DE DATOS *****************************************/

    interface Tablas {
        //String CABECERA_PEDIDO = "cabecera_pedido";
        //String DETALLE_PEDIDO = "detalle_pedido";
        String PRODUCTO = "producto";
        String COMPRADOR = "comprador";
        String VENDEDOR = "vendedor";
    }

    /********************************************** INTERFACE *********************************************/

    interface Referencias {

      /*  String ID_CABECERA_PEDIDO = String.format("REFERENCES %s(%s) ON DELETE CASCADE",
                Tablas.CABECERA_PEDIDO, CabecerasPedido.ID);

        String ID_PRODUCTO = String.format("REFERENCES %s(%s)",
                Tablas.PRODUCTO, Productos.ID);*/

        String ID_COMPRADOR = String.format("REFERENCES %s(%s)",
                Tablas.COMPRADOR, Compradores.ID);

      /*  String ID_VENDEDOR = String.format("REFERENCES %s(%s)",
                Tablas.VENDEDOR, Vendedores.ID); */
    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												CONSTRUCTOR   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public MiBaseDatos(Context _contexto) {
        super(_contexto, NOMBRE_BASE_DATOS, null, VERSION_ACTUAL);
        this.contexto = _contexto;
    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //		    									  ON OPEN   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onOpen(SQLiteDatabase db) {

        super.onOpen(db);
        if (!db.isReadOnly()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                db.setForeignKeyConstraintsEnabled(true);
            } else {
                db.execSQL("PRAGMA foreign_keys=ON");
            }
        }
    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												ON CREATE   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onCreate(SQLiteDatabase db) {

       /* db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT UNIQUE NOT NULL,%s DATETIME NOT NULL,%s TEXT NOT NULL %s," +
                        "%s TEXT NOT NULL %s)",
                Tablas.CABECERA_PEDIDO, BaseColumns._ID,
                CabecerasPedido.ID, CabecerasPedido.FECHA,
                CabecerasPedido.ID_CLIENTE, Referencias.ID_CLIENTE,
                CabecerasPedido.ID_FORMA_PAGO, Referencias.ID_FORMA_PAGO));

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL %s,%s INTEGER NOT NULL CHECK (%s>0),%s INTEGER NOT NULL %s," +
                        "%s INTEGER NOT NULL,%s REAL NOT NULL,UNIQUE (%s,%s) )",
                Tablas.DETALLE_PEDIDO, BaseColumns._ID,
                DetallesPedido.ID, Referencias.ID_CABECERA_PEDIDO,
                DetallesPedido.SECUENCIA, DetallesPedido.SECUENCIA,
                DetallesPedido.ID_PRODUCTO, Referencias.ID_PRODUCTO,
                DetallesPedido.CANTIDAD, DetallesPedido.PRECIO,
                DetallesPedido.ID, DetallesPedido.SECUENCIA));

        db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s REAL NOT NULL," +
                        "%s INTEGER NOT NULL CHECK(%s>=0) )",
                Tablas.PRODUCTO, BaseColumns._ID,
                Productos.ID, Productos.NOMBRE, Productos.PRECIO,
                Productos.EXISTENCIAS, Productos.EXISTENCIAS)); */

        db.execSQL(String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL,%s TEXT NOT NULL,%s TEXT NOT NULL)",
                Tablas.COMPRADOR, BaseColumns._ID,
                Compradores.ID, Compradores.NOMBRE, Compradores.EMAIL, Compradores.TELEFONO, Compradores.PASSWORD));
        // Creamos las tablas en la Base de datos
       // db.execSQL(Contrato.COMPRADORES_TABLE_CREATE);

       /* db.execSQL(String.format("CREATE TABLE %s ( %s INTEGER PRIMARY KEY AUTOINCREMENT," +
                        "%s TEXT NOT NULL UNIQUE,%s TEXT NOT NULL )",
                Tablas.FORMA_PAGO, BaseColumns._ID,
                FormasPago.ID, FormasPago.NOMBRE)); */

    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												ON UPGRADE   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

     /*   db.execSQL("DROP TABLE IF EXISTS " + Tablas.CABECERA_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.DETALLE_PEDIDO);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.PRODUCTO); */
        //db.execSQL("DROP TABLE IF EXISTS " + Tablas.COMPRADOR);
        //db.execSQL("DROP TABLE IF EXISTS " + Tablas.VENDEDOR);

        //El método onUpgrade se ejecuta cada vez que recompilamos e instalamos la app con un
        //nuevo numero de version de base de datos (DATABASE_VERSION), de tal mamera que en este
        // método lo que hacemos es:
        // 1. Con esta sentencia borramos la tabla "notes"
        //db.execSQL(Contrato.COMPRADORES_TABLE_DROP);
        db.execSQL("DROP TABLE IF EXISTS " + Tablas.COMPRADOR);

        // 2. Con esta sentencia llamamos de nuevo al método onCreate para que se cree de nuevo
        //la tabla "notes" la cual seguramente al cambair de versión ha tenido modifciaciones
        // en cuanto a su estructura de columnas
        this.onCreate(db);
    }


  /*  public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if(dbExist){
            //Si existe no hacemos nada
        }
        else{
            //Creamos base de datos vacia en la ruta por defecto del sistema de la app -> sobreescribimos Base datos
            this.getReadableDatabase();

            try{
                copyDataBase();

            }catch (IOException e) {
                throw new Error("\nError copiando Base de Datos");
            }
        }
    }


    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try{
            String mypath = DB_PATH + NOMBRE_BASE_DATOS;
            checkDB = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
        } catch (SQLiteException e){
            //Base de datos no creada por ahora
        }

        if(checkDB != null) {
            checkDB.close();
        }

        return checkDB != null ? true : false;
    }

    public void openDataBase() throws SQLException {

        //Open the database
        String mypath = DB_PATH + NOMBRE_BASE_DATOS;
        mydatabase = SQLiteDatabase.openDatabase(mypath, null, SQLiteDatabase.OPEN_READWRITE);
    }


    public synchronized void close() {
        if(mydatabase != null){
            mydatabase.close();
        }
        super.close();
    }

    private void copyDataBase() throws IOException {

        //Abrimos la Base de Datos de la carpeta Assets
        InputStream myDBInputStream = contexto.getAssets().open(NOMBRE_BASE_DATOS);

        //Carpeta de Destino donde hemos creado la DB vacia
        String outFileName = DB_PATH + NOMBRE_BASE_DATOS;
        //Abrimos la base de datos vacia
        OutputStream dbOutputStream = new FileOutputStream(outFileName);

        //Transfiere los Bytes entre el Stram de entrada y el de salida
        byte[] buffer = new byte[1024];
        int length;

        while((length = myDBInputStream.read(buffer)) > 0) {
            dbOutputStream.write(buffer, 0, length);
        }

        myDBInputStream.close();
        dbOutputStream.flush();
        dbOutputStream.close();
    }*/
}
