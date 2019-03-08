package com.example.josezequiel.sqliteprueba;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    //redundante con respecto al método getApplicationContext();
    Context context = this;


    //Declaramos los componentes gráficos
    ListView empleadosLV;
    Button nuevoEmpleadoBtn;

    private static final String TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Obtener la referencia de los componentes gráficos
        empleadosLV = this.findViewById(R.id.empleadosLV);
        nuevoEmpleadoBtn = this.findViewById(R.id.nuevoEmpleadoBTN);

        DatabaseHelper databaseHelper = DatabaseHelper.getInstance(getApplicationContext());
        databaseHelper.insertaNuevoEmpleado("Juan", 1445, "juan@mail.com");
        databaseHelper.insertaNuevoEmpleado("Lara", 1226.68, "lara@mail.com");

        List<Empleado> empleadoList = databaseHelper.getEmployees();
        for(Empleado e : empleadoList){
            Log.d(TAG, e.getNombre());
            Log.d(TAG, e.getSueldo() + "");
            Log.d(TAG, e.getEmail());
        }

        //Vamos a crear un ArrayAdapter que actúa como "pegamento" a la listView
        //es decir le pasa los datos que tiene mostrar en cada elemento de su lista
        ArrayAdapter<Empleado> empleadoArrayAdapter = new ArrayAdapter<>(getApplicationContext(), android.R.layout.simple_list_item_1, empleadoList);

        //Ahora ligamos el array adapter al list view que consideremos
        empleadosLV.setAdapter(empleadoArrayAdapter);

    }


    //No me deja entrar con context desde otra clase
    //Y tampoco podemos crear objetos de la clase MainActivity para acceder a sus métodos
    public int suma(int a, int b){
        return a + b;
    }
}
