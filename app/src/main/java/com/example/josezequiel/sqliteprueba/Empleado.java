package com.example.josezequiel.sqliteprueba;


public class Empleado{

    //No nos hace falta el constructor en nuestro modelo de datos

    private String email; //vamos a usar este como id_primario
    private String nombre;
    private double sueldo;


    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString(){
        return nombre + " " + sueldo + " EUR " + " " + email;
    }
}