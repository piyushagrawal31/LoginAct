package com.example.rucha.loginact;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.rucha.loginact.Database.DatabaseActivity;
import com.example.rucha.loginact.Fragments.DialogFragment.Activity.DateTimeActivity;
import com.example.rucha.loginact.Fragments.WFragment.ViewQuoteActivity;
import com.example.rucha.loginact.Fragments.woFragment.WofTitleListActivity;
import com.example.rucha.loginact.Location.GetLocationActivity;
import com.example.rucha.loginact.MenuExample.MenuActivity;
import com.example.rucha.loginact.Services.Activity.MusicServiceActivity;
import com.example.rucha.loginact.Services.MusicService;

public class WelcomeActivity extends AppCompatActivity implements View.OnClickListener,
        NavigationView.OnNavigationItemSelectedListener {

    public static String welUserName = "UserName";
    public static String welPassword = "Password";

    private TextView lcllblWelcome;
    private ImageButton lclImgView;
    private ImageView lcldispImg;
    private ImageButton lclimgBtnUpld;
    private Button lclbtnImgfromGallery;
    private Button lclbtnListView;
    private Button lclbtnwofTitleList;
    private Button lclbtnWfQuotes;
    private Button lclbtnDatabase;
    private Button lclbtnMenu;
    private Button lclbtnMusic;
    private Button lclbtnDateTime;
    private Button lclbtnGetLocation;
    private int Delay = 500;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        lcllblWelcome = findViewById(R.id.lblWelcome);

        Intent i = getIntent();

        String dispUserName = "Welcome " + i.getStringExtra(welUserName);
        String dispPassword = i.getStringExtra(welPassword);

        lcllblWelcome.setText(dispUserName);
        lclImgView = findViewById(R.id.imgBack);
        lclImgView.setOnClickListener(this);

        //Loading Image declaration
        lcldispImg = (ImageView) findViewById(R.id.dispImg);
        lclimgBtnUpld = (ImageButton) findViewById(R.id.imgbtnUpload);

        lclimgBtnUpld.setOnClickListener(this);

        //Loading Image from Gallery
        lclbtnImgfromGallery = findViewById(R.id.btnImagefromGallery);
        lclbtnImgfromGallery.setOnClickListener(this);

        //List View
        lclbtnListView = findViewById(R.id.btnListView);
        lclbtnListView.setOnClickListener(this);

        //Without fragment
        lclbtnwofTitleList = findViewById(R.id.btnWofTitleList);
        lclbtnwofTitleList.setOnClickListener(this);

        //With Fragment
        lclbtnWfQuotes = findViewById(R.id.btnWfQuotes);
        lclbtnWfQuotes.setOnClickListener(this);

        //Database Example
        lclbtnDatabase = findViewById(R.id.btnDatabase);
        lclbtnDatabase.setOnClickListener(this);

        //Menu Example
        lclbtnMenu = findViewById(R.id.btnMenu);
        lclbtnMenu.setOnClickListener(this);

        //Service Example
        lclbtnMusic = findViewById(R.id.btnMusic);
        lclbtnMusic.setOnClickListener(this);

        //Date Time Picker
        lclbtnDateTime = findViewById(R.id.btnDateTime);
        lclbtnDateTime.setOnClickListener(this);

        //Get Location
        lclbtnGetLocation = findViewById(R.id.btnGetLocation);
        lclbtnGetLocation.setOnClickListener(this);

        //        Navigation View starts
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
//        Navigation View ends

    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)){
            drawer.closeDrawer(GravityCompat.START);
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
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

    @Override
    public void onClick(View view) {
        if (view == lclImgView) {
            finish();
        }

        //Load Image on click of Upload button
        if (view == lclimgBtnUpld){
            LoadImage loadimagetask = new LoadImage(WelcomeActivity.this);
            loadimagetask.execute(R.drawable.landscape);
        }
        else if (view == lclbtnImgfromGallery) {
            Intent intentImgGallery = new Intent(WelcomeActivity.this, GalleryUploadActivity.class);
            startActivity(intentImgGallery);
        }
        else if (view == lclbtnListView) {
            Intent intentListView = new Intent(WelcomeActivity.this, ListViewActivity.class);
            startActivity(intentListView);
        }
        else if (view == lclbtnwofTitleList){
            Intent intentwofTitle = new Intent(WelcomeActivity.this, WofTitleListActivity.class);
            startActivity(intentwofTitle);
        }
        else if (view == lclbtnWfQuotes){
            Intent intentwfQuote = new Intent(WelcomeActivity.this, ViewQuoteActivity.class);
            startActivity(intentwfQuote);
        }
        else if (view == lclbtnDatabase){
            Intent intentDatabase = new Intent(WelcomeActivity.this, DatabaseActivity.class);
            startActivity(intentDatabase);
        }
        else if (view == lclbtnMenu){
            Intent intentDatabase = new Intent(WelcomeActivity.this, MenuActivity.class);
            startActivity(intentDatabase);
        }
        else if (view == lclbtnMusic){
            Intent intentMusic = new Intent(WelcomeActivity.this, MusicServiceActivity.class);
            startActivity(intentMusic);
        }
        else if (view == lclbtnDateTime){
            Intent intentDateTime = new Intent(WelcomeActivity.this, DateTimeActivity.class);
            startActivity(intentDateTime);
        }
        else if (view == lclbtnGetLocation){
            Intent intentGetLoc = new Intent(WelcomeActivity.this, GetLocationActivity.class);
            startActivity(intentGetLoc);
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {
//            Sharing our app
            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_SUBJECT, "Share this app");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Welcome to Login App");
            startActivity(Intent.createChooser(sharingIntent, "Share via"));
        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    class LoadImage extends AsyncTask<Integer, Integer, Bitmap>{

        private ProgressDialog dialog;
        private Context context;

        public LoadImage(Activity activity) {
            context = activity;
            dialog = new ProgressDialog(context);
        }

        @Override
        protected void onPreExecute(){
            this.dialog.setMessage("Loading Image...");
            this.dialog.show();
        }

        @Override
        protected Bitmap doInBackground(Integer... intArray) {

            Bitmap tmap = BitmapFactory.decodeResource(getResources(), intArray[0]);
            for (int i = 1; i <= 10; i++){
                try {
                    Thread.sleep(Delay);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return tmap;
        }

        @Override
        protected void onPostExecute(Bitmap resultImg){

            if (dialog.isShowing()){
                dialog.dismiss();
            }
            lcldispImg.setImageBitmap(resultImg);
        }
    }
}
