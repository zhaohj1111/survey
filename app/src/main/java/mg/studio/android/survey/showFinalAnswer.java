package mg.studio.android.survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.service.autofill.TextValueSanitizer;
import android.widget.TextView;

public class showFinalAnswer extends AppCompatActivity {
    private String q1;
    private String q2;
    private String q3;
    private String q4;
    private String q5;
    private  String q6;
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
        TextView Question1=findViewById(R.id.Q1_GetBack);
        TextView Question2=findViewById(R.id.Q2_GetBack);
        TextView Question3=findViewById(R.id.Q3_GetBack);
        TextView Question4=findViewById(R.id.Q4_GetBack);
        TextView Question5=findViewById(R.id.Q5_GetBack);
        TextView Question6=findViewById(R.id.Q6_GetBack);
        Intent intent = getIntent();
        q1=intent.getStringExtra("Q1");
        q2=intent.getStringExtra("Q2");
        q3=intent.getStringExtra("Q3");
        q4=intent.getStringExtra("Q4");
        q5=intent.getStringExtra("Q5");
        q6=intent.getStringExtra("Q6");
        Question1.setText("Q1:"+q1);
        Question2.setText("Q2:"+q2);
        Question3.setText("Q3:"+q3);
        Question4.setText("Q4:"+q4);
        Question5.setText("Q5:"+q5);
        Question6.setText("Q6:"+q6);

    }
    //private TextView Question6;
}
