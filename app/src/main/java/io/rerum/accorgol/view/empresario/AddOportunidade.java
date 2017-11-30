package io.rerum.accorgol.view.empresario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;

import com.beardedhen.androidbootstrap.BootstrapButton;

import io.rerum.accorgol.R;
import io.rerum.accorgol.controller.CarreiraService;
import io.rerum.accorgol.controller.FirebaseHelper;
import io.rerum.accorgol.controller.OportunidadeService;
import io.rerum.accorgol.model.Oportunidade;
import io.rerum.accorgol.view.empresario.fragments.ComOportunidadeFragment;
import io.rerum.accorgol.view.empresario.fragments.SemOportunidadeFragment;
import io.rerum.accorgol.view.jogador.fragments.ComConquistaFragment;

/**
 * Created by osman on 31/10/2017.
 */

public class AddOportunidade extends AppCompatActivity {

    private Spinner posicao;
    private Spinner altura;
    private EditText ano;
    private EditText cidade;
    private Spinner pe;
    private Spinner estado;
    private BootstrapButton button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionaroportunidade);
        posicao = (Spinner) findViewById(R.id.addOportunidadePosicao);
        altura = (Spinner) findViewById(R.id.addOportunidadeAltura);
        ano = (EditText) findViewById(R.id.addOportunidadeAno);
        cidade = (EditText) findViewById(R.id.addOportunidadeCidade);
        pe = (Spinner) findViewById(R.id.addOportunidadePe);
        estado = (Spinner) findViewById(R.id.addOportunidadeEstado);
        button = (BootstrapButton) findViewById(R.id.addOportunidadeButton);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Oportunidade oportunidade = new Oportunidade(
                        posicao.getSelectedItem().toString(),
                        altura.getSelectedItem().toString(),
                        ano.getText().toString(),
                        cidade.getText().toString(),
                        pe.getSelectedItem().toString(),
                        estado.getSelectedItem().toString());
                String id = new FirebaseHelper().recuperar(getApplicationContext(), String.valueOf(R.string.id_Usuario));
                new OportunidadeService().addOportunidade(oportunidade, "Empresarios/" + id + "/Oportunidades/", getApplicationContext());
                finish();
            }
        });

    }

    public void voltarTela(View view) {
        this.finish();
    }
}
