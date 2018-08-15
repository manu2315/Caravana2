package calculatuesfuerzo.finsol.com.mx.calcula.ui;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.SubtitleCollapsingToolbarLayout;
import android.support.v4.app.Fragment;
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

import calculatuesfuerzo.finsol.com.mx.calcula.R;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {


    //INICIO VARIABLES
    //BottomTab
    private TextView mTextMessage;
    private BottomNavigationView navigation;
    //Drawable
    DrawerLayout drawer;
    NavigationView navigationView;
    ActionBarDrawerToggle toggle;
    //Collapse ActionBar with ImageView Parallax Slide Animation
    Toolbar toolbar;
    AppBarLayout appBarLayout;
    //SubtitleCollapsingToolbar
    SubtitleCollapsingToolbarLayout collapsingToolbarLayout;
    boolean ExpandedActionBar = true;
    //fragments
    public android.app.FragmentManager fragmentManager;
    int idFm;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bindUI();

        //Pruebas
        /*
        collapsingToolbarLayout.setTitle("Marco Antonio Lopez Perez");
        collapsingToolbarLayout.setSubtitle("1234567890-Asesor de Credito");*/
    }

    //Metodos propios
    private void bindUI(){
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout=(SubtitleCollapsingToolbarLayout)findViewById(R.id.ctoolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //INICIO TAB ACTIVITY
        mTextMessage = (TextView) findViewById(R.id.message);
        navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        //FIN TAB ACTIVITY
    }

    private void expand(){
        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                if(Math.abs(verticalOffset)>200){
                    ExpandedActionBar=false;
                    collapsingToolbarLayout.setTitle("Marco Antonio Lopez Perez");
                    collapsingToolbarLayout.setSubtitle("1234567890-Asesor de Credito");
                    invalidateOptionsMenu();
                }else{
                    ExpandedActionBar = true;
                    collapsingToolbarLayout.setTitle("");//Marco Antonio Lopez Perez\n"+"1234567890-Asesor de Credito
                    collapsingToolbarLayout.setSubtitle("");
                    invalidateOptionsMenu();
                }
            }
        });
    }

    private void setFragmentByDefault()
    {
        //HomeFragment fragment = new HomeFragment();
        //Metodo para utilzarse con el menu de sandwich MenuItem item
        //changeFragment(fragment, navigationView.getMenu().getItem(0));
    }
    //Metodo para utilzarse con el menu de sandwich MenuItem item
    private void changeFragment(Fragment fragment, MenuItem item) {


        //Intentarlo con content_main en el layout principal
        getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, fragment).commit();
        //idFm=getSupportFragmentManager().getBackStackEntryCount();
        item.setCheckable(true);
        //getSupportActionBar().setTitle(item.getTitle());
        getSupportActionBar().setTitle("Datos del Cliente");
        drawer.closeDrawers();
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
    //Metodos genericos
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        }else if(getSupportFragmentManager().getBackStackEntryCount()>0){
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

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
