package com.example.myuser;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class UserList extends AppCompatActivity {

    TextView user_name, user_email;
    Button view_profile;
    ImageView mainUserImage;
    private final String ImageUrl = "http://10.0.2.2//ByptUser/images/";
    private Model[] data;
    private static final String url = "http://10.0.2.2//ByptUser/getData.php";
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_list);

        user_name = findViewById(R.id.main_user_name);
        user_email = findViewById(R.id.main_user_email);
        mainUserImage = findViewById(R.id.main_user_image);
        view_profile = findViewById(R.id.main_view_profile);
        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(UserList.this));

        set_main_user_profile();

        processData();
    }

    public void set_main_user_profile(){
        Intent intent =getIntent();
        String response = intent.getStringExtra("mainUserData");
        Model mainUserData = new GsonBuilder().create().fromJson(response, Model.class);
        user_name.setText(mainUserData.getName());
        user_email.setText(mainUserData.getEmail());
        Glide.with(this).load(ImageUrl + mainUserData.getImage()).into(mainUserImage);

        view_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(UserList.this, UserProfile.class);
                intent.putExtra("name", mainUserData.getName());
                intent.putExtra("email", mainUserData.getEmail());
                intent.putExtra("username", mainUserData.getUsername());
                intent.putExtra("img_url", ImageUrl + mainUserData.getImage());
                startActivity(intent);
            }
        });
    }

    public void processData() {
        StringRequest request = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                GsonBuilder gsonBuilder = new GsonBuilder();
                Gson gson = gsonBuilder.create();

                data = gson.fromJson(response, Model[].class);
                MyAdapter myAdapter = new MyAdapter(data, ImageUrl);
                recyclerView.setAdapter(myAdapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(UserList.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(request);
    }
}