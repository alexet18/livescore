package ro.ase.proiectandroidlivescore;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {


    static String state = "notlive";
    Spinner spinnerSports;
    ArrayAdapter adapter;
    String[] sports;
    ImageButton buton_live, buton_notLive,buton_profile,buton_pronosticuri,buton_rapoarte;
    public User user;
    final int REQUEST_CODE = 100;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buton_live = findViewById(R.id.imageButton2);
        buton_notLive = findViewById(R.id.imageButton1);
        spinnerSports = findViewById(R.id.spinnerSports);
        buton_profile = findViewById(R.id.imageButton5);
        buton_pronosticuri = findViewById(R.id.imageButton4);
        buton_rapoarte = findViewById(R.id.imageButton3);
        sports = getResources().getStringArray(R.array.spinnerSports);
        adapter = new ArrayAdapter(this, android.R.layout.simple_spinner_dropdown_item,sports);
        spinnerSports.setAdapter(adapter);
        Intent intent_login = getIntent();
        user = (User) intent_login.getSerializableExtra("User");

      spinnerSports.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                  @Override
                                                  public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                                                      if(spinnerSports.getSelectedItemPosition()==0)
                                                          getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Fotbal()).commit();
                                                      if(spinnerSports.getSelectedItemPosition()==1)
                                                          getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Tenis()).commit();
                                                      if(spinnerSports.getSelectedItemPosition()==2)
                                                      getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Baschet()).commit();
                                                      if(spinnerSports.getSelectedItemPosition()==3)
                                                          getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Volei()).commit();
                                                  }

                                                  @Override
                                                  public void onNothingSelected(AdapterView<?> parent) {

                                                  }
                                              });



      buton_live.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              state = "live";
              if(spinnerSports.getSelectedItemPosition()==0)
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Fotbal()).commit();
              if(spinnerSports.getSelectedItemPosition()==1)
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Tenis()).commit();
              if(spinnerSports.getSelectedItemPosition()==2)
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Baschet()).commit();
              if(spinnerSports.getSelectedItemPosition()==3)
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Volei()).commit();




          }
      });



      buton_notLive.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              state = "notlive";
              if(spinnerSports.getSelectedItemPosition()==0)
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Fotbal()).commit();
              if(spinnerSports.getSelectedItemPosition()==1)
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Tenis()).commit();
              if(spinnerSports.getSelectedItemPosition()==2)
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Baschet()).commit();
              if(spinnerSports.getSelectedItemPosition()==3)
                  getSupportFragmentManager().beginTransaction().replace(R.id.frameID, new Volei()).commit();

          }
      });


      buton_rapoarte.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {
              Intent rapoarte = new Intent(getApplicationContext(),RapoarteActivity.class);
              startActivity(rapoarte);
          }
      });

buton_profile.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent profile = new Intent(getApplicationContext(),MyProfileActivity.class);
        profile.putExtra("user",user);
        startActivityForResult(profile,REQUEST_CODE);

    }
});


buton_pronosticuri.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent pronosticuri = new Intent(getApplicationContext(),PronosticuriActivity.class);
        startActivity(pronosticuri);
    }
});
    };


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == REQUEST_CODE && resultCode == Activity.RESULT_OK && data!=null)
        {

            user = (User)data.getSerializableExtra("user_changed");
        }
    }
}







