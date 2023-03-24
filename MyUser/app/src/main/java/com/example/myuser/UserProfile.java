package com.example.myuser;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

public class UserProfile extends AppCompatActivity {

    ImageView profile_image;
    TextView profile_username, profile_name, profile_email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        profile_image = findViewById(R.id.profile_image);
        profile_email = findViewById((R.id.profile_email));
        profile_name = findViewById(R.id.profile_name);
        profile_username = findViewById(R.id.profile_username);

        Intent intent = getIntent();
        profile_email.setText(intent.getStringExtra("email"));
        profile_name.setText(intent.getStringExtra("name"));
        profile_username.setText(intent.getStringExtra("username"));

        String img_url = intent.getStringExtra("img_url");
        Glide.with(this).load(img_url).into(profile_image);
    }

    public void homeClick(View view) {
        Intent intent = new Intent(this, UserList.class);
        startActivity(intent);
    }

    public void logout(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}