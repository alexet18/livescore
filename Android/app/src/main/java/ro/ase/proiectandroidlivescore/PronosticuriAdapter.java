package ro.ase.proiectandroidlivescore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


public class PronosticuriAdapter extends ArrayAdapter<Pronosticuri> {

    Context context;
    int resource;
    ArrayList<Pronosticuri> pr;
    String[] options = {"Marcheaza corect","Marcheaza incorect","Adauga Marcatori","Sterge"};

    public PronosticuriAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Pronosticuri> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        pr = objects;
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Pronosticuri p = pr.get(position);

        LayoutInflater inflater = LayoutInflater.from(context);
         View view = inflater.inflate(resource,parent,false);


        TextView team1Tv = view.findViewById(R.id.pronosticTeam1);
        TextView team2Tv = view.findViewById(R.id.pronosticTeam2);
        TextView verdictTv = view.findViewById(R.id.pronosticVerdict);

        team1Tv.setText(p.getTeam1());
        team2Tv.setText(p.getTeam2());
        if(p.getVerdict() == -1) {
            verdictTv.setText("N/A");
        }
        else
        {
            if(p.getVerdict() == 1)
            {
                verdictTv.setText("Corect");
            }
            else
            {
                verdictTv.setText("Incorect");
            }
        }


        if(p.getWinner() == 1)
            team1Tv.setTextColor(Color.GREEN);
        else
            team2Tv.setTextColor(Color.GREEN);

        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,String.valueOf(position),Toast.LENGTH_LONG).show();
                ArrayAdapter<String> adapter = new ArrayAdapter<String>( context, android.R.layout.select_dialog_item,options);
                AlertDialog.Builder builder = new AlertDialog.Builder(parent.getContext());
                builder.setTitle("Optiuni");
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                       switch (which) {
                           case 0:
                               Toast.makeText(context,String.valueOf(0),Toast.LENGTH_LONG).show();
                               RoomDB database =  RoomDB.getInstance(context);

                               database.pronosticuriDao().setRezultat(which+1,p.getMatchId());
                               verdictTv.setText("Corect");
                               break;
                           case 1:
                               RoomDB database2 =  RoomDB.getInstance(context);
                               Toast.makeText(context,String.valueOf(1),Toast.LENGTH_LONG).show();
                               database2.pronosticuriDao().setRezultat(which+1,p.getMatchId());
                               verdictTv.setText("Incorect");
                               break;
                               case 2:


                          Intent marcatori = new Intent(context,AdaugaMarcatoriActivity.class);
                          marcatori.putExtra("id",String.valueOf(p.getMatchId()));
                          context.startActivity(marcatori);

                               break;
                           case 3:
                               RoomDB database3 =  RoomDB.getInstance(context);
                               database3.pronosticuriDao().deletePronostic(p.getMatchId());
                               database3.jucatoriDao().delete(p.getMatchId());
                               database3.marcatoriDao().deleteById(p.getMatchId());
                              // pr.remove(position);

                               break;
                       }
                    }
                });
                final AlertDialog a = builder.create();
                a.show();

            }
        });
        

       return view;
    }


}
