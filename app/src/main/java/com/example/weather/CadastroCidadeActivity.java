package com.example.weather;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.example.weather.models.CidadeModel;

public class CadastroCidadeActivity extends AppCompatActivity {

    private CidadeModel cidade;

    private EditText editTextCidade;
    private Button btnSalvarCidade;
    private ImageButton btnVoltarHome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_cidade);

        editTextCidade = (EditText) findViewById(R.id.editTextCidade);
        btnSalvarCidade = (Button) findViewById(R.id.btnSalvarCidade);
        btnVoltarHome = (ImageButton) findViewById(R.id.btnVoltarHome);

        this.btnSalvarCidade.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //Salvar Cidade no DB

            }
        });

        this.btnVoltarHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CadastroCidadeActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }

    private CidadeModel getCidadeFormulario(){
        this.cidade = new CidadeModel();

        if(this.cidade.getName().isEmpty() == false){
            cidade.setName(this.editTextCidade.toString());
            return cidade;
        }else{
            System.out.println("Cidade inválida");
            return null;
        }

    }

}
