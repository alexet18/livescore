package ro.ase.proiectandroidlivescore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

public class MyProfileActivity extends AppCompatActivity {


    Intent intent;
    EditText username,pass,email;
    Button save;
    CheckBox checkbox,battery;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        username = findViewById(R.id.profile_username);
        pass = findViewById(R.id.profile_password);
        email = findViewById(R.id.profile_email);
        save = findViewById(R.id.profile_save_button);
        checkbox = findViewById(R.id.profile_checkbox);
        battery = findViewById(R.id.profile_checkbox_baterie);
        intent = getIntent();
        User u = (User)intent.getSerializableExtra("user");


        username.setText(u.getUsername());
        pass.setText(u.getPassword());
        email.setText(u.getEmail());


        SharedPreferences prefs = this.getSharedPreferences(
                "ro.ase.proiectandroidlivescore", Context.MODE_PRIVATE);

        boolean night = prefs.getBoolean("nightmode",false);
        boolean bat = prefs.getBoolean("battery",false);

        checkbox.setChecked(night);
        battery.setChecked(bat);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                u.setUsername(username.getText().toString());
                u.setPassword(pass.getText().toString());
                u.setEmail(email.getText().toString());

                intent.putExtra("user_changed",u);
                setResult(Activity.RESULT_OK,intent);
                finish();
            }
        });



        checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(checkbox.isChecked())
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    prefs.edit().putBoolean("nightmode",true).apply();
                    prefs.edit().putBoolean("battery",false).apply();
                    battery.setChecked(false);
                }
                else
                {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    prefs.edit().putBoolean("nightmode",false).apply();
                }

            }
        });


        battery.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
              if(battery.isChecked())
              {
                  prefs.edit().putBoolean("battery",true).apply();
              }
              else
              {
                  prefs.edit().putBoolean("battery",false).apply();
              }
            }
        });

    }
}