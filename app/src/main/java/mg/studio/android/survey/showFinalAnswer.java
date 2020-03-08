package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.nfc.Tag;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.service.autofill.TextValueSanitizer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;

public class showFinalAnswer extends AppCompatActivity {
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private String q6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_final_answer);
        /*
        Question6=findViewById(R.id.Q6_GetBack);
        Intent intent = getIntent();
        String Q6=intent.getStringExtra("Q6");
        Question6.setText("Q6:"+Q6);
        */
        TextView Question1 = findViewById(R.id.Q1_GetBack);
        TextView Question2 = findViewById(R.id.Q2_GetBack);
        TextView Question3 = findViewById(R.id.Q3_GetBack);
        TextView Question4 = findViewById(R.id.Q4_GetBack);
        TextView Question5 = findViewById(R.id.Q5_GetBack);
        TextView Question6 = findViewById(R.id.Q6_GetBack);
        Intent intent = getIntent();
        q1 = intent.getStringExtra("Q1");
        q2 = intent.getStringExtra("Q2");
        q3 = intent.getStringExtra("Q3");
        q4 = intent.getStringExtra("Q4");
        q5 = intent.getStringExtra("Q5");
        q6 = intent.getStringExtra("Q6");
        Question1.setText("Q1:" + q1);
        Question2.setText("Q2:" + q2);
        Question3.setText("Q3:" + q3);
        Question4.setText("Q4:" + q4);
        Question5.setText("Q5:" + q5);
        Question6.setText("Q6:" + q6);

        Button store = findViewById(R.id.storeToFile);

        store.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String text1 = q1;
                String text2 = q2;
                String text3 = q3;
                String text4 = q4;
                String text5 = q5;
                String text6 = q6;

                if ((text1.length() == 0) && (text2.length() == 0) && (text3.length() == 0) && (text4.length() == 0) && (text5.length() == 0) && (text6.length() == 0)) {
                    Toast.makeText(getApplicationContext(), "Please for sure you are choose questuons Q1-Q6", Toast.LENGTH_LONG).show();
                } else {
                    JSONObject jsonObject = new JSONObject();
                    try {
                        jsonObject.put("Q1", text1);
                        jsonObject.put("Q2", text2);
                        jsonObject.put("Q3", text3);
                        jsonObject.put("Q4", text4);
                        jsonObject.put("Q5", text5);
                        jsonObject.put("Q6", text6);

                        String json = jsonObject.toString();


                        /*
                        * when done the save2External class, opening the new acticity to show the 006
                         */

                        save2Internal("result.json", json);
                        save2External("result.json", json);




                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    //saveToFile(text);

                }
            }
        });

    }

    private void save2Internal(String fileName, String fileContents) {
        try (FileOutputStream fos = this.openFileOutput(fileName, Context.MODE_APPEND)) {
            fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
            Toast.makeText(this, "Success: save to internal done.", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error: cannot save to internal", Toast.LENGTH_SHORT).show();
        }
    }

    private void save2External(String fileName, String fileContents) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            File file = new File(this.getExternalFilesDir(""), fileName);
            try (FileOutputStream fos = new FileOutputStream(file, true)) {
                fos.write(fileContents.getBytes(StandardCharsets.UTF_8));
                Toast.makeText(this, "Success: save to external done.", Toast.LENGTH_SHORT).show();


                /*
                *open anohter activity
                 */
                Intent intentRead=new Intent(this,questionReadFile.class);
                startActivity(intentRead);
            } catch (Exception e) {
                e.printStackTrace();
                Toast.makeText(this, "Error: cannot save to external.", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(this, "Error: no external storage!", Toast.LENGTH_SHORT).show();
        }
    }




}
