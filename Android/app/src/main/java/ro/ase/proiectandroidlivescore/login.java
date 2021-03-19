package ro.ase.proiectandroidlivescore;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class login extends AppCompatActivity {

    TextView register;
    Button login;
    EditText username,password;
    Intent intent;
    String username_string,password_string,email_string;
    private final int REQUEST_CODE = 100;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        register = findViewById(R.id.register);
        login = findViewById(R.id.login_button);
        username = findViewById(R.id.username);
        password=findViewById(R.id.password);


        SharedPreferences prefs = this.getSharedPreferences(
                "ro.ase.proiectandroidlivescore", Context.MODE_PRIVATE);

        username.setText(prefs.getString("username",null));
        password.setText(prefs.getString("password",null));


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

              // Toast toast = Toast.makeText(getApplicationContext(),"merge",Toast.LENGTH_LONG);
                //toast.show();

                Intent intent = new Intent(getApplicationContext(),register.class);
                startActivityForResult(intent,REQUEST_CODE);


            }
        });





        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                if(username.length() < 3 || password.length()<3)
                {

                    Toast toast = Toast.makeText(getApplicationContext(),"Introdu nume si parola",Toast.LENGTH_LONG);
                    toast.show();

                }
                else
                {

                    username_string = username.getText().toString();
                    password_string = password.getText().toString();

                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference ref = database.getReference("users");

                    ref.addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                            int ok=0;
                            for(DataSnapshot ds:snapshot.getChildren())
                            {
                                User user1 = ds.getValue(User.class);

                                if(user1.getUsername().equals(username_string) == true && user1.getPassword().equals(password_string) == true)
                                {
                                    user1.setUid(ds.getKey().toString());
                                         prefs.edit().putString("username",username_string).apply();
                                         prefs.edit().putString("password",password_string).apply();


                                    intent = new Intent(getApplicationContext(),MainActivity.class);
                                    //user = new User(username.getText().toString(),password.getText().toString(),ds.child("email").toString());
                                    intent.putExtra("User", user1);
                                    startActivity(intent);
                                    ok=1;
                                    break;
                                }
                            }
                            if(ok==0)
                            {
                               Toast.makeText(getApplicationContext(),"Bad credentials",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onCancelled(@NonNull DatabaseError error) {

                        }
                    });


                }
            }
        });




    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == REQUEST_CODE) {
            if (resultCode == Activity.RESULT_OK) {

                username_string = data.getStringExtra("username");
                password_string = data.getStringExtra("password");
                email_string = data.getStringExtra("email");


                username.setText(username_string);
                password.setText(password_string);

                user = new User(username_string,password_string,email_string);
            }
        }
    }



}