package com.example.shopapp.sqlite;

import java.util.UUID;


/***************************************************************
 * Clase que establece los nombres a usar en la base de datos. *
 ***************************************************************/
public class Contrato {


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												COMPRADORES   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    /********************************************** INTERFACE **********************************************/

    interface ColumnasComprador {
        //Nombre de la tabla
       // String TABLE_NAME = "comprador";
        //Nombre de las Columnas que contiene la tabla
        String ID = "id";
        String NOMBRE = "nombre";
        String EMAIL = "email";
        String TELEFONO = "telefono";
        String PASSWORD = "password";
    }

    /********************************************* COMPRADORES *********************************************/

    public static class Compradores implements ColumnasComprador{
        public static String generarIdComprador() {

            return "COMP-" + UUID.randomUUID().toString();
        }
    }

    //Setencia SQL que permite crear la tabla Notes
  /*  public static final String COMPRADORES_TABLE_CREATE =
            "CREATE TABLE " + Compradores.TABLE_NAME + " (" +
                    Compradores.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    Compradores.NOMBRE + " TEXT, " +
                    Compradores.EMAIL + " TEXT, " +
                    Compradores.TELEFONO + " TEXT, " +
                    Compradores.PASSWORD + " TEXT);";

    //Setencia SQL que permite eliminar la tabla Notes
    public static final String COMPRADORES_TABLE_DROP = "DROP TABLE IF EXISTS " + Compradores.TABLE_NAME; */

      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												VENDEDORES   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
