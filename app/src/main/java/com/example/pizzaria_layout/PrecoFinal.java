package com.example.pizzaria_layout;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class PrecoFinal extends AppCompatActivity {

    private TextView txtv_resumo, txtv_total;
    private Button btn_voltar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preco_final);

        txtv_resumo = findViewById(R.id.txtv_resumo);
        txtv_total = findViewById(R.id.txtv_total);

        Intent intent = getIntent();
        String resumoPedido = intent.getStringExtra("resumoPedido");
        double precoTotal = intent.getDoubleExtra("precoTotal", 0);

        txtv_resumo.setText(resumoPedido);
        txtv_total.setText("Preço Total: R$ " + String.format("%.2f", precoTotal));

        btn_voltar = findViewById(R.id.btn_voltar);
        btn_voltar.setOnClickListener(view -> {
            Intent mainIntent = new Intent(PrecoFinal.this, MainActivity.class);
            mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(mainIntent);
            finish();
        });
    }
}