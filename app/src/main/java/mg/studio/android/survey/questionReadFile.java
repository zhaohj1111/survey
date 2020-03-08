package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.res.AssetManager;
import android.os.Bundle;
import android.os.Environment;
import android.renderscript.ScriptGroup;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class questionReadFile extends AppCompatActivity {

    private TextView Q1,Q2,Q3,Q4,Q5,Q6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_read_file);
        Q1=findViewById(R.id.readJsonQ1);
        Q2=findViewById(R.id.readJsonQ2);
        Q3=findViewById(R.id.readJsonQ3);
        Q4=findViewById(R.id.readJsonQ4);
        Q5=findViewById(R.id.readJsonQ5);
        Q6=findViewById(R.id.readJsonQ6);

        String a1=getJson("example.json",this);
        // Toast.makeText(this,a1,Toast.LENGTH_SHORT).show();
        try {
            JSONObject as=new JSONObject(a1);
            String example1=as.getString("Q1");
            String example2=as.getString("Q2");
            String example3=as.getString("Q3");
            String example4=as.getString("Q4");
            String example5=as.getString("Q5");
            String example6=as.getString("Q6");
            //Toast.makeText(this,example,Toast.LENGTH_SHORT).show();

            Q1.setText("Q1:"+example1);
            Q2.setText("Q2"+example2);
            Q3.setText("Q3"+example3);
            Q4.setText("Q4"+example4);
            Q5.setText("Q5"+example5);
            Q6.setText("Q6"+example6);

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void Read(View view) {

        readFile("result.json");
    }
    private void readFile(String fileName){
        try {
            File sdPath=Environment.getExternalStorageDirectory();
            if(!sdPath.exists()){
                Toast.makeText(this,"no",Toast.LENGTH_SHORT).show();
                return;
            }
            File myfile = new File(sdPath+"/Android/data/mg.studio.android.survey/files",fileName);
            FileInputStream fis=new FileInputStream(myfile);
            InputStreamReader isr= new InputStreamReader(fis,"UTF-8");
            BufferedReader bfr=new BufferedReader(isr);
            String res;

            String R_Q1;
            String R_Q2;
            String R_Q3;
            String R_Q4;
            String R_Q5;
            String R_Q6;


            res=bfr.readLine();
//            Toast.makeText(this,res,Toast.LENGTH_SHORT).show();
            JSONObject obRead=new JSONObject(res);
            R_Q1=obRead.getString("Q1");
            R_Q2=obRead.getString("Q2");
            R_Q3=obRead.getString("Q3");
            R_Q4=obRead.getString("Q4");
            R_Q5=obRead.getString("Q5");
            R_Q6=obRead.getString("Q6");
            Q1.setText("read Q1:"+R_Q1);
            Q2.setText("read Q2:"+R_Q2);
            Q3.setText("read Q3:"+R_Q3);
            Q4.setText("read Q4:"+R_Q4);
            Q5.setText("read Q5:"+R_Q5);
            Q6.setText("read Q6:"+R_Q6);

//            Toast.makeText(this,Q1,Toast.LENGTH_SHORT).show();

        }catch (IOException | JSONException e){
            e.printStackTrace();
        }
    }
    public static String getJson(String fileName,Context context) {

        StringBuilder stringBuilder = new StringBuilder();

        try {
            AssetManager assetManager = context.getAssets();
            BufferedReader bf = new BufferedReader(new InputStreamReader(
                    assetManager.open(fileName)));
            String line;
            while ((line = bf.readLine()) != null) {
                stringBuilder.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return stringBuilder.toString();

    }
}
