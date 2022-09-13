package com.example.shopapp.vista;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;
import com.example.shopapp.sqlite.OpsBD;

public class InterfazLogin extends AppCompatActivity {


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //												DECLARACIONES  											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    OpsBD datos;

    EditText email,passw;
    String contrasena, mail;
    private Cursor fila, fila2;
    //private DBOpenHelper myDBHelper;
    //private SQLiteDatabase myDb;
    private String dbname = "baseDatosApp.sqlite";


    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //												ON CREATE   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        System.out.println("INICIO DE LA INTERFAZ LOGIN");

        //LoadBase();

        Button botonRegistro = (Button) findViewById(R.id.button_register);
        Button botonLogin = (Button) findViewById(R.id.button_login);

        datos = OpsBD.obtenerInstancia(getApplicationContext());

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

                mail = email.getText().toString();
                contrasena = passw.getText().toString();
                System.out.println("VAMOS A INGRESAR");
                ingresar(v);
            }
        });
    }

    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
    //											        INGRESAR   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    private void ingresar(View v) {

        // getApplicationContext().deleteDatabase("AppShop");
        datos = OpsBD.obtenerInstancia(getApplicationContext());

        datos.getDb().beginTransaction();

        fila = datos.buscarComprador(mail, contrasena);

        if (fila != null) {

            Intent ven = new Intent(this, InterfazComprador.class);
            startActivity(ven);

            email.setText("");
            passw.setText("");
        }
    }

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
