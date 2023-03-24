package com.example.myuser;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Base64;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Registration extends AppCompatActivity {

    public static final String url = "http://10.0.2.2//ByptUser/index.php";
    ImageView regImage;
    EditText name, email, username, password;
    Button regUpload, regLogin, regSignup;
    ActivityResultLauncher<String> mUploadImage;
    private Bitmap bitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        regImage = findViewById(R.id.regImage);
        name = findViewById(R.id.regName);
        email = findViewById(R.id.regEmail);
        username = findViewById(R.id.regUsername);
        password = findViewById(R.id.regPassword);
        regUpload = findViewById(R.id.regUpload);
        regLogin = findViewById(R.id.regLogin);
        regSignup = findViewById(R.id.regSignup);

        mUploadImage = registerForActivityResult(new ActivityResultContracts.GetContent(), new ActivityResultCallback<Uri>() {
            @Override
            public void onActivityResult(Uri result) {
                if(result != null) {
                    try {
                        bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), result);
                        regImage.setImageBitmap(bitmap);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                else{
                    Toast.makeText(Registration.this, "Image not uploaded", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private String bitmapToString(@NonNull Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        byte[] byteArray = stream.toByteArray();
        String result = Base64.encodeToString(byteArray, Base64.DEFAULT);
        return result;
    }

    public void sendData(String mName, String mEmail, String mUsername, String mPassword, String img_path, String img_name) {
/*
        Toast.makeText(this, "gotpath:"+path, Toast.LENGTH_SHORT).show();
        try {
            String uploadId = UUID.randomUUID().toString();
            Toast.makeText(this, "error", Toast.LENGTH_SHORT).show();
            new MultipartUploadRequest(this, uploadId, url)
                    .addFileToUpload(path, "img_path")
                    .addParameter("img_name", img_name)
                    .addParameter("username", mUsername)
                    .addParameter("password", mPassword)
                    .addParameter("name", mName)
                    .addParameter("email", mEmail)
                    .setNotificationConfig(new UploadNotificationConfig())
                    .setMaxRetries(3)
                    .startUpload();

        } catch (Exception ex) {
            Toast.makeText(this, ex.toString(), Toast.LENGTH_SHORT).show();
            username.setText(ex.toString());
        }
*/
        StringRequest request = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Toast.makeText(Registration.this, response, Toast.LENGTH_SHORT).show();
                System.out.println(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(Registration.this, error.toString(), Toast.LENGTH_SHORT).show();
            }
        }){
            @NonNull
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> map = new HashMap<String, String>();
                map.put("name", mName);
                map.put("email", mEmail);
                map.put("username", mUsername);
                map.put("password", mPassword);
                map.put("img_name", img_name);
                map.put("img_path", img_path);
                return map;
            }
        };
        RequestQueue queue = Volley.newRequestQueue(Registration.this);
        queue.add(request);
    }

    public void regUploadClick(View view) {
        mUploadImage.launch("image/*");
    }
    public void regSignupClick(View view) {
        String mName = name.getText().toString().trim();
        String mEmail = email.getText().toString().trim();
        String mUsername = username.getText().toString().trim();
        String mPassword = password.getText().toString().trim();
        String img_name = mUsername + ".jpg";

        if(mName.isEmpty())
            Toast.makeText(this, "Enter Name", Toast.LENGTH_SHORT).show();
        else if(mUsername.isEmpty())
            Toast.makeText(this, "Enter Username", Toast.LENGTH_SHORT).show();
        else if(mEmail.isEmpty())
            Toast.makeText(this, "Enter Email", Toast.LENGTH_SHORT).show();
        else if(mPassword.isEmpty())
            Toast.makeText(this, "Enter Password", Toast.LENGTH_SHORT).show();
        else if(bitmap == null)
            Toast.makeText(this, "Upload Image", Toast.LENGTH_SHORT).show();
        else {
            String img_path = bitmapToString(bitmap);
            sendData(mName, mEmail, mUsername, mPassword, img_path, img_name);
            name.setText("");
            email.setText("");
            username.setText("");
            password.setText("");
            bitmap = null;
            regImage.setImageBitmap(null);

            Toast.makeText(this, "Login Now", Toast.LENGTH_SHORT).show();
        }
    }
    public void regLoginClick(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}