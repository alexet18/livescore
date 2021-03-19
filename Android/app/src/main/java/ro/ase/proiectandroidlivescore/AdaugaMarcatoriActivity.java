package ro.ase.proiectandroidlivescore;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import java.util.ArrayList;
import java.util.List;

public class AdaugaMarcatoriActivity extends AppCompatActivity {



    ListView list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adauga_marcatori);

        Intent data;

        data = getIntent();


        int id = Integer.parseInt(data.getStringExtra("id"));

        List<Marcatori> marcatori;
        List<Jucatori> jucatori;
        ArrayList<String> numeJucatori = new ArrayList<String>();


        RoomDB database = RoomDB.getInstance(getApplicationContext());

        marcatori = database.marcatoriDao().selectall();
        jucatori = database.jucatoriDao().select(id);

        for(Jucatori j :jucatori)
        {
            numeJucatori.add(j.getNume());
        }

        MarcatoriAdapter adapter = new MarcatoriAdapter(getApplicationContext(),R.layout.marcatori_view,numeJucatori,id);

        list = findViewById(R.id.listviewMarcatori);

        list.setAdapter(adapter);




    }
}