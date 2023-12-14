package com.example.spaceexplorer;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import androidx.recyclerview.widget.RecyclerView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class FetchPlanet extends AsyncTask<String,Void,String> {
    private ArrayList<ItemData> values;
    private PlanetAdapter planetAdapter;
    private RecyclerView recyclerView;
    private Context context;

    public FetchPlanet(Context context, ArrayList<ItemData> values,
                       PlanetAdapter planetAdapter, RecyclerView recyclerView) {
        this.context = context;
        this.values = values;
        this.planetAdapter = planetAdapter;
        this.recyclerView = recyclerView;

        this.planetAdapter = new PlanetAdapter(context, values);
        this.recyclerView.setAdapter(this.planetAdapter);
    }

    @Override
    protected String doInBackground(String... strings) {
        String queryString = strings[0];
        BufferedReader reader = null;
        String planetJSONString = "";
        String PLANETARY_FACTSHEET_URL = "https://images-api.nasa.gov/search";
        String QUERY_PARAM = "q";
        HttpURLConnection urlConnection = null;
        Uri builtUri = Uri.parse(PLANETARY_FACTSHEET_URL).buildUpon()
                .appendQueryParameter(QUERY_PARAM, queryString).build();
        ;
        URL requestURL = null;
        try {
            requestURL = new URL(builtUri.toString());
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuilder builder = new StringBuilder();
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                builder.append(line).append("\n");
            }
            if (builder.length() == 0) {
                return null;
            }
            planetJSONString = builder.toString();
            Log.d("APIResponse", "Response: " + planetJSONString); // Tambahkan log ini
        } catch (IOException e) {
            Log.e("APIError", "Error fetching data from API: " + e.getMessage()); // Tambahkan log ini
            throw new RuntimeException(e);

        }
        return planetJSONString;
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);

        // Membersihkan nilai-nilai sebelum menambahkan data baru
        values.clear();

        try {
            // Parsing respons JSON untuk mendapatkan data planet
            JSONObject collectionObject = new JSONObject(s);
            if (collectionObject.has("collection")) {
                JSONObject collection = collectionObject.getJSONObject("collection");

                if (collection.has("items")) {
                    JSONArray itemsArray = collection.getJSONArray("items");

                    for (int i = 0; i < itemsArray.length(); i++) {
                        JSONObject item = itemsArray.getJSONObject(i);

                        if (item.has("data")) {
                            JSONArray dataArray = item.getJSONArray("data");

                            // Hanya mengambil data dari elemen pertama
                            JSONObject data = dataArray.getJSONObject(0);

                            String title = data.getString("title");
                            String desc = data.getString("description");
                            String image = ""; // Inisialisasi image dengan string kosong

                            // Periksa apakah kunci "links" dan "href" ada dalam JSON
                            if (item.has("links")) {
                                JSONArray linksArray = item.getJSONArray("links");
                                if (linksArray.length() > 0) {
                                    // Ambil URL gambar dari elemen pertama
                                    image = linksArray.getJSONObject(0).getString("href");
                                }
                            }

                            // Membuat objek ItemData dan menambahkannya ke values
                            ItemData itemData = new ItemData();
                            itemData.itemTitle(title);
                            itemData.itemDesc(desc);
                            itemData.itemImage(image);

                            values.add(itemData);

                        }
                    }
                }
            }
        } catch (JSONException e) {
            // Menambahkan log kesalahan jika terjadi JSONException
            Log.e("JSONError", "Error parsing JSON: " + e.getMessage());
            e.printStackTrace();
        }

        // Memperbarui adapter setelah mendapatkan data baru
        planetAdapter.notifyDataSetChanged();
        Log.d("FetchPlanet", "Data size after parsing: " + values.size());
    }
}




