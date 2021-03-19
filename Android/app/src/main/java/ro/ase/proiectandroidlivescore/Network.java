

        package ro.ase.proiectandroidlivescore;


        import android.os.AsyncTask;
        import android.util.Log;

        import org.json.JSONArray;
        import org.json.JSONException;
        import org.json.JSONObject;

        import java.io.BufferedReader;
        import java.io.IOException;
        import java.io.InputStream;
        import java.io.InputStreamReader;
        import java.net.HttpURLConnection;
        import java.net.URL;
        import java.util.ArrayList;
        import java.util.Date;
        import java.util.List;

        public class Network extends AsyncTask<URL, Void, String> {




    public MeciFotbal fotbal;
    public MeciBaschet bascket;
    public MeciVolei volei;
    public MeciTenis tenis;

    public static ArrayList<MeciFotbal> mFotbal;
    public ArrayList<MeciBaschet> mBaschet;
    public ArrayList<MeciVolei> mVolei;
    public ArrayList<MeciTenis> mTenis;



    @Override
    protected String doInBackground(URL... urls) {
        HttpURLConnection conn = null;
        try {

            conn = (HttpURLConnection)urls[0].openConnection();
            conn.setRequestMethod("GET");
            InputStream ist = conn.getInputStream();
            InputStreamReader isr = new InputStreamReader(ist);
            BufferedReader br = new BufferedReader(isr);

            String sbuf = "";
            String linie = "";
            while ((linie = br.readLine()) != null){
                sbuf += linie + "\n";
            }

            loadJSONObject(sbuf);

            return sbuf;

        } catch (Exception ex) {
            Log.e("doInBackground", ex.getMessage());
        }
        finally {
            if (conn != null){
                conn.disconnect();
            }
        }
        return null;
    }

    public void loadJSONObject(String jsonStr){
        if (jsonStr != null){
            try {

                JSONObject parent = null;
                try {
                    parent = new JSONObject(jsonStr);
                }  catch (JSONException e) {
                    e.printStackTrace();
                }


                JSONArray jsonArray = parent.getJSONArray("fotbalMatches");
                 mFotbal = new ArrayList<>();
                for(int i=0;i<jsonArray.length();i++)
                {

                    JSONObject object = jsonArray.getJSONObject(i);
                    int id = Integer.parseInt(object.getString("matchId"));
                    int cod_imagine1 = Integer.parseInt(object.getString("codimg1"));
                    int cod_imagine2 = Integer.parseInt(object.getString("codimg2"));
                    MeciFotbal m = new MeciFotbal(id,object.getString("team1"),
                            object.getString("team2"),
                            object.getString("type"),
                            object.getString("league"),
                            object.getString("date"),
                            object.getString("time"),
                            cod_imagine1,cod_imagine2,
                            Integer.parseInt(object.getString("score1")),
                            Integer.parseInt(object.getString("score2")));
                    JSONArray roster = object.getJSONArray("roster");
                    JSONObject team = roster.getJSONObject(0);
                    Log.println(Log.INFO,"progres","inainte de foruri");
                    for(int j = 1;j<12;j++) {


                        String player = team.getString("player"+String.valueOf(j));
                        Log.println(Log.INFO,"player",player);
                        m.addPlayers(1,player);
                    }

                    team = roster.getJSONObject(1);
                    for(int j = 1;j<12;j++) {


                        String player = team.getString("player"+String.valueOf(j));
                        m.addPlayers(2,player);
                    }
                    mFotbal.add(m);


                }

            } catch (JSONException e){
                e.printStackTrace();
            }

        } else {
            Log.e("loadJSONObject", "Couldn't get any data from the url");
        }
    }
}