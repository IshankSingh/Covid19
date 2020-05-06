package developer.exam.live.vi;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.text.DecimalFormat;

public class TestResult extends AppCompatActivity {
    TextView result_TV, result_comment_TV, result_comment_title_TV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        result_TV = findViewById(R.id.test_result);
        result_comment_TV = findViewById(R.id.test_result_comment);
        result_comment_title_TV = findViewById(R.id.test_result_comment_tips);
        getResultIntent();
        String concerned = "If you have any health regarded issue, please consult to doctor immediately";
        result_comment_title_TV.setText(concerned);

    }

    private void getResultIntent() {
        Intent intent = getIntent();
        if (intent.hasExtra("result_array")) {
            float [] values = intent.getFloatArrayExtra("result_array");
            float result = 0.0f;
            for (float x : values) {
                result = result + x;
            }
            result = result * 100 / 12;
            if ( result >= 15.0f && result < 30.0f) {
                result = result - 7.5f;
            }
            if ( result >= 30.0f && result < 40.0f) {
                result = result - 9.0f;
            }
            if ( result >= 40.0f && result < 50.0f) {
                result = result - 9.0f;
            }
            if ( result >= 50.0f && result < 60.0f) {
                result = result - 12.0f;
            }
            if ( result >= 60.0f && result < 74.0f) {
                result = result - 13.0f;
            }
            showComment(result);

        }
    }

    private void showComment(float result) {
        if ( result >= 0.0f && result < 15.0f) {
            result_TV.setVisibility(View.GONE);
            String comment = "You are safe";
            result_comment_TV.setText(comment);
            result_TV.setTextColor(Color.parseColor("#4CAF50"));
            result_comment_TV.setTextColor(Color.parseColor("#4CAF50"));
        }
        if ( result >= 15.0f && result < 30.0f) {
            result_TV.setVisibility(View.VISIBLE);
            result_TV.setText(new DecimalFormat("##.##").format(result)+"%");
            String comment = "Poor possibility";
            result_comment_TV.setText(comment);
            result_TV.setTextColor(Color.parseColor("#4CAF50"));
            result_comment_TV.setTextColor(Color.parseColor("#4CAF50"));
        }
        if ( result >= 30.0f && result < 40.0f) {
            result_TV.setVisibility(View.VISIBLE);
            result_TV.setText(new DecimalFormat("##.##").format(result)+"%");
            String comment = "Mild chance";
            result_comment_TV.setText(comment);
            result_TV.setTextColor(Color.parseColor("#4CAF50"));
            result_comment_TV.setTextColor(Color.parseColor("#4CAF50"));
        }
        if ( result >= 40.0f && result < 50.0f) {
            result_TV.setVisibility(View.VISIBLE);
            result_TV.setText(new DecimalFormat("##.##").format(result)+"%");
            String comment = "Severe chance";
            result_comment_TV.setText(comment);
            result_TV.setTextColor(getResources().getColor(R.color.colorPrimary));
            result_comment_TV.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        if ( result >= 50.0f && result < 60.0f) {
            result_TV.setVisibility(View.VISIBLE);
            result_TV.setText(new DecimalFormat("##.##").format(result)+"%");
            String comment = "Highly Possible";
            result_comment_TV.setText(comment);
            result_TV.setTextColor(getResources().getColor(R.color.colorPrimary));
            result_comment_TV.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        if ( result >= 60.0f && result < 74.0f) {
            result_TV.setVisibility(View.VISIBLE);
            result_TV.setText(new DecimalFormat("##.##").format(result)+"%");
            String comment = "Severe chance";
            result_comment_TV.setText(comment);
            result_TV.setTextColor(getResources().getColor(R.color.colorPrimary));
            result_comment_TV.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
        if ( result >= 74.0f && result < 100.0f) {
            result_TV.setVisibility(View.VISIBLE);
            result_TV.setText(new DecimalFormat("##.##").format(result)+"%");
            String comment = "Critical Situation";
            result_comment_TV.setText(comment);
            result_TV.setTextColor(getResources().getColor(R.color.colorPrimary));
            result_comment_TV.setTextColor(getResources().getColor(R.color.colorPrimary));
        }
    }

    public void finish(View v) {
        startActivity(new Intent(TestResult.this, MainActivity.class));
        MainActivity.isAtHomeFragment = true;
        finish();
    }
    @Override
    public void onBackPressed() {
        startActivity(new Intent(TestResult.this, MainActivity.class));
        MainActivity.isAtHomeFragment = true;
        finish();
    }
}
