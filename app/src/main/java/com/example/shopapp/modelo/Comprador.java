package com.example.shopapp.modelo;


/****************************
 * Clase usuario comprador. *
 ****************************/
public class Comprador {


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												DECLARACIONES   										       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public String idComprador;
    public String  nombre, email, password, phone;


      ////////////////////////////////////////////////////////////////////////////////////////////////////////////////
     //												CONSTRUCTOR   											       //
    ////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public Comprador(){}

    public Comprador(String idComprador, String nombre,String email, String phone, String password){

        this.idComprador = idComprador;
        this.nombre = nombre;
        this.email = email;
        this.phone = phone;
        this.password = password;
    }

    // properties
    public void setID(String id) {
        this.idComprador= id;
    }

    public String getID() {
        return this.idComprador;
    }

    public void setCompradorName(String compradorname) {
        this.nombre = compradorname;
    }

    public String getCompradorName() {
        return this.nombre;
    }

    public void setCompradorPass(String passwordComp) {
        this.password = passwordComp;
    }

    public String getCompradorPass() {
        return this.password;
    }

    public void setCompradorPhone(String compradorphone) {
        this.phone = compradorphone;
    }

    public String getCompradorPhone() {
        return this.phone;
    }

    public void setCompradorMail(String compradormail) {
        this.email = compradormail;
    }

    public String getCompradorMail() {
        return this.email;
    }

}
