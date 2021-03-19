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


public class Volei extends Fragment {

    List<MeciVolei> matches;
    List<MeciVolei> liveMatches;
    List<MeciVolei> notLiveMatches;
    RecyclerView recyclerView;
    AdapterVolei adapter;
    ProgressDialog pDialog;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootview = inflater.inflate(R.layout.fragment_volei, container, false);

           matches = new ArrayList<MeciVolei>();
           liveMatches = new ArrayList<MeciVolei>();
           notLiveMatches = new ArrayList<MeciVolei>();

           NetworkVolei meciuriJson = new NetworkVolei(){
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
                    matches.addAll(NetworkVolei.mVolei);

                   try {
                       filter_matches();
                   } catch (ParseException e) {
                       e.printStackTrace();
                   }

                   recyclerView = (RecyclerView)rootview.findViewById(R.id.recycler_volei);

                   recyclerView.setHasFixedSize(true);
                   recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


                   if(MainActivity.state == "live")
                   {
                       adapter = new AdapterVolei(liveMatches, getContext());
                   }
                   else
                   {
                       adapter = new AdapterVolei(matches,getContext());
                   }
                   recyclerView.setAdapter(adapter);

                   pDialog.cancel();

               }
           };


        try {
            meciuriJson.execute(new URL("https://pastebin.com/raw/nDA5gbpE"));
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return rootview;
    }

    private void filter_matches() throws ParseException {

        long ONE_MINUTE_IN_MILLIS=60000;
        for(int i=0;i<matches.size();i++) {
            MeciVolei m = matches.get(i);



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


    private void read_json() {
        String json;
        try {
            InputStream stream = getActivity().getAssets().open("UpcomingMatches.json");
            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();

            json = new String(buffer,"UTF-8");
            JSONObject parent = new JSONObject(json);
            JSONArray jsonArray = parent.getJSONArray("voleiMatches");

            for(int i=0;i<jsonArray.length();i++)
            {

                JSONObject object = jsonArray.getJSONObject(i);
                int cod_imagine1 = Integer.parseInt(object.getString("codimg1"));
                int cod_imagine2 = Integer.parseInt(object.getString("codimg2"));
                MeciVolei m = new MeciVolei(object.getString("team1"),
                        object.getString("team2"),
                        object.getString("type"),
                        object.getString("league"),
                        object.getString("date"),
                        object.getString("time"),
                        cod_imagine1,cod_imagine2);
                matches.add(m);


            }


        } catch (IOException | JSONException e) {

            e.printStackTrace();
        }
    }
}





