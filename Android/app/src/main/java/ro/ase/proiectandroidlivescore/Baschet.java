package ro.ase.proiectandroidlivescore;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;


public class Baschet extends Fragment {

    List<MeciBaschet> matches;
    List<MeciBaschet> liveMatches;
    List<MeciBaschet> notLiveMatches;
    RecyclerView recyclerView;
    AdapterBaschet adapter;
    ProgressDialog pDialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_baschet, container, false);

        matches = new ArrayList<MeciBaschet>();
        liveMatches = new ArrayList<MeciBaschet>();
        notLiveMatches = new ArrayList<MeciBaschet>();

        NetworkBaschet meciuriJson =  new NetworkBaschet(){


            @Override
            protected void onPreExecute() {
                super.onPreExecute();

                pDialog = new ProgressDialog(rootview.getContext());
                pDialog.setMessage("Please wait...");

                pDialog.setCancelable(false);
                pDialog.show();
            }

            @Override
            protected void onPostExecute(String s) {

                matches.addAll(NetworkBaschet.mBaschet);
                try {
                    filter_matches();
                } catch (ParseException e) {
                    e.printStackTrace();
                }

                recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_baschet);

                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                if(MainActivity.state == "live")
                {
                    adapter = new AdapterBaschet(liveMatches, getContext());
                }
                else
                {
                    adapter = new AdapterBaschet(matches,getContext());
                }
                recyclerView.setAdapter(adapter);

                pDialog.cancel();
            };
        };

        try{
            meciuriJson.execute(new URL("https://pastebin.com/raw/nDA5gbpE"));
        }
        catch (MalformedURLException e) {
            e.printStackTrace();
        }







        return rootview;
    }

    private void filter_matches() throws ParseException {

        long ONE_MINUTE_IN_MILLIS=60000;
        for(int i=0;i<matches.size();i++) {
            MeciBaschet m = matches.get(i);



            Calendar c = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
            //String currentTime = sdf.format(c.getTime());
            Date currentTime = new Date(System.currentTimeMillis());
            String stringCurrentTime = sdf.format(currentTime);
            currentTime = sdf.parse(stringCurrentTime);

            String matchTime = m.getDate() +" " + m.getTime();


            Date matchDate = sdf.parse(matchTime);

            c.setTime(matchDate);
            c.add(Calendar.MINUTE,130);

            Date targetTime = c.getTime();


            if(currentTime.after(matchDate) && currentTime.before(targetTime))
            {
                liveMatches.add(m);
            }
            else
            {
                notLiveMatches.add(m);
            }



        }
    }



}





