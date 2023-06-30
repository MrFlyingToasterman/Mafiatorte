package org.securesolutions.mafiatorte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class PizzaRund extends AppCompatActivity {

    // Globale Variablen
    public int Anzahl = 0;
    public int AItterator = 0;
    public int Durchmesser = 0;
    public double Preis = 0;
    public static double besterPreis = 9999999;
    public static int AngebotID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pizza_rund);
        // Setze Gesamtzahl
        Anzahl = MainActivity.AnzahlAngebote;
        // Setze Gesamtanzahl in UI
        TextView AnzahlAnzeige = findViewById(R.id.AngeboteLabel);
        AnzahlAnzeige.setText("" + Anzahl);
        // Automatisch Tastatur starten mit Fokus auf Durchmesser Eingabefeld
        EditText DuchmesserBox = findViewById(R.id.editDurchmesser);
        DuchmesserBox.requestFocus();
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
    }

    public void auswertung(View view) {
        EditText DuchmesserBox = findViewById(R.id.editDurchmesser);
        EditText PreisBox = findViewById(R.id.editPreis);
        TextView AngebotItLabel = findViewById(R.id.AngebotItterator);
        // Berechne Angebote nur solange die Anzahl an Angeboten nicht erreicht wurde
        if (AItterator < Anzahl) {
            // Werte Durchmesser aus
            try {
                Durchmesser = Integer.parseInt(DuchmesserBox.getText().toString());
            } catch (Exception e) {
                DuchmesserBox.setError("Dieses Feld ist erforderlich!");
            }
            // Werte Preis aus
            try {
                Preis = Double.parseDouble(PreisBox.getText().toString());
            } catch (Exception e) {
                PreisBox.setError("Dieses Feld ist erforderlich!");
            }
            // Prüfe ob Durchmesser mindestens 1 und Preis mindestens 10 Cent
            if (Durchmesser <= 1 || Preis < 1) {
                // Zeige Fehler an
                toastThis("Fehler: Falsche Werte!\nBitte Eingaben überprüfen!");
                // Variabeln leeren
                Durchmesser = 0;
                Preis = 0;
            } else {
                // Berechne Flächeninhalt in cm²
                double radius = Durchmesser / 2;
                double Ergebnis = Math.PI * Math.pow(radius, 2);
                // Preis pro cm² berechnen
                double ppcm = Preis / Ergebnis;
                // Prüfe ob dieses Angebot das beste ist
                if (ppcm <= besterPreis) {
                    // Das Angebot ist das Beste
                    // Schreibe den globalen Preis uns den jetzigen Itterator
                    besterPreis = ppcm;
                    AngebotID = AItterator;
                }
                // Leere Eingabeboxen
                DuchmesserBox.setText("");
                PreisBox.setText("");
                // Leere Variabeln
                Durchmesser = 0;
                Preis = 0;
                // Ausgabe via Toast mit detailierten Konditionen
                toastThis("Angebot Nummer " + (AItterator + 1) + " beträgt " + ppcm + "€ pro cm²");
                // Zähle Angebot
                AItterator++;
                // Setze Focus auf Durchmesser
                DuchmesserBox.requestFocus();
                getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_VISIBLE);
                if (AItterator >= Anzahl) {
                    // Diable TextEdits
                    disableEditText(PreisBox);
                    disableEditText(DuchmesserBox);
                    // Alle Angebote berechnet - Zeige Auswertung auf neuer Seite
                    Intent intent = new Intent(this, Auswertung.class);
                    startActivity(intent);
                } else {
                    // Schreibe aktuelle AngebotID in UI
                    AngebotItLabel.setText("" + (AItterator+1));
                }
            }

        } else {
            // Button Click erneut
            // Alle Angebote berechnet - Zeige Auswertung auf neuer Seite
            Intent intent = new Intent(this, Auswertung.class);
            startActivity(intent);
        }
    }

    public void toastThis(String ToastText) {
        Toast.makeText(PizzaRund.this, ToastText,
                Toast.LENGTH_LONG).show();
    }

    private void disableEditText(EditText editText) {
        editText.setFocusable(false);
        editText.setEnabled(false);
        editText.setCursorVisible(false);
        editText.setKeyListener(null);
    }


}