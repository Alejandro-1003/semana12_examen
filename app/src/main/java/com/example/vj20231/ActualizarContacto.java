package com.example.vj20231;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vj20231.entities.Contact;
import com.example.vj20231.services.ContactService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.HashMap;
import java.util.Map;

public class ActualizarContacto extends AppCompatActivity {

    EditText etNombreUp, etImgContactUp;
    Button btnGuardar;
    ContactService contactService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_contacto);

        etNombreUp = findViewById(R.id.etNombreUp);
        etImgContactUp = findViewById(R.id.etImgContactUp);

        btnGuardar = findViewById(R.id.btnGuardar);

        // Obtener los datos del contacto actual del intent
        String nombreContacto = getIntent().getStringExtra("nombre");
        String imagenContacto = getIntent().getStringExtra("imagen");

        // Completar los campos con los datos del contacto actual
        etNombreUp.setText(nombreContacto);
        etImgContactUp.setText(imagenContacto);

        // Crear una instancia de Retrofit y ContactService
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://64868bfdbeba6297278ee1c3.mockapi.io/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        contactService = retrofit.create(ContactService.class);

        btnGuardar.setOnClickListener(v -> {
            // Obtener los nuevos valores de nombre, número de teléfono, tipo y imagen
            String nuevoNombre = etNombreUp.getText().toString().trim();
            String nuevaImagen = etImgContactUp.getText().toString().trim();

            // Obtener el ID del contacto actual del intent
            int idContacto = getIntent().getIntExtra("id", 0);

            // Crear un objeto Contact con los nuevos datos
            Contact contactoActualizado = new Contact( idContacto, nuevoNombre, nuevaImagen);

            // Llamar al método updateContacto del ContactService para actualizar el contacto
            Call<Contact> llamada = contactService.updateContacto(idContacto, contactoActualizado);
            llamada.enqueue(new Callback<Contact>() {
                @Override
                public void onResponse(Call<Contact> call, Response<Contact> response) {
                    if (response.isSuccessful()) {
                        // Actualización exitosa, puedes realizar cualquier acción adicional necesaria
                        Toast.makeText(ActualizarContacto.this, "Contacto actualizado exitosamente", Toast.LENGTH_SHORT).show();
                        setResult(RESULT_OK);
                        finish();
                        Intent intent = new Intent(ActualizarContacto.this, ListaContactActivity.class);
                        startActivity(intent);
                    } else {
                        // Error en la actualización, muestra un mensaje de error o realiza una acción de manejo de errores
                        Toast.makeText(ActualizarContacto.this, "Error al actualizar el contacto", Toast.LENGTH_SHORT).show();
                    }
                }

                @Override
                public void onFailure(Call<Contact> call, Throwable t) {
                    // Error en la solicitud, muestra un mensaje de error o realiza una acción de manejo de errores
                    Toast.makeText(ActualizarContacto.this, "Error al realizar la solicitud", Toast.LENGTH_SHORT).show();
                }
            });
        });
    }
}
