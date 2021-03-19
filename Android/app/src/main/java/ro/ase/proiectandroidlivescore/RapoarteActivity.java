package ro.ase.proiectandroidlivescore;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.Switch;
import android.widget.TextView;

import java.util.List;

public class RapoarteActivity extends AppCompatActivity {


    TextView raport1,raport2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rapoarte);

        raport1 = findViewById(R.id.raport1procent);
       raport2 = findViewById(R.id.raport2);

       int voleiTotal,voleiReusit,fotbalTotal,fotbalReusit,baschetTotal,baschetReusit;
        double procentVolei,procentBaschet,procentFotbal = 0;

        RoomDB database = RoomDB.getInstance(getApplicationContext());

        List<Pronosticuri> pronosticuri = database.pronosticuriDao().selectall();

        int pronosticuriCorecte = 0;
        fotbalReusit = fotbalTotal = voleiTotal = voleiReusit = baschetTotal = baschetReusit = 0;

        for(Pronosticuri p : pronosticuri)
        {



            if(p.getVerdict()==1)
            {
                pronosticuriCorecte++;


                switch (p.getSport()){
                    case "fotbal":
                        fotbalReusit++;
                        fotbalTotal++;
                        break;
                    case "baschet":
                        baschetReusit++;
                        baschetTotal++;
                        break;
                    case "volei":
                        voleiTotal++;
                        voleiReusit++;
                        break;
                }

            }


            if(p.getVerdict()==2)
            {


                switch (p.getSport()){
                    case "fotbal":

                        fotbalTotal++;
                        break;
                    case "baschet":

                        baschetTotal++;
                        break;
                    case "volei":
                        voleiTotal++;

                        break;
                }

            }
        }

        double fractie = ((double)pronosticuriCorecte)/pronosticuri.size();



        raport1.setText(String.valueOf(fractie*100)+"%");



        procentBaschet = ((double)baschetReusit)/baschetTotal;
        procentFotbal = ((double)fotbalReusit)/fotbalTotal;
        procentVolei = ((double)voleiReusit)/voleiTotal;



        if(procentBaschet>procentFotbal)
        {
            if(procentBaschet>procentVolei)
            {
                raport2.setText("Baschet");
            }
            else
            {
                raport2.setText("Volei");
            }
        }
        else
        {
            if(procentFotbal>procentVolei)
            {
                raport2.setText("Fotbal");
            }
            else
            {
                raport2.setText("Volei");
            }
        }





    }
}