package com.example.recoded.news;

import android.os.AsyncTask;

import java.util.ArrayList;

public class NewsAsyncTask extends AsyncTask<String, Void, ArrayList<News>> {//string is input//Void is the progress parameter left empty here.// Arraylist is the output of Asyntask
    private NewsAsyncResponse mNewsAsyncResponse = null;


    public NewsAsyncTask(NewsAsyncResponse newsAsyncResponse) {
        mNewsAsyncResponse = newsAsyncResponse;
    }

    // aslo there there OnPreExcurte prepared the smth on main thread before do InBackground happen
    @Override
    protected ArrayList<News> doInBackground(String... strings) {//on back thread.
        return NewsExtractData.initiateConnection(strings[0]);// get the data on back thread , that comes from the first Uri
        //publish progress (the percentage of progress);
    }

    //also there is onProgressUpdate(Progress p) // it shows the progress on main thread.
    @Override
    protected void onPostExecute(ArrayList<News> Newss) {//on main thread
        if (Newss == null) {
            return;
        }
        mNewsAsyncResponse.processFinish(Newss);// here you assign your values. such as : TextView and ImageView

    }

}
