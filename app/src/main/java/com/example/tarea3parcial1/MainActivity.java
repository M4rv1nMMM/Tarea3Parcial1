package com.example.tarea3parcial1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText etxtnombre, etxtapellidos, etxtedad, etxtcorreo, etxtdireccion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etxtnombre = (EditText) findViewById(R.id.txtNombre);
        etxtapellidos = (EditText) findViewById(R.id.txtApellidos);
        etxtedad = (EditText) findViewById(R.id.txtEdad);
        etxtcorreo = (EditText) findViewById(R.id.txtCorreo);
        etxtdireccion = (EditText) findViewById(R.id.txtDireccion);
    }
    //metodo de creacion
    public void crear(View view){
        personas admin = new personas(this, "dbpersonas", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        String varnombre = etxtnombre.getText().toString();
        String varapellidos = etxtapellidos.getText().toString();
        String varedad = etxtedad.getText().toString();
        String varcorreo = etxtcorreo.getText().toString();
        String vardireccion = etxtdireccion.getText().toString();

        if(!varnombre.isEmpty() && !varapellidos.isEmpty() && !varedad.isEmpty() && !varcorreo.isEmpty() && !vardireccion.isEmpty()){
            ContentValues creacion = new ContentValues();

            creacion.put("pname", varnombre);
            creacion.put("pname2", varapellidos);
            creacion.put("page", varedad);
            creacion.put("pmail", varcorreo);
            creacion.put("paddress", vardireccion);

            BD.insert("tbl_personas", null, creacion);
            BD.close();

            etxtnombre.setText("");
            etxtapellidos.setText("");
            etxtedad.setText("");
            etxtcorreo.setText("");
            etxtdireccion.setText("");

            Toast.makeText(this, "Registro Exitoso", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Debes llenar todos los campos", Toast.LENGTH_SHORT).show();
        }
    }

    //metodo de busqueda
    public void buscar(View view){
        personas admin = new personas(this, "dbpersonas", null, 1);
        SQLiteDatabase BD = admin.getWritableDatabase();

        String varname = etxtnombre.getText().toString();

        if(!varname.isEmpty()){
            Cursor fila = BD.rawQuery
                    ("select pname2, page, pmail, paddress from tbl_personas where pname =" + varname, null);

            if(fila. moveToFirst()){
                etxtapellidos.setText(fila.getString(0));
                etxtedad.setText(fila.getString(1));
                etxtcorreo.setText(fila.getString(2));
                etxtdireccion.setText(fila.getString(3));
                BD.close();
            }else{
                Toast.makeText(this, "No existe el Registro", Toast.LENGTH_SHORT).show();
                BD.close();
            }
        }else {
            Toast.makeText(this, "Debes introducir un nombre", Toast.LENGTH_SHORT).show();
        }
    }
}