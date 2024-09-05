package com.example.pizzaria_layout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText edt_havaiana, edt_frango, edt_vegetariana, edt_mm;
    private Button btn_pedido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edt_havaiana = findViewById(R.id.edt_pizza1);
        edt_frango = findViewById(R.id.edt_pizza2);
        edt_vegetariana = findViewById(R.id.edt_pizza3);
        edt_mm = findViewById(R.id.edt_pizza4);

        btn_pedido = findViewById(R.id.btn_calcular);
        btn_pedido.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int quantHavaiana = getQuantidadeFromEditText(edt_havaiana);
                int quantFrango = getQuantidadeFromEditText(edt_frango);
                int quantVegetariana = getQuantidadeFromEditText(edt_vegetariana);
                int quantMM = getQuantidadeFromEditText(edt_mm);

                if (quantHavaiana == 0 && quantFrango == 0 && quantVegetariana == 0 && quantMM == 0) {
                    Toast.makeText(MainActivity.this, "Selecione ao menos um sabor de pizza!", Toast.LENGTH_SHORT).show();
                    return;
                }

                double precoTotal = (quantHavaiana * 24.90) + (quantFrango * 26.80) + (quantVegetariana * 29.90) + (quantMM * 31.50);

                StringBuilder resumoPedido = new StringBuilder();
                if (quantHavaiana > 0) {
                    resumoPedido.append("Havaiana: ").append(quantHavaiana).append(" x R$24,90\n");
                }
                if (quantFrango > 0) {
                    resumoPedido.append("Frango com catupiry: ").append(quantFrango).append(" x R$26,80\n");
                }
                if (quantVegetariana > 0) {
                    resumoPedido.append("Vegetariana: ").append(quantVegetariana).append(" x R$29,90\n");
                }
                if (quantMM > 0) {
                    resumoPedido.append("M&M's: ").append(quantMM).append(" x R$31,50\n");
                }

                Intent intent = new Intent(MainActivity.this, PrecoFinal.class);
                intent.putExtra("resumoPedido", resumoPedido.toString());
                intent.putExtra("precoTotal", precoTotal);
                startActivity(intent);
            }
        });

    }

    private int getQuantidadeFromEditText(EditText editText) {
        String quantidadeStr = editText.getText().toString();
        if (quantidadeStr.isEmpty()) {
            return 0;
        } else {
            return Integer.parseInt(quantidadeStr);
        }
    }
}