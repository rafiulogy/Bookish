package com.example.bookish;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class KobitaFragment extends Fragment {


    // Declaring Hashmap And ArrayList
    HashMap<String, String> hashMap;
    ArrayList<HashMap<String, String>> arrayList = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View myView = inflater.inflate(R.layout.fragment_kobita, container, false);
        // +++++++++++++++++++++++++++++
        ListView listView = myView.findViewById(R.id.listView);
        ProgressBar progressBar = myView.findViewById(R.id.progressBar);
        // +++++++++++++++++++++++++++++
        // +++++++++++++++++++++++++++++++++
        String url = "https://rafiulogy.xyz/bookishApp/API/kobita.json";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                progressBar.setVisibility(View.GONE);


                try {
                    JSONArray jsonArray =response.getJSONArray("books");

                    for(int x = 0; x<jsonArray.length(); x++){
                        JSONObject jsonObject = jsonArray.getJSONObject(x);
                        String bookName = jsonObject.getString("title");
                        String bookDes = jsonObject.getString("description");
                        String bookImage = jsonObject.getString("cover_image");
                        String bookRating = jsonObject.getString("rating");
                        String pdf_link = jsonObject.getString("pdf_link");






                        // putting Data from json to hasmap

                        hashMap = new HashMap<>();
                        hashMap.put("bookName", bookName);
                        hashMap.put("bookDes", bookDes);
                        hashMap.put("bookImage", bookImage);
                        hashMap.put("bookRating", bookRating);
                        hashMap.put("pdf_link", pdf_link);

                        arrayList.add(hashMap);






                    }

                    // setting adapter to listview
                    MyAdapter myAdapter = new MyAdapter();
                    listView.setAdapter(myAdapter);





                } catch (JSONException e) {
                    throw new RuntimeException(e);
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                progressBar.setVisibility(View.GONE);



            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(requireActivity());
        requestQueue.add(jsonObjectRequest);






        return myView ;
    }

    private class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return arrayList.size();
        }

        @Override
        public Object getItem(int i) {
            return null;
        }

        @Override
        public long getItemId(int i) {
            return 0;
        }

        @Override
        public View getView(int i, View view, ViewGroup viewGroup) {


            LayoutInflater layoutInflater =getLayoutInflater();
            @SuppressLint("ViewHolder") View myView1 = layoutInflater.inflate(R.layout.item, null, false);

            LinearLayout linearLayout = myView1.findViewById(R.id.linerLayout);
            TextView bookName = myView1.findViewById(R.id.bookName);
            TextView bookDes = myView1.findViewById(R.id.bookDes);
            TextView bookRating = myView1.findViewById(R.id.bookRating);
            ImageView bookImage = myView1.findViewById(R.id.bookImage);





            // Most Important Part
            HashMap<String,String> hashMap = arrayList.get(i);


            // ++++++++++++++++++++++++
            String Title =hashMap.get("bookName");
            String Description = hashMap.get("bookDes");
            String thumbnail = hashMap.get("bookImage");
            String Rating = hashMap.get("bookRating");
            String bookLink = hashMap.get("pdf_link");





            // ++++++++++++++++++++++++
            bookName.setText(Title);
            bookDes.setText(Description);
            bookRating.setText(Rating);
            Picasso.get()
                    .load(thumbnail)
                    .placeholder(R.drawable.img_1)
                    .into(bookImage);
            System.out.println(bookLink + "bookLink");
            linearLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    PdfViewer.url = bookLink;

                    startActivity(new Intent(getActivity(), PdfViewer.class));

                }
            });

            return myView1;
        }
    }



}