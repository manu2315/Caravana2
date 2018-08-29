package calculatuesfuerzo.finsol.com.mx.calcula.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SubtitleCollapsingToolbarLayout;
import android.support.v4.app.Fragment;
import android.text.SpannableString;
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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.io.IOException;
import java.net.URL;

import calculatuesfuerzo.finsol.com.mx.calcula.R;
import calculatuesfuerzo.finsol.com.mx.calcula.adapters.user.UserSingleton;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Adicionales;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Cliente;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Direccion;
import calculatuesfuerzo.finsol.com.mx.calcula.models.Telefono;
import calculatuesfuerzo.finsol.com.mx.calcula.providers.ClienteProvider;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.ClientAdditionalDataFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.ClientAddressFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.ClientDataFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.ClientTelephoneFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.CompleteFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.ui.fragments.HomeFragment;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Constantes;
import calculatuesfuerzo.finsol.com.mx.calcula.util.Util;
import de.hdodenhof.circleimageview.CircleImageView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,HomeFragment.OnFragmentInteractionListener,
        ClientDataFragment.OnFragmentInteractionListener, ClientAddressFragment.OnFragmentInteractionListener,
        ClientTelephoneFragment.OnFragmentInteractionListener,
        ClientAdditionalDataFragment.OnFragmentInteractionListener,CompleteFragment.OnFragmentInteractionListener{


    //INICIO VARIABLES
    //BottomTab
    private TextView mTextMessage;
    private BottomNavigationView navigation;

    //Drawable
    //DrawerLayout drawer;
    NavigationView navigationView;
    //ActionBarDrawerToggle toggle;
    //Collapse ActionBar with ImageView Parallax Slide Animation
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    //SubtitleCollapsingToolbar
    SubtitleCollapsingToolbarLayout collapsingToolbarLayout;
    boolean ExpandedActionBar = true;//**
    int scrollRange = -1;
    //fragments
    public android.app.FragmentManager fragmentManager;
    int idFm;
    //Navigation Header
    private String mail;
    //ImageView imageViewUser;
    CircleImageView imageViewUser;
    TextView textViewUserMail,textViewUserName,textViewUserNumber;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();
        setFragmentByDefault();
        Util.expand(appBarLayout,collapsingToolbarLayout,user.getDisplayName().toString(),Constantes.NO_NOMINA+UserSingleton.USER_MODEL.getPersona().toString());

    }


    //Metodos propios
    private void bindUI(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout=(SubtitleCollapsingToolbarLayout)findViewById(R.id.ctoolbar);
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

        //drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        //toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        //drawer.addDrawerListener(toggle);
        //toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //INICIO TAB ACTIVITY
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //FIN TAB ACTIVITY

        //Navigation

        user= FirebaseAuth.getInstance().getCurrentUser();


        //NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        View headerView = navigationView.getHeaderView(0);
        //TextView navUsername = (TextView) headerView.findViewById(R.id.navUsername);
        //navUsername.setText("Your Text Here");
        imageViewUser =(CircleImageView) findViewById(R.id.user_photo);
        //imageViewUser =(CircleImageView) headerView.findViewById(R.id.user_photo);
        //textViewUserMail=(TextView) headerView.findViewById(R.id.user_email);
        textViewUserMail=(TextView) findViewById(R.id.user_email);
        textViewUserName=(TextView)findViewById(R.id.user_name);
        textViewUserNumber=(TextView)findViewById(R.id.user_rol);
        datosUserLogin();
        collapsingToolbarLayout.setTitle(" ");
        appBarLayout=(AppBarLayout)findViewById(R.id.app_bar_main);

    }

    private void datosUserLogin(){
        //mail=user.getEmail();
        getPhotoUser() ;
        textViewUserMail.setText(user.getEmail().toString());
        textViewUserName.setText(user.getDisplayName().toString());
        textViewUserNumber.setText(Constantes.NO_NOMINA+UserSingleton.USER_MODEL.getPersona().toString());

    }



    private void setFragmentByDefault()
    {
        //HomeFragment fragment = new HomeFragment();
        //Metodo para utilzarse con el menu de sandwich MenuItem item
        //changeFragment(fragment, navigationView.getMenu().getItem(0));
        HomeFragment fragment = new HomeFragment();
        changeFragment(fragment);
        changeFragment(fragment);
    }
    //Metodo para utilzarse con el menu de sandwich MenuItem item
    //private void changeFragment(Fragment fragment, MenuItem item) {
    private void changeFragment(Fragment fragment) {
        //Intentarlo con content_main en el layout principal
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame_step, fragment).commit();
        //idFm=getSupportFragmentManager().getBackStackEntryCount();
        //item.setCheckable(true);
        //getSupportActionBar().setTitle(item.getTitle());

        //TItulo//************
        getSupportActionBar().setTitle("");
        //drawer.closeDrawers();
    }
    /*
    public void fragmentClienttelephone(){

        fragmentManager=getFragmentManager();

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        ClientTelephoneFragment fragment = new ClientTelephoneFragment();
        idFm++;
        getSupportActionBar().setTitle("Teléfonos del Cliente");
        transaction.replace(R.id.content_frame,fragment).addToBackStack(null).commit();
    }
     */

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
    //Metodos genericos
    @Override
    public void onBackPressed() {
       /* DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_step);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else*/
       if(getSupportFragmentManager().getBackStackEntryCount()>0){
            if(idFm>0) {
                getSupportFragmentManager().popBackStack();
                idFm--;
            }else
                super.onBackPressed();
        }
        else {
            super.onBackPressed();
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
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

    //BottomTab
    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    return true;
                case R.id.add_prospect:
                    Intent i = new Intent(MainActivity.this, StepperActivity.class );
                    //i.putExtra("USER_MODEL",userModel) ;
                    startActivity(i);
                    finish();
                    return true;
                case R.id.reports:
                    return true;
                case R.id.others:
                    return true;
            }
            return false;
        }
    };


    //Drawable
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

        }  else if (id == R.id.nav_send) {

        }*/
        //Ejemplo de fragments con drawable
         /*
        boolean fragmentTransaction = false;
         fragment = null;

        //mNuEventos.setEnabled(false);

        switch (item.getItemId()) {

            case R.id.mnCalendario:
                fragment = new CalendarFragment();
                fragmentTransaction = true;
                //idFm=31;
                //fragmentID.push(idFm);
                break;

            case R.id.mnueventos:
                fragment = new EventoListFragment();
                fragmentTransaction = true;

                //idFm =32;
                //GPSstar();
                //fragmentID.push(idFm);
                break;

                */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout_step);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }

    @Override
    public ClienteProvider getClienteProvider() {
        return null;
    }

    @Override
    public void setTelefono(Telefono telefono) {

    }

    @Override
    public void setAdicionales(Adicionales adicionales) {

    }


    @Override
    public void fragmentClientData() {

    }

    @Override
    public void setDireccion(Direccion direccion) {

    }


    @Override
    public void backToMain() {

    }

    @Override
    public void setClienteProvider(ClienteProvider clienteProvider_) {

    }


}
