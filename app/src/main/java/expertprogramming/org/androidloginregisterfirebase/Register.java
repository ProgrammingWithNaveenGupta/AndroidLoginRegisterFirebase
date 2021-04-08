package expertprogramming.org.androidloginregisterfirebase;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Register extends AppCompatActivity {

    private FirebaseAuth mAuth;

    EditText email,password;
    Button register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);
        register = findViewById(R.id.register);

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String emailid = email.getText().toString();
                String pass = password.getText().toString();

                Registeruser(emailid,pass);
                Log.d("user",""+emailid +" "+pass);
            }
        });
    }

    private void Registeruser(String uemail, String upass){
        Log.d("user",""+uemail +" "+upass);
        mAuth.createUserWithEmailAndPassword(uemail,upass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Log.d("User","user created successfully");
                    FirebaseUser currentUser = mAuth.getCurrentUser();
                    if(currentUser != null) {
                        Toast.makeText(getApplicationContext(), "Current user is " + currentUser.getUid(), Toast.LENGTH_SHORT).show();
                        Log.d("User", "" + currentUser.getUid());
                    }else
                        Log.d("User","Unable to get user details");
                }
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Log.d("User","Failed "+e.getLocalizedMessage().toLowerCase());
            }
        });
    }
}