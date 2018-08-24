package calculatuesfuerzo.finsol.com.mx.calcula.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SubtitleCollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.stepstone.stepper.StepperLayout;
import com.stepstone.stepper.VerificationError;

import java.io.IOException;
import java.net.URL;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.adapters.MyStepperAdapter;
import calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserSingleton;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.ClientAdditionalDataFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.ClientAddressFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.ClientDataFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.ClientTelephoneFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Constantes;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;
import de.hdodenhof.circleimageview.CircleImageView;


public class StepperActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,StepperLayout.StepperListener, ClientDataFragment.OnFragmentInteractionListener,
        ClientAddressFragment.OnFragmentInteractionListener,ClientTelephoneFragment.OnFragmentInteractionListener,ClientAdditionalDataFragment.OnFragmentInteractionListener {

    //SubtitleCollapsingToolbar
    SubtitleCollapsingToolbarLayout collapsingToolbarLayout;
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    //DrawerLayout drawer;
    ActionBarDrawerToggle toggle;
    NavigationView navigationView;
    //STEPPER
    private StepperLayout mStepperLayout;
    //private MyStepperAdapter mStepperAdapter;

    //VARIABLES SCROLL
    private String mail;
    //ImageView imageViewUser;
    CircleImageView imageViewUser;
    TextView textViewUserMail,textViewUserName,textViewUserNumber;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stepper);
        bindUI();
        Util.expand(appBarLayout,collapsingToolbarLayout,user.getDisplayName().toString(),Constantes.NO_NOMINA+UserSingleton.USER_MODEL.getPersona().toString());

    }

    private void bindUI(){
        toolbar = (Toolbar) findViewById(R.id.toolbar_step);
        collapsingToolbarLayout=(SubtitleCollapsingToolbarLayout)findViewById(R.id.ctoolbar_step);
        collapsingToolbarLayout.setCollapsedSubtitleTextAppearance(R.style.TextAppearance_AppCompat_Caption);
        collapsingToolbarLayout.setCollapsedTitleTextAppearance(R.style.TextAppearance_AppCompat_Button);
        setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/

        //drawer = (DrawerLayout) findViewById(R.id.drawer_layout_step);
        //toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view_step);
        navigationView.setNavigationItemSelectedListener(this);

        //STEPPER
        mStepperLayout = (StepperLayout) findViewById(R.id.stepperLayout);
        MyStepperAdapter mStepperAdapter = new MyStepperAdapter(getSupportFragmentManager(), this);
        mStepperLayout.setAdapter(mStepperAdapter);
        mStepperLayout.setListener(this);
        getSupportActionBar().setTitle("");

        user= FirebaseAuth.getInstance().getCurrentUser();

        imageViewUser =(CircleImageView) findViewById(R.id.user_photo);
        textViewUserMail=(TextView) findViewById(R.id.user_email);
        textViewUserName=(TextView)findViewById(R.id.user_name);
        textViewUserNumber=(TextView)findViewById(R.id.user_rol);
        datosUserLogin();
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_step);
    }

    private void datosUserLogin(){
        //mail=user.getEmail();
        getPhotoUser() ;
        textViewUserMail.setText(user.getEmail().toString());
        textViewUserName.setText(user.getDisplayName().toString());
        textViewUserNumber.setText(Constantes.NO_NOMINA+ UserSingleton.USER_MODEL.getPersona().toString());
    }

    public void backToMain(){
        Intent i = new Intent(StepperActivity.this, MainActivity.class );
        //i.putExtra("USER_MODEL",userModel) ;
        startActivity(i);
        finish();
    }
    private void changeFragment(Fragment fragment) {

        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_step, fragment).commit();
        getSupportActionBar().setTitle("Test");

    }
    public void getPhotoUser(){
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        if(user!=null){
            Uri photoUrl = user.getPhotoUrl();
            new DownloadImageTask().execute(photoUrl);
        }
    }

    private class DownloadImageTask extends AsyncTask<Uri, Void, Bitmap> {

        protected Bitmap doInBackground(Uri... uris) {
            Uri photoUrl = uris[0];
            Bitmap bmp = null;
            try {
                URL url = new URL(photoUrl.toString());
                bmp = BitmapFactory.decodeStream(url.openConnection().getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
            return bmp;
        }

        protected void onPostExecute(Bitmap result) {
            if(result!=null){
                //imageUser = mBinding.getRoot().findViewById(R.id.circle_image);
                //imageUser.setImageBitmap(result);

                imageViewUser.setImageBitmap(result);

            }
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_step);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.stepper, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_share) {

        }
       /* else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_send) {

        }*/

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_step);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public void fragmentClientAdditionalData() {

    }

    @Override
    public void fragmentClientAddress() {

    }

    @Override
    public void fragmentClientData() {
        
    }

    @Override
    public void fragmentClientTelephone() {

    }

    @Override
    public void onCompleted(View completeButton) {

     //   Toast.makeText(this, "onCompleted! Activity 1", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onError(@NonNull VerificationError error) {
     //   Toast.makeText(this, "onError! Activity 1 -> " + error.getErrorMessage(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onStepSelected(int newStepPosition) {
       // Toast.makeText(this, "onStepSelected! Activity 1 -> " + newStepPosition, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onReturn() {
        //finish();
      //  Toast.makeText(this, "onReturn! Activity 1 -> ", Toast.LENGTH_SHORT).show();
    }




}
