package com.example.escribirfichero;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class MainActivity extends AppCompatActivity {

    private Button botonGuardar, botonLeer, botonGuardarSD, botonLeerSD;

    private TextView textoRuta, textoArchivo;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        botonGuardar = findViewById(R.id.bt_guardar);
        botonGuardar.setOnClickListener(this::guardarInterno);

        botonLeer = findViewById(R.id.bt_leer);
        botonLeer.setOnClickListener(this::leerInterno);

        botonGuardarSD = findViewById(R.id.bt_guardarSD);
       // botonGuardarSD.setOnClickListener(this::guardarSD);

        botonLeerSD = findViewById(R.id.bt_leerSD);
        //botonLeerSD.setOnClickListener(this::leerSD);

        textoRuta = findViewById(R.id.txtArchivo);

        textoArchivo = findViewById(R.id.txtContenido);


    }


    protected void guardarInterno(View view) {
        OutputStreamWriter escritor;
        try {
            escritor = new OutputStreamWriter(openFileOutput(textoRuta.getText().toString(),
                    Context.MODE_PRIVATE));
            escritor.write(textoArchivo.getText().toString());
            escritor.close();
            Toast.makeText(this, "Se ha guardado correctamente ", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se ha guardado correctamente", Toast.LENGTH_LONG).show();
        }
    }


    protected void leerInterno(View view) {
        InputStreamReader lector = null;
        try {
            //Abrimos el fichero escrito en el EditText etFichero
            lector = new InputStreamReader(openFileInput(textoRuta.getText().toString()));
            BufferedReader buff = new BufferedReader(lector);
            String strTmp;
            StringBuffer strBuff = new StringBuffer();
            while ((strTmp = buff.readLine()) != null) {
                strBuff.append(strTmp).append("\n");
            }
            lector.close();
            //Escribimos el texto cargado del fichero en el EditText etEditor
            textoArchivo.setText(strBuff);
            Toast.makeText(this, "Se ha leido correctamente", Toast.LENGTH_LONG).show();
        } catch (FileNotFoundException e) {
            Toast.makeText(this, "No se ha encontrado el archivo", Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            Toast.makeText(this, "No se ha ledio de forma correcta", Toast.LENGTH_LONG).show();
        }
    }


}