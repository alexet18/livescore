package ro.ase.proiectandroidlivescore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class register extends AppCompatActivity {


    Button register;
    ImageButton back;
    String username,password,email;
    Intent intent;
    EditText username_et,password_et,email_et;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        register = findViewById(R.id.register_button);
        back = findViewById(R.id.back_arrow_register);
        username_et = findViewById(R.id.username_register);
        password_et = findViewById(R.id.password_register);
        email_et = findViewById(R.id.email_register);
        intent = getIntent();



        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });


        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                username = username_et.getText().toString();
                password = password_et.getText().toString();
                email = email_et.getText().toString();
                //intent.setData(Uri.parse(username+"$"+password+"$"+email));

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference ref = database.getReference("users");
                User u = new User(username,password,email);
                ref.push().setValue(u);

                u.setUid(ref.getKey());



                intent.putExtra("username",username);
                intent.putExtra("password",password);
                intent.putExtra("email",email);
                setResult(RESULT_OK,intent);
                finish();
               // Toast toast = Toast.makeText(getApplicationContext(),"User registered(de terminat)",Toast.LENGTH_LONG);
                //toast.show();

            }
        });
    }
}