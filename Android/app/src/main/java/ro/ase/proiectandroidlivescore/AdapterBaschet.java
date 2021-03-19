package ro.ase.proiectandroidlivescore;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterBaschet extends RecyclerView.Adapter<AdapterBaschet.ViewHolder> {

    List<MeciBaschet> matches;
    Context context;
    private int images[] = {R.drawable.lakers,R.drawable.miamiheat,R.drawable.toronto,R.drawable.sanantonio,R.drawable.cleveland,R.drawable.portland};
    final String[] options = {"Adauga pronostic"};


    public AdapterBaschet(List<MeciBaschet> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }


    public AdapterBaschet.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_baschet, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MeciBaschet m = matches.get(position);

        holder.team1Tv.setText(m.getTeam1());
        holder.team2Tv.setText(m.getTeam2());
        holder.date.setText(m.getDate());
        holder.time.setText(m.getTime());
        holder.team1Img.setImageResource(images[m.getCod_img1()]);
        holder.team2Img.setImageResource(images[m.getCod_img2()]);
        holder.scor1Tv.setText(String.valueOf(m.getScor1()));
        holder.scor2Tv.setText(String.valueOf(m.getScor2()));

        if(m.getScor1()!=-1 && m.getScor2() !=-2)
        {


            holder.scor1Tv.setText(String.valueOf(m.getScor1()));
            holder.scor2Tv.setText(String.valueOf(m.getScor2()));

            if(m.getScor2() == m.getScor1())
            {
                holder.scor1Tv.setTextColor(Color.BLACK);
                holder.scor2Tv.setTextColor(Color.BLACK);

            }
            else
            {
                if(m.getScor1()>m.getScor2())
                {
                    holder.scor1Tv.setTextColor(Color.GREEN);
                    holder.scor2Tv.setTextColor(Color.RED);
                }
                else
                {
                    holder.scor2Tv.setTextColor(Color.GREEN);
                    holder.scor1Tv.setTextColor(Color.RED);
                }
            }

        }

        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayAdapter<String> adapter = new ArrayAdapter<String>( context, android.R.layout.select_dialog_item,options);
                AlertDialog.Builder builder = new AlertDialog.Builder(context);
                builder.setTitle("Optiuni");
                builder.setAdapter(adapter, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        switch (which){
                            case 0:




                                ArrayAdapter<String> adapter2 = new ArrayAdapter<String>( context, android.R.layout.select_dialog_item, new String[]{matches.get(position).getTeam1(),
                                        matches.get(position).getTeam2()});
                                AlertDialog.Builder builder2 = new AlertDialog.Builder(context);
                                builder2.setTitle("Alege castigator");
                                builder2.setAdapter(adapter2, new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        switch (which){
                                            case 0:
                                                RoomDB database =  RoomDB.getInstance(context);
                                                //Toast.makeText(context,matches.get(position).getTeam1(),Toast.LENGTH_LONG).show();
                                                database.pronosticuriDao().insert(new Pronosticuri(matches.get(position).getId(),"baschet",matches.get(position).getTeam1(),matches.get(position).getTeam2(),1));
                                                for(String s :matches.get(position).getPlayersTeam1())
                                                {
                                                    database.jucatoriDao().insert(new Jucatori(matches.get(position).getId(),s));
                                                }for(String s :matches.get(position).getPlayersTeam2())
                                            {
                                                database.jucatoriDao().insert(new Jucatori(matches.get(position).getId(),s));
                                            }
                                                break;
                                            case 1:
                                                RoomDB database2 =  RoomDB.getInstance(context);
                                                database2.pronosticuriDao().insert(new Pronosticuri(matches.get(position).getId(),"baschet",matches.get(position).getTeam1(),matches.get(position).getTeam2(),2));
                                                for(String s :matches.get(position).getPlayersTeam1())
                                                {
                                                    database2.jucatoriDao().insert(new Jucatori(matches.get(position).getId(),s));
                                                }for(String s :matches.get(position).getPlayersTeam2())
                                            {
                                                database2.jucatoriDao().insert(new Jucatori(matches.get(position).getId(),s));
                                            }
                                                break;

                                        }
                                    }
                                });
                                final AlertDialog a2 = builder2.create();
                                a2.show();




                                break;
                        }
                    }
                });
                final AlertDialog a = builder.create();
                a.show();
            }
        });

    }


    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView team1Img,team2Img;
        TextView date,time,team1Tv,team2Tv,scor1Tv,scor2Tv;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            team1Tv = (TextView)itemView.findViewById(R.id.team1BaschetTv);
            team2Tv = itemView.findViewById(R.id.team2BaschetTv);
            team1Img = itemView.findViewById(R.id.team1Baschet);
            team2Img = itemView.findViewById(R.id.team2Baschet);
            date = itemView.findViewById(R.id.dateBaschet);
            time = itemView.findViewById(R.id.timeBaschet);
            cardView =  itemView.findViewById(R.id.cardViewBaschet);
            scor1Tv = itemView.findViewById(R.id.scor1Baschet);
            scor2Tv = itemView.findViewById(R.id.scor2Baschet);
        }
    }
}