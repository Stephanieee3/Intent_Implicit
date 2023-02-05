package com.example.intent_2031146;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    EditText etWebsite, etLocation, etShare, etPhone;
    Button btnWebsite, btnLocation, btnShare, btnDial, btnSMS;
    String website, location, shareText, phone;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etWebsite = findViewById(R.id.website_edittext);
        etLocation = findViewById(R.id.location_edittext);
        etShare = findViewById(R.id.share_edittext);
        etPhone = findViewById(R.id.phone_edittext);

        btnWebsite = findViewById(R.id.open_website_btn);
        btnLocation = findViewById(R.id.button_loc);
        btnShare = findViewById(R.id.share_text_btn);
        btnDial = findViewById(R.id.phone_text_btn);
        btnSMS = findViewById(R.id.send_text_btn);


        btnWebsite.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                website = etWebsite.getText().toString();

                Uri uriWebsite = Uri.parse(website);
                Intent openWebsite = new Intent(Intent.ACTION_VIEW, uriWebsite);

                try {
                    startActivity(openWebsite);
                } catch (Exception e) {
                    Log.d("Message", "The link is either broken or error");
                }
            }
        });

        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                location = etLocation.getText().toString();

                Uri uriLocation = Uri.parse("geo:0,0?q=" + location);
                Intent openLocation = new Intent(Intent.ACTION_VIEW, uriLocation);
                startActivity(openLocation);
            }
        });

        btnShare.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                shareText = etShare.getText().toString();

                String mimeType = "text/plain";
                ShareCompat.IntentBuilder
                        .from(MainActivity.this)
                        .setType(mimeType)
                        .setChooserTitle("Share this text with:")
                        .setText(shareText)
                        .startChooser();
            }
        });

        btnDial.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                phone = etPhone.getText().toString().trim();

                Uri uriPhone = Uri.parse("tel:" + Uri.encode(phone));
                Intent openPhone = new Intent(Intent.ACTION_DIAL, uriPhone);
                startActivity(openPhone);
            }
        });

        btnSMS.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SENDTO);
                intent.setData(Uri.parse("sms:081200000000"));
                startActivity(intent);
            }
        });
    }
}