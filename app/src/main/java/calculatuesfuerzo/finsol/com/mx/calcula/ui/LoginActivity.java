package calculatuesfuerzo.finsol.com.mx.calcula.ui;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.databinding.DataBindingUtil;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
//import calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserModel;
//import calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserSingleton;
import calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserModel;
import calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserSingleton;
import calculatuesfuerzo.finsol.com.mx.calcula.databinding.ActivityLoginBinding;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.Provider;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;

import static calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserSingleton.getUserModel;
//import calculatuesfuerzo.finsol.com.mx.calcula.providers.Provider;
//import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;

//import static calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserSingleton.getUserModel;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{

    private static final int RC_SIGN_IN = 9001;
    private Button iniciar ;
    private GoogleSignInClient mGoogleSignInClient;
    private FirebaseAuth mAuth;
    private ActivityLoginBinding mBinding;
    private ProgressBar mProgressBar;
    private final int REQUEST_PERMISSION_ACCESS_FINSOL = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mAuth = FirebaseAuth.getInstance();
        //setContentView(R.layout.activity_login);

        FirebaseUser currentUser = mAuth.getCurrentUser();



        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_login);

        findViewById(R.id.btnLogin).setOnClickListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);


        if(currentUser!=null){
            loginFirebase();
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            GoogleSignInResult result = Auth.GoogleSignInApi.getSignInResultFromIntent(data);
            if (result.isSuccess()) {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = result.getSignInAccount();
                firebaseAuthWithGoogle(account);
            } else {
                // Google Sign In failed, update UI appropriately
                // ...
            }
        }
    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.btnLogin)
        {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) ==
                    PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.SYSTEM_ALERT_WINDOW) ==
                            PackageManager.PERMISSION_GRANTED &&
                    ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) ==
                            PackageManager.PERMISSION_GRANTED){

                signIn();

            } else {
                // Toast.makeText(this, "Sin permisos", Toast.LENGTH_LONG).show();
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.CAMERA, Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_CONTACTS, Manifest.permission.READ_CALL_LOG,
                        Manifest.permission.READ_SMS, Manifest.permission.RECORD_AUDIO,
                }, REQUEST_PERMISSION_ACCESS_FINSOL);

            }
        }
    }
    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
        setupProgress();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_PERMISSION_ACCESS_FINSOL:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED
                        && grantResults[1] == PackageManager.PERMISSION_GRANTED
                        && grantResults[2] == PackageManager.PERMISSION_GRANTED
                        && grantResults[3] == PackageManager.PERMISSION_GRANTED
                        && grantResults[4] == PackageManager.PERMISSION_GRANTED
                        && grantResults[5] == PackageManager.PERMISSION_GRANTED
                        && grantResults[6] == PackageManager.PERMISSION_GRANTED
                        && grantResults[7] == PackageManager.PERMISSION_GRANTED
                        && grantResults[8] == PackageManager.PERMISSION_GRANTED
                        && grantResults[9] == PackageManager.PERMISSION_GRANTED){
                    signIn();
                }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {

                            loginFirebase() ;
                        }
                        else{
                            hideProgressBar();
                            singout();
                            Util.infoDialog(LoginActivity.this,R.string.dialog_sin_contenido, R.string.dialog_internet_problem_positive_button_label).show() ;
                        }
                    }
                });
    }
    private void setupProgress(){
        if(mProgressBar==null){
            mProgressBar = new ProgressBar(this,null,android.R.attr.progressBarStyleHorizontal);
            mProgressBar.setIndeterminate(true);
            mBinding.tableLayout.addView(mProgressBar);
        }
        mProgressBar.setVisibility(View.VISIBLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    private void hideProgressBar(){
        mProgressBar.setVisibility(View.GONE);
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
    }
    private void singout(){
        FirebaseAuth.getInstance().signOut();
        mGoogleSignInClient.signOut()
                .addOnCompleteListener(LoginActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        Log.e("","");
                    }
                });
    }

    private void loginFirebase() {
        try {
            FirebaseUser user = mAuth.getCurrentUser();
            final String sha1 =Util.getSHA1(user.getEmail()) ;
            setupProgress();
            mBinding.btnLogin.setVisibility(View.GONE);
            Provider.addValueEventListener(Provider.NODE_USUARIO_LOGIN+sha1+"/status", true, new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    Boolean status=(Boolean)dataSnapshot.getValue();

                    if (status != null && status){
                        getUserModel(new UserSingleton.Callback() {
                            @Override
                            public void modelReady(UserModel userModel) {
                                Intent i = new Intent(LoginActivity.this, MainActivity.class );
                                i.putExtra("USER_MODEL",userModel) ;
                                startActivity(i);
                                finish();
                            }
                        }) ;

                    }
                    else{
                        mBinding.btnLogin.setVisibility(View.VISIBLE);
                        hideProgressBar();
                        singout();
                        Util.infoDialog(LoginActivity.this,R.string.dialog_sin_permisos, R.string.dialog_internet_problem_positive_button_label).show() ;
                    }
                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    mBinding.btnLogin.setVisibility(View.VISIBLE);
                    hideProgressBar();
                    singout();
                    Util.infoDialog(LoginActivity.this, R.string.dialog_internet_problem_message, R.string.dialog_internet_problem_positive_button_label).show() ;
                }
            });
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }
}
