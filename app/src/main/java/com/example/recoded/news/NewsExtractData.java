package com.example.recoded.news;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public class NewsExtractData {
    /**
     * This function initiates the process of making HTTP connection and returns ArrayList of Newss.
     *
     * @param stringUrl
     * @return
     */

    public static ArrayList<News> initiateConnection(String stringUrl) {
        String jsonResponse = ""; // not to be null
        URL url = getURL(stringUrl);
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e("NewsAsyncTask", "Error establishing Connection!!!");
        }
        ArrayList<News> Newss = extractFromJson(jsonResponse);
        return Newss;
    }

    /**
     * This function take a string url and the URL Object.
     *
     * @param stringUrl
     * @return
     */
    public static URL getURL(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("NewsAsyncTask", "URL Exception => Not able to convert to url object.");
        }
        return url;
    }

    /**
     * Returns jsonresponse in String format.
     *
     * @param url
     * @return
     * @throws IOException
     */
    public static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.setConnectTimeout(10000);
            urlConnection.setReadTimeout(15000);
            urlConnection.connect();
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e("NewsExtractData", "Error response code : " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("NewsExtractData", "Error IOExeception");
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    /**
     * Return String from inputstream
     *
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

    /**
     * Return arrayList of News Objects.
     * actually here where we are going to fetch data from the server.
     *deserialize the jason the to get to ur News object
     * @param jsonData
     * @return
     */
    public static ArrayList<News> extractFromJson(String jsonData) {
        ArrayList<News> Newss = new ArrayList<>();
        try {
            JSONObject baseObject = new JSONObject(jsonData);
            JSONObject responseobject = baseObject.getJSONObject("response");
            JSONArray resultArray = responseobject.getJSONArray("results");
            for (int i = 0; i < resultArray.length(); i++) {
                JSONObject arrayObject = resultArray.optJSONObject(i);
                String NewsTitle = arrayObject.optString("webTitle");
                String NewsPublishDate = arrayObject.optString("webPublicationDate");
                JSONObject fieldsObject = arrayObject.optJSONObject("fields");
                String NewsText = fieldsObject.optString("bodyText");
                String NewsUrl = fieldsObject.optString("shortUrl");
                String NewsWriter = fieldsObject.optString("byline");
                String NewsImageResourceId = fieldsObject.optString("thumbnail");
                Newss.add(new News(NewsTitle , NewsWriter,NewsPublishDate,NewsText ,NewsUrl,NewsImageResourceId ));
            }
        } catch (JSONException e) {
            Log.e("NewsExtractData", "JSON data extract error.");
        }
        return Newss;
    }

}
