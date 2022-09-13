package com.example.shopapp.vista;

import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;

import androidx.appcompat.app.AppCompatActivity;

import com.example.shopapp.R;
import com.example.shopapp.modelo.Comprador;
import com.example.shopapp.sqlite.DBOpenHelper;
import com.example.shopapp.sqlite.OpsBD;

import java.io.IOException;

/***************************************************************
 * Clase que interactua con la interfaz de registro de la app. *
 ***************************************************************/
public class InterfazRegistro extends AppCompatActivity implements View.OnClickListener {


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												DECLARACIONES  											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    OpsBD ops;

    EditText nom,mail,passw, phn;
    RadioGroup tipoUsua;

    Button botonRegistro, botonSalir;

    private DBOpenHelper myDBHelper;
    private SQLiteDatabase datos;



      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												ON CREATE   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        /*myDBHelper = new DBOpenHelper(this);

        try {
            myDBHelper.updateDataBase();
        } catch (IOException mIOException) {
            throw new Error("UnableToUpdateDatabase");
        }

        try {
            datos = myDBHelper.getWritableDatabase();
        } catch (SQLException mSQLException) {
            throw mSQLException;
        }*/

        ops = OpsBD.obtenerInstancia(getApplicationContext());

        //datos.getDb().beginTransaction();

       // this.datos = Main.getDatos();

        mail = findViewById(R.id.email);
        nom = findViewById(R.id.nombre);
        passw = findViewById(R.id.password);
        phn = findViewById(R.id.phone);
        tipoUsua = findViewById(R.id.tipoUSU);

        botonRegistro = findViewById(R.id.button_register);
        botonSalir = findViewById(R.id.button_salir);

        botonRegistro.setOnClickListener(this);
        botonSalir.setOnClickListener(this);

    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												   ON CLICK   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void onClick(View v) {
        //Acciones de cada boton
        switch (v.getId()) {
            case R.id.button_register:
                System.out.println("ESTE USUARIO ES UN COMPRADOR:\n");
                //ops.obtenerCompradores();
               // System.out.println("___________\n");
                registrar(v);
                break;
            case R.id.button_salir:
                salir(v);
                break;
        }
    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												REGISTRAR   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void registrar(View view){

        if(tipoUsua.getCheckedRadioButtonId() == R.id.soyComp) {

            //String comprador1 =
                    ops.insertarComprador(new Comprador(
                    null,nom.getText().toString(),mail.getText().toString(),
                    phn.getText().toString(),passw.getText().toString()));

        }
       /* else {

            //Insertamos en la tabla Vendedores los valores
             String vendedor1 = datos.insertarVendedor(new Vendedor(null, nom.getText().toString(), mail.getText().toString(), phn.getText().toString(), passw.getText().toString()));

        }*/

       /*** Intent ven = new Intent(this, Main.class);
        startActivity(ven); **/
        salir(view);
    }


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												    SALIR   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public void salir(View view){

        finish();
    }
}
