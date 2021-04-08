package expertprogramming.org.androidloginregisterfirebase;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    private FirebaseAuth mAuth;
//    String userId = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mAuth = FirebaseAuth.getInstance();

        FirebaseUser currentUser = mAuth.getCurrentUser();
        if(currentUser != null){
            Toast.makeText(getApplicationContext(),"Current user is "+currentUser.getUid(),Toast.LENGTH_SHORT).show();
            Log.d("User",""+currentUser.getUid());
        }else
        {
            Toast.makeText(getApplicationContext(),"User not found",Toast.LENGTH_SHORT).show();
            Log.d("User","user not found");
            startActivity(new Intent(getApplicationContext(),Register.class));
        }
    }
}