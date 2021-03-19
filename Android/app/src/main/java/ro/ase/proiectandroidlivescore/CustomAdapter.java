package ro.ase.proiectandroidlivescore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.ViewHolder>{


    private List<Meci> matches;
    private Context context;
    final String[] options = {"Adauga pronostic","Vezi raport echipe","Vezi raport jucatori"};

    public CustomAdapter(List<Meci> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
         Meci match = matches.get(position);
         holder.team1Tv.setText(match.getTeam1());
        holder.team2Tv.setText(match.getTeam2());
        holder.matchTypeTv.setText(match.getType());
        holder.leagueTv.setText(match.getLeague());
        holder.dateTv.setText(match.getDate());



        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(context,"CLICK",Toast.LENGTH_LONG).show();
               /* ArrayAdapter<String> adapter = new ArrayAdapter<String>( context, android.R.layout.select_dialog_item,options);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Optiuni");
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                final AlertDialog a = builder.create();
                a.show();*/
            }
        });
    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {


        TextView team1Tv,team2Tv,matchTypeTv,leagueTv,dateTv;
        CardView cardView;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
          team1Tv=  itemView.findViewById(R.id.team1FotbalTv);
            team2Tv=itemView.findViewById(R.id.team2FotbalTv);
            matchTypeTv=itemView.findViewById(R.id.matchType);
            leagueTv = itemView.findViewById(R.id.leagueTv);
            dateTv = itemView.findViewById(R.id.dateTv);
            cardView = itemView.findViewById(R.id.cardViewFotbal);
        }
}
}
