package ro.ase.proiectandroidlivescore;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class AdapterTenis extends RecyclerView.Adapter<AdapterTenis.ViewHolder> {

    List<MeciTenis> matches;
    Context context;
    private int images[] = {R.drawable.federer,R.drawable.nadal,R.drawable.djokovic,R.drawable.murray,R.drawable.halep,R.drawable.sharapova};


    public AdapterTenis(List<MeciTenis> matches, Context context) {
        this.matches = matches;
        this.context = context;
    }


    public AdapterTenis.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.row_tenis, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        MeciTenis m = matches.get(position);

        holder.team1Tv.setText(m.getTeam1());
        holder.team2Tv.setText(m.getTeam2());
        holder.date.setText(m.getDate());
        holder.time.setText(m.getTime());
        holder.team1Img.setImageResource(images[m.getCod_img1()]);
        holder.team2Img.setImageResource(images[m.getCod_img2()]);
    }


    @Override
    public int getItemCount() {
        return matches.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView team1Img,team2Img;
        TextView date,time,team1Tv,team2Tv;
        CardView cardView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            team1Tv = (TextView)itemView.findViewById(R.id.team1TenisTv);
            team2Tv = itemView.findViewById(R.id.team2TenisTv);
            team1Img = itemView.findViewById(R.id.team1Tenis);
            team2Img = itemView.findViewById(R.id.team2Tenis);
            date = itemView.findViewById(R.id.dateTenis);
            time = itemView.findViewById(R.id.timeTenis);
            cardView =  itemView.findViewById(R.id.cardViewTenis);
        }
    }
}