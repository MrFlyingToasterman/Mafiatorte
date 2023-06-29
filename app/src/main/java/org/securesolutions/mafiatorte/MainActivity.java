package org.securesolutions.mafiatorte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // Globale Variablen
    public static int AnzahlAngebote = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Automatisch Tastatur starten mit Fokus auf Anzahl Eingabefeld
        EditText AnzahlInput = findViewById(R.id.editTextNumber);
        AnzahlInput.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void goToAnActivity(View view) {
        // Öffne nächstes Fenster
        // Lese Wert von Eingabebox
        EditText AnzahlInput = findViewById(R.id.editTextNumber);
        int Anzahl = 0;
        try {
            Anzahl = Integer.parseInt(AnzahlInput.getText().toString());
        } catch (Exception e) {
            AnzahlInput.setError("Dieses Feld ist erforderlich!");
        }
        // Prüfe ob Eingabe mindestens 2
        if(Anzahl > 1) {
            // Eingabe ist mehr als 2
            AnzahlAngebote = Anzahl;
            // Starte neue View
            Intent intent = new Intent(this, PizzaRund.class);
            startActivity(intent);
        } else {
            // Eingabe ist weniger als 2
            toastThis("Bitte geben Sie mindestens einen Wert von 2 ein!");
        }

    }

    public void toastThis(String ToastText) {
        Toast.makeText(MainActivity.this, ToastText,
                Toast.LENGTH_LONG).show();
    }


}