package org.securesolutions.mafiatorte;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.widget.TextView;
import android.view.View;

public class Auswertung extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auswertung);
        // Regel für Double Nummer Formatierung
        NumberFormat n = NumberFormat.getInstance();
        n.setMaximumFractionDigits(2);
        // Setze Endgültiges Ergebnis in Textlabel ein
        TextView ResultatLabel = findViewById(R.id.Resultat);
        ResultatLabel.setText("Das Angebot Nummer " + (PizzaRund.AngebotID+1) + " zum Preis von " + n.format(PizzaRund.besterPreis) + "€ / cm² ist das Beste!");
    }

    public void restart(View view) {
        // Starte neue View
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}