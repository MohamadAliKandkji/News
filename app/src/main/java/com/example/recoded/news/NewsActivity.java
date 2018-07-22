package com.example.recoded.news;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class NewsActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<ArrayList<News>> {
    private RecyclerView recyclerView;
    public ArrayList<News> Newss = new ArrayList<>();
    private static final int NEWS_LOADER_ID = 1;// special number to recall the loader.
    NewsAdapter NewsAdapter = new NewsAdapter(this, Newss);// set the adapter with , data and context , here it is C as we defined
    ImageView imageView ;

    public static final String URL = "http://content.guardianapis.com";//base url
  //  public static final tring URL = "http://content.guardianapis.com/search?section=politics&order-by=newest&show-fields=byline,bodyText,thumbnail,shortUrl,webTitle&page-size=10&api-key=624928af-5ab6-4e12-8a95-b2d1a03165fd"; // how the default url will look like . this will get the last ten political artical from the Guardians.

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);
        imageView = findViewById(R.id.newsimage);


        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager(); // responsible for make loaders and destroyed in the right time.
            loaderManager.initLoader(NEWS_LOADER_ID, null, this);// unique identifier to call it // Additional bundle //the activity itself is the loadercall back object that we want to pass in.
            recyclerView = (RecyclerView) findViewById(R.id.newstrecylerview);
            recyclerView.setLayoutManager(new LinearLayoutManager(this));// to which activitylayout
            recyclerView.setAdapter(NewsAdapter);
            recyclerView.setVisibility(View.VISIBLE);

        } else {
            /*
            This code will work when there is no internet connectivity.
             */

            Toast.makeText(this, "No internet habibi", Toast.LENGTH_SHORT).show();

        }
    }
        @Override
        public Loader<ArrayList<News>> onCreateLoader(int id, Bundle args) {
            //search?section=politics&order-by=newest&show-fields=byline,bodyText,thumbnail,shortUrl,webTitle&page-size=10&api-key=624928af-5ab6-4e12-8a95-b2d1a03165fd
            //how to obtain the above url as default.
            //
            SharedPreferences sharedPrefs = PreferenceManager.getDefaultSharedPreferences(this);
            String sizeItem = sharedPrefs.getString(getString(R.string.size_item_key), getString(R.string.size_item_default));
            String apikey = sharedPrefs.getString(getString(R.string.api_key), getString(R.string.api_key_default));
            Uri baseUri = Uri.parse(URL);
            Uri.Builder uriBuilder = baseUri.buildUpon();// this will build upon the base url using the below uribuilder.

            uriBuilder.appendPath("search");// it will "/search"
            uriBuilder.appendQueryParameter("section", "politics");// means in uri "section=politics"
            uriBuilder.appendQueryParameter("format", "json");// means data will be in json format
            uriBuilder.appendQueryParameter("order-by", "newest");//means in uri "order-by=newest"
            uriBuilder.appendQueryParameter("show-fields", "byline,bodyText,thumbnail,shortUrl");//means in uri show-fields will have three things to show
            uriBuilder.appendQueryParameter("page-size", sizeItem);//means in uri "page-size=sizeItem(specified by the user default value is 10 )" note here it is a value stored from shared prefences
            uriBuilder.appendQueryParameter("api-key", apikey);//this is the api key taken from the source after registering in their api.
            return new NewsLoader(this, uriBuilder.toString());
        }

    @Override
    public void onLoadFinished(Loader<ArrayList<News>> loader, ArrayList<News> NewsList) {
        if (NewsList.size() != 0) {
           Newss.clear();// make sure it is empty//can be remove if u want.
        Newss.addAll(NewsList);// add the obtaind list (NewsList) to our Arraylist (Newss).

            NewsAdapter.notifyDataSetChanged();// this very important because it notify the adapter of data change.

        } else {
            recyclerView.setVisibility(View.GONE);

        }

        //Picasso.with(this).load("https://cdn.journaldev.com/wp-content/uploads/2016/11/android-image-picker-project-structure.png").into(imageView);

    }


    @Override
    public void onLoaderReset(Loader<ArrayList<News>> loader) {
        Newss.clear();
    }

    }

        /*ArrayList<News> Newss = new ArrayList<>();
        Newss.add(new News("ssas","sadsada",R.drawable.ic_launcher_background));
        Newss.add(new News("ssas","sadsada",R.drawable.ic_launcher_background));
        Newss.add(new News("ssas","sadsada",R.drawable.ic_launcher_background));
        Newss.add(new News("ssas","sadsada",R.drawable.ic_launcher_background));
        Newss.add(new News("ssas","sadsada",R.drawable.ic_launcher_background));
*/



