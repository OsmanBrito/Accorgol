package io.rerum.accorgol.view.empresario;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import io.rerum.accorgol.R;

/**
 * Created by osman on 31/10/2017.
 */

public class AddOportunidade extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.adicionaroportunidade);
    }

    public void voltarTela(View view) {
        this.finish();
    }
}
