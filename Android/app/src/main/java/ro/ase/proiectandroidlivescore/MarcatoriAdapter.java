package ro.ase.proiectandroidlivescore;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;




public class MarcatoriAdapter extends ArrayAdapter<String> {


    Context context;
    int resource;
    List<String> marcatori;
    int id;

    List<Marcatori> lista;

    public MarcatoriAdapter(@NonNull Context context, int resource, @NonNull List<String> objects,int id) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
        this.marcatori = objects;

        RoomDB databse = RoomDB.getInstance(context);
        lista = databse.marcatoriDao().selectall();
        this.id = id;



    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {


        String m = marcatori.get(position);
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(resource,parent,false);


        TextView nume = view.findViewById(R.id.numeJucator);
        nume.setText(m);

        RoomDB database2 = RoomDB.getInstance(context);
        List<Jucatori> jucatori = database2.jucatoriDao().select(id);

        for(Marcatori mr : lista)
        {
            if(mr.getPlayername().equals(m) && mr.getMatchId() == id)
            {
                nume.setTextColor(Color.GREEN);
            }

        }

           view.setOnClickListener(new View.OnClickListener() {
               @Override
               public void onClick(View v) {
                   RoomDB database = RoomDB.getInstance(context);





                   if(nume.getCurrentTextColor()== Color.GREEN)
                   {

                       database.marcatoriDao().delete(id,m);
                       nume.setTextColor(Color.BLACK);

                   }
                   else
                   {nume.setTextColor(Color.GREEN);
                       database.marcatoriDao().insert(new Marcatori(id,m));
                   }
               }
           });


             return view;


    }
}
