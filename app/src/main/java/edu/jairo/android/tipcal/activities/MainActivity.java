package edu.jairo.android.tipcal.activities;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import edu.jairo.android.tipcal.R;
import edu.jairo.android.tipcal.TipCalcApp;
import edu.jairo.android.tipcal.fragments.TipHistoryListFragment;
import edu.jairo.android.tipcal.fragments.TipHistoryListFragmentListener;
import edu.jairo.android.tipcal.models.TipRecord;

public class MainActivity extends AppCompatActivity {

    // Para poder utilizar el plugin de ButterKnife es necesario seleccionar el elemento R.layout.activity_main, luego alt + insert

    @Bind(R.id.inputPercentage)
    EditText inputPercentage;
    @Bind(R.id.inputBill)
    EditText inputBill;
    @Bind(R.id.txtTip)
    TextView txtTip;

    private TipHistoryListFragmentListener fragmentListener;

    private final static int TIP_STEP_CHANGE = 1;
    private final static int DEFAULT_TIP_PERCENTAGE = 10;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Con esto hago una inyeccion de todos los componentes que utilizo
        ButterKnife.bind(this);


        // Instanceamos el fragment
        TipHistoryListFragment fragment = (TipHistoryListFragment) getSupportFragmentManager().findFragmentById(R.id.fragmentList);
        // Para que guarde la instancia
        fragment.setRetainInstance(true);

        fragmentListener = (TipHistoryListFragmentListener)fragment;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_about) {
            about();
        }
        return super.onOptionsItemSelected(item);
    }

    // ButterKnife tambien me permite inyectar un evento click sobre un elemento button
    // con la siguiente notacion
    @OnClick(R.id.btnSubmit)
    public void handleClickSubmit() {
        Log.e(getLocalClassName(), "click en submit");
        // ButterKnife me implemeta un Listener sin necesidad que yo haga el <code></code>
        hideKeyboard();

        String strInputTotal = inputBill.getText().toString().trim();
        if (!strInputTotal.isEmpty()) {
            double total = Double.parseDouble(strInputTotal);
            int tipPercentage = getTipPercentage();

            TipRecord tipRecord = new TipRecord();
            tipRecord.setBill(total);
            tipRecord.setTipPercentage(tipPercentage);
            tipRecord.setTimestamp(new Date());




            String strTip = String.format(getString(R.string.global_message_tip), tipRecord.getTip());

            // Enviamos datos al fragmento
            fragmentListener.addToList(tipRecord);
            txtTip.setVisibility(View.VISIBLE);
            txtTip.setText(strTip);

        }
    }

    // Anotacion Onclick para button incrementar
    @OnClick(R.id.btnIncrease)
    public void handleClickIncrease(){
        // Esto oculta el teclado por si el usuario no quiere seguir ingresando datos, esto no se muestra en emulador
        hideKeyboard();
        handleTipChange(TIP_STEP_CHANGE);
    }

    // Anotacion Onclick para button decrementar
    @OnClick(R.id.btnDecrease)
    public void handleClickDecrease(){
        // Esto oculta el teclado por si el usuario no quiere seguir ingresando datos, esto no se muestra en emulador
        hideKeyboard();
        handleTipChange(-TIP_STEP_CHANGE);
    }

    @OnClick(R.id.btnClear)
    public void handleClear(){
        fragmentListener.clearList();
    }


    private void handleTipChange(int change){

        int tipPercentage = getTipPercentage();
        tipPercentage += change;
        if (tipPercentage > 0){
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
    }

    private int getTipPercentage() {
        int tipPercentage = DEFAULT_TIP_PERCENTAGE;
        String strInputTipPercentage = inputPercentage.getText().toString().trim();
        if (!strInputTipPercentage.isEmpty()){
            tipPercentage = Integer.parseInt(strInputTipPercentage);
        }else{
            inputPercentage.setText(String.valueOf(tipPercentage));
        }
        return tipPercentage;
    }

    private void hideKeyboard() {
        InputMethodManager inputManager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        try {
            inputManager.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), InputMethodManager.HIDE_NOT_ALWAYS);
        } catch (NullPointerException npe) {
            Log.e(getLocalClassName(), Log.getStackTraceString(npe));
        }
    }

    private void about() {
        TipCalcApp app = (TipCalcApp) getApplication();
        String strUrl = app.getAboutUrl();

        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(strUrl));
        startActivity(intent);
    }
}
