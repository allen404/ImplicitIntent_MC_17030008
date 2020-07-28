package id.praktikummobilecomputing.mc_intentimpliciit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ImplicitIntentActivity extends AppCompatActivity implements View.OnClickListener{


    private EditText edtDialPhone;
    private EditText websiteUri;
    private EditText locationUri;
    private EditText textShare;

    Button btnWebsite;
    Button btnLocation;
    Button btnText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_implicit_intent);

        edtDialPhone = findViewById(R.id.edtPhoneNumber);



        websiteUri = findViewById(R.id.edtWebsiteUri);
        locationUri = findViewById(R.id.edtLocationUri);
        textShare = findViewById(R.id.edtShareText);

        btnWebsite = findViewById(R.id.btnWebsiteUri);
        btnWebsite.setOnClickListener(this);

        btnLocation  = findViewById(R.id.btnLocationUri);
        btnLocation.setOnClickListener(this);

        btnWebsite = findViewById(R.id.btnShareText);
        btnWebsite.setOnClickListener(this);

    }

    public void openDialPhone (View view)
    {
        if(TextUtils.isEmpty(edtDialPhone.getText().toString())){
            Toast.makeText(this, "Tolong Masukkan Nomor Telepon",Toast.LENGTH_SHORT).show();
            return;
        }

        Intent dialPhone = new Intent(Intent.ACTION_DIAL, Uri.parse("tel: "+ edtDialPhone.getText().toString()));
        startActivity(dialPhone);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btnWebsiteUri:
                if(TextUtils.isEmpty(websiteUri.getText().toString())){
                    Toast.makeText(this, "Tolong Masukkan Alamat Website",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent openWebsite = new Intent (Intent.ACTION_VIEW).setData(Uri.parse("https://" + websiteUri.getText().toString()));
                startActivity(openWebsite);
                break;

            case R.id.btnLocationUri:
                if(TextUtils.isEmpty(locationUri.getText().toString())){
                    Toast.makeText(this, "Tolong Masukkan Alamat Kota/Provinsi/Negara",Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent openLocation = new Intent(Intent.ACTION_VIEW,Uri.parse("geo:0,0?q=" + locationUri.getText().toString()));
                startActivity(openLocation);
                break;

            case R.id.btnShareText:
                if(TextUtils.isEmpty(textShare.getText().toString())){
                    Toast.makeText(this, "Tolong Masukkan Kata/Kalimat",Toast.LENGTH_SHORT).show();
                    return;
                }
                ShareCompat.IntentBuilder
                        .from(this)
                        .setType("text/plan")
                        .setChooserTitle("Share this text with : ")
                        .setText(textShare.getText().toString())
                        .startChooser();
                break;
        }
    }

}