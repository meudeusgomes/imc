package com.example.imc;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

    // DECLARAÇÃO DE VARIÁVEIS
    private EditText pesoInput, alturaInput;
    private Button calculateButton, clearButton, closeButton;
    private TextView resultText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);



        // RETIRA A BARRA DE STATUS VISÍVEL
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_main); // DEFINE O LAYOUT DO MAIN ACTIVITY

        // INICIA E VINCULA AS VARIÁVEIS
        pesoInput = findViewById(R.id.pesoInput);
        alturaInput = findViewById(R.id.alturaInput);
        calculateButton = findViewById(R.id.calculateButton);
        resultText = findViewById(R.id.resultText);
        clearButton = findViewById(R.id.tabelaImcButton);
        closeButton = findViewById(R.id.closeButton);


        // CONFIGURAÇÃO DO BOTÃO CALCULAR
        calculateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                calculateIMC();
            }
        });

        // CONFIGURAÇÃO DO BOTÃO LIMPAR DADOS
        clearButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // LIMPA OS CAMPOS INPUT, RESULTADO E TEXTO DE RESULTADO
                pesoInput.setText("");
                alturaInput.setText("");
                resultText.setText("");

                // TOAST BOTÃO LIMPAR DADOS
                Toast.makeText(MainActivity.this, "LIMPANDO DADOS", Toast.LENGTH_SHORT).show();
            }
        });

        // CONFIGURAÇÃO DO BOTÃO FECHAR
        closeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // METODO PARA CALCULAR IMC
    private void calculateIMC() {
        String pesoStr = pesoInput.getText().toString();
        String alturaStr = alturaInput.getText().toString();

        if (pesoStr.isEmpty() || alturaStr.isEmpty()) {
            // SE OUVER CAMPOS VAZIOS EXIBE TOAST
            Toast.makeText(this, "POR FAVOR PREENCHER TODOS OS CAMPOS!", Toast.LENGTH_SHORT).show();
            return;
        }

        double peso = Double.parseDouble(pesoStr);
        double altura = Double.parseDouble(alturaStr);
        double imc = peso / (altura * altura);

        String classification;

        // CLASSIFICAÇÃO PARA BASE DO CÁLCULO
        if (imc < 18.5) {
            classification = "ABAIXO DO PESO";
        } else if (imc < 24.9) {
            classification = "PESO NORMAL";
        } else if (imc < 29.9) {
            classification = "SOBREPESO";
        } else if (imc < 34.9) {
            classification = "OBESIDADE GRAU 1";
        } else if (imc < 39.9) {
            classification = "OBESIDADE GRAU 2";
        } else {
            classification = "OBESIDADE GRAU 3 OU MÓRBIDA";
        }

        // TEXTVIEW RESULTADO
        resultText.setText("SEU IMC É " + String.format("%.2f", imc) + "\nCLASSIFICAÇÃO: " + classification);
    }
}
