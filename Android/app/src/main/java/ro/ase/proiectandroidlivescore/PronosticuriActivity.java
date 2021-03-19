package ro.ase.proiectandroidlivescore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class PronosticuriActivity extends AppCompatActivity {



    ListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pronosticuri);

        lv=findViewById(R.id.listviewPronosticuri);

        List<Pronosticuri> pr_buffer;
        ArrayList<Pronosticuri> pr;

        RoomDB database = RoomDB.getInstance(getApplicationContext());
        pr_buffer = database.pronosticuriDao().selectall();

        pr = (ArrayList<Pronosticuri>)pr_buffer;




        PronosticuriAdapter adapter = new PronosticuriAdapter(getApplicationContext(),R.layout.pronosticuri_view,pr);
        lv.setAdapter(adapter);

    }
}