package com.example.josezequiel.sqliteprueba;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    //**Atributos de nuestra base de datos **//
    private static final String DB_NAME = "Agency";
    private static final int DB_VERSION = 1;

    private static final String EMPLOYEE_TABLE_NAME = "employee";
    private static final String EMPOYEE_TABLE_CREATE = "create table " + EMPLOYEE_TABLE_NAME + "( nombre TEXT, sueldo REAL, email TEXT primary key)";

    //Patrón de diseño Singleton: evita que haya en memoria más de una imagen de esta clase
    //y por lo tanto la base datos que es lo que lleva dentro esta clase
    private static DatabaseHelper databaseHelper = null;
    private final Context context;

    public static DatabaseHelper getInstance(Context context){
        if(databaseHelper == null){
            databaseHelper = new DatabaseHelper(context);
        }

        return databaseHelper;
    }

    private DatabaseHelper(Context context){
        super( context, DB_NAME , null, DB_VERSION );
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("Versión de nuestra base de datos " + db.getVersion());
        db.execSQL(EMPOYEE_TABLE_CREATE);



    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }


    ///// INSERCIÖN DE DATOS EMPLEADOS ////
    public void insertaNuevoEmpleado(String nombre, double sueldo, String email){

        //Obtenemos una imagen de la base datos con permiso para escribir
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues contentValues = new ContentValues();
        contentValues.put("nombre", nombre);
        contentValues.put("sueldo", sueldo);
        contentValues.put("email", email);

        db.insert(EMPLOYEE_TABLE_NAME, null, contentValues);
        db.close();
    }

    /////SELECCION DE DATOS EMPLEADOS ////
    public List<Empleado> getEmployees(){

        List<Empleado> empleados = new ArrayList<>();

        //En este caso solo vamos a leer seleccionando datos
        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM " + EMPLOYEE_TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        if(cursor.moveToFirst()){
            do{
                Empleado empleado = new Empleado();

                empleado.setNombre(cursor.getString(0));
                empleado.setSueldo(cursor.getDouble(1));
                empleado.setEmail(cursor.getString(2));

                empleados.add(empleado);

            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return empleados;

    }


}
