package com.codigomaestro.textview;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Crear instancia del botón, EditText y TextView
        Button btn_cambiar = findViewById(R.id.btn_cambiar);
        EditText editText = findViewById(R.id.editarT);
        TextView textView = findViewById(R.id.text_view);

        // Configurar OnClickListener
        btn_cambiar.setOnClickListener(view -> {
            String texto = editText.getText().toString();
            textView.setText(texto);
        });
    }

}