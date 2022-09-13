package com.example.shopapp.vista;

import android.content.Intent;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;
import com.example.shopapp.modelo.Comprador;
import com.example.shopapp.sqlite.DBOpenHelper;
import com.example.shopapp.sqlite.MiBaseDatos;
import com.example.shopapp.sqlite.OpsBD;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;


/*************************************
 * Clase principal de la aplicación. *
 *************************************/
public class Main extends AppCompatActivity {


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												DECLARACIONES   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    OpsBD datos;

    EditText email,passw;
    String contrasena, mail;
    private Cursor fila, fila2;
   //private DBOpenHelper myDBHelper;
    //private SQLiteDatabase myDb;
    private String dbname = "baseDatosApp.sqlite";

    public class TareaPruebaDatos extends AsyncTask<Void, Void, Void> {
        @Override
        protected Void doInBackground(Void... params) {

            // [INSERCIONES]
            System.out.println("PROBANDO INSERCIONES");
            try {

                datos.getDb().beginTransaction();

                // Inserción Compradores
                //String comprador1 =
                        datos.insertarComprador(new Comprador(null, "Admin", "admin@gmail.com", "123445",  "Admin1"));
               // String comprador2 =
                        datos.insertarComprador(new Comprador(null, "Antia", "antia@gmail.com", "4673561", "4521Ant"));
               // String comprador3 =
                        datos.insertarComprador(new Comprador(null, "Maday", "maday@gmail.com", "8966198", "Mad156w"));
                //String comprador4 =
                        datos.insertarComprador(new Comprador(null, "Veronica", "Del Topo", "4552000", "Vvero67"));
                System.out.println("COMPRADORES INSERTADOS");

                // Actualización Comprador
               //datos.actualizarComprador(new Comprador(comprador4, "Carlos Alberto", "Villagran", "3333333", "caR45"));
                System.out.println("COMPRADORES ACTUALIZADOS");

                datos.getDb().setTransactionSuccessful();

            } finally {
                datos.getDb().endTransaction();
            }

            // [QUERIES]
            Log.d("Compradores","Compradores");
            DatabaseUtils.dumpCursor(datos.obtenerCompradores());
            System.out.println("VUELVO A ON CREATE");
            return null;
        }
    }

      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												ON CREATE   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        System.out.println("INICIO DE LA APLICACIÓN");

        //LoadBase();

        Button botonRegistro = (Button) findViewById(R.id.button_register);
        Button botonLogin = (Button) findViewById(R.id.button_login);

        //myDBHelper = new DBOpenHelper(this);

       /* try {
            myDb = myDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }*/

       /* //Inicializamos la base de datos
        MiBaseDatos db = new MiBaseDatos(this);
        try{
            db.createDataBase();
            db.openDataBase();
        } catch (IOException e) {
            e.printStackTrace();
        }*/

        System.out.println("****** EMPEZAMOS CON LA BASE DE DATOS ******");
        getApplicationContext().deleteDatabase("baseDatosApp.sqlite");
        datos = OpsBD.obtenerInstancia(getApplicationContext());

        new TareaPruebaDatos().execute();

        email = findViewById(R.id.email);
        passw = findViewById(R.id.phone);

        botonRegistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                System.out.println("VAMOS A REGISTRARNOS");
                registro(v);
            }
        });

        botonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                email = findViewById(R.id.email);
                passw = findViewById(R.id.phone);
                mail = email.getText().toString();
                contrasena = passw.getText().toString();
                System.out.println("VAMOS A INGRESAR");
                ingresar(v);
            }
        });
    }

    private void LoadBase(){

        File checkDB = null;

        try{
            checkDB = new File(getFilesDir() + "/" + dbname);

            if(!checkDB.exists()){
                InputStream myInput = getApplicationContext().getAssets().open(dbname);
                OutputStream myOutput = new FileOutputStream(getFilesDir() + "/" + dbname);

                byte[] buffer = new byte[1024];
                int length;
                while((length = myInput.read(buffer)) > 0){
                    myOutput.write(buffer, 0, length);
                }

                myOutput.flush();
                myOutput.close();
                myInput.close();
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //											        INGRESAR   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void ingresar(View v){

       // getApplicationContext().deleteDatabase("AppShop");
        datos = OpsBD.obtenerInstancia(getApplicationContext());

        datos.getDb().beginTransaction();

        fila = datos.buscarComprador(mail, contrasena);

        if(fila != null) {

            Intent ven = new Intent(this, InterfazComprador.class);
            startActivity(ven);

            email.setText("");
            passw.setText("");
        }
       /* else{
            fila2 = datos.buscarVendedor(usuario, contrasena);

            if(fila2 != null) {
                if (fila2.moveToFirst()) {
                    String usua = fila2.getString(0);
                    String pass = fila2.getString(1);
                    if (usuario.equals(usua) && contrasena.equals(pass)) {
                        Intent ven = new Intent(this, Vendedor.class);
                        startActivity(ven);
                        id.setText("");
                        passw.setText("");
                    }
                }
            }
            else{
                //ERROR no existe un usuario con esos datos.
                salir(v);
            }
        }*/
    }

/*    public static OpsBD getDatos(){
        return datos;
    }
*/

      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //											        REGISTRO   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void registro(View v){

        System.out.println("NOS VAMOS A REGISTRAR A UNA NUEVA INTERFAZ");
        Intent x = new Intent(this, InterfazRegistro.class);
        startActivity(x);
        System.out.println("ESTAMOS DE VUELTA DESPUES DEL REGISTRO");

    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //											    	SALIR   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void salir(View v){

        finish();
        //System.exit(0);
    }
}
