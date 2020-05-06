package developer.exam.live.vi.adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;
import developer.exam.live.vi.MainActivity;
import developer.exam.live.vi.R;
import developer.exam.live.vi.TestResult;
import developer.exam.live.vi.classes.Constant;
import developer.exam.live.vi.classes.MySingleton;
import developer.exam.live.vi.utils.TestDataItem;

public class TestAdapter extends RecyclerView.Adapter<TestAdapter.TestHolder>{

    private Context mContext;
    private ArrayList<TestDataItem> mTestDataList;
    private String test_counter_result;
    private int position = 0;
    private float[] value = new float[12];
    private boolean[] isChecked = {false, false, false, false, false, false, false, false, false, false, false, false};

    public TestAdapter(Context mContext, ArrayList<TestDataItem> mTestDataList, String test_counter_result) {
        this.mContext = mContext;
        this.mTestDataList = mTestDataList;
        this.test_counter_result = test_counter_result;
    }

    @NonNull
    @Override
    public TestHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.coronavirus_test_data_item, parent, false);
        return new TestHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TestHolder holder, final int position) {
        holder.test_question.setText(mTestDataList.get(position).getTest_question());
        holder.test_option1.setText(mTestDataList.get(position).getTest_option1());
        holder.test_option2.setText(mTestDataList.get(position).getTest_option2());
        holder.test_option3.setText(mTestDataList.get(position).getTest_option1());
        holder.test_option4.setText(mTestDataList.get(position).getTest_option2());
        holder.test_option5.setText(mTestDataList.get(position).getTest_option3());

        // Radio Button activity started
        if (mTestDataList.get(position).isOptionCountTwo()) {
            holder.test_option1.setVisibility(View.VISIBLE);
            holder.test_option2.setVisibility(View.VISIBLE);
            holder.test_option3.setVisibility(View.GONE);
            holder.test_option4.setVisibility(View.GONE);
            holder.test_option5.setVisibility(View.GONE);

            if (holder.test_option1.isChecked() || holder.test_option2.isChecked()) {
                isChecked[position] = true;
            }

            holder.test_rg_option_2.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.test_boolean_yes: {
                            value[position] =  mTestDataList.get(position).getTest_value1();
                            isChecked[position] = true;
                            Log.d("TestAdapter", "position: " +position + "isChecked: " + isChecked[position]);
                            break;
                        }
                        case R.id.test_boolean_no: {
                            value[position] =  mTestDataList.get(position).getTest_value2();
                            isChecked[position] = true;
                            Log.d("TestAdapter", "position: " +position + "isChecked: " + isChecked[position]);
                        }
                    }
                }
            });

        }
        else{
            holder.test_option1.setVisibility(View.GONE);
            holder.test_option2.setVisibility(View.GONE);
            holder.test_option3.setVisibility(View.VISIBLE);
            holder.test_option4.setVisibility(View.VISIBLE);
            holder.test_option5.setVisibility(View.VISIBLE);

            if (holder.test_option3.isChecked() || holder.test_option4.isChecked() || holder.test_option5.isChecked()) {
                isChecked[position] = true;
            }

            holder.test_rg_option_3.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(RadioGroup group, int checkedId) {
                    switch (checkedId) {
                        case R.id.test_rg_d1: {
                            value[position] =  mTestDataList.get(position).getTest_value1();
                            isChecked[position] = true;
                            Log.d("TestAdapter", "position: " +position + "isChecked: " + isChecked[position]);
                            break;
                        }
                        case R.id.test_rg_d2: {
                            value[position] =  mTestDataList.get(position).getTest_value2();
                            isChecked[position] = true;
                            Log.d("TestAdapter", "position: " +position + "isChecked: " + isChecked[position]);
                            break;
                        }
                        case R.id.test_none: {
                            value[position] =  mTestDataList.get(position).getTest_value3();
                            isChecked[position] = true;
                            Log.d("TestAdapter", "position: " +position + "isChecked: " + isChecked[position]);
                        }
                    }
                }
            });

        }
        // Radio Button activity finished

        // Result Button activity started


        if (position == mTestDataList.size()-1) {
            holder.test_result_button.setVisibility(View.VISIBLE);
            holder.test_result_button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (isAllItemsChecked()) {
                        MainActivity.isAtTestFragment = false;
                        Intent intent = new Intent(mContext, TestResult.class);
                        intent.putExtra("result_array", value);
                        mContext.startActivity(intent);
                        updateTestCounter();
                    }
                    else {
                        Toast.makeText(mContext, "Please answer all questions", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        } else {
            holder.test_result_button.setVisibility(View.GONE);
            holder.test_result_button_card_view_container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));
        }

        // Button activity finished

        holder.test_card_view_container.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_animation));

    }

    private boolean isAllItemsChecked() {
        boolean flag = true;

        for (int i = 0; i < isChecked.length; i++) {
            if (!isChecked[i]) {
                flag = false;
                position = i;
            }
        }

        return flag;
    }


    // updating the test counter json
    private void updateTestCounter() {

        String url = Constant.get_count_url;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
            @Override
            protected Map<String, String> getParams()throws AuthFailureError {
                Map<String,String> updateCount = new HashMap<>();
                int count = Integer.parseInt(test_counter_result);
                count = count + 1;
                updateCount.put("count",String.valueOf(count));
                return updateCount;
            }
        };

        MySingleton.getInstance(mContext.getApplicationContext()).addTORequestQueue(stringRequest);

    }

    @Override
    public int getItemCount() {
        return mTestDataList.size();
    }

    class TestHolder extends RecyclerView.ViewHolder {
        TextView test_question;
        RadioButton test_option1, test_option2, test_option3, test_option4, test_option5;
        RadioGroup test_rg_option_2, test_rg_option_3;
        CardView test_card_view_container, test_result_button_card_view_container;
        Button test_result_button;

         TestHolder(@NonNull View itemView) {
            super(itemView);
            test_question = itemView.findViewById(R.id.test_question);
            test_option1 = itemView.findViewById(R.id.test_boolean_yes);
            test_option2 = itemView.findViewById(R.id.test_boolean_no);
            test_option3 = itemView.findViewById(R.id.test_rg_d1);
            test_option4 = itemView.findViewById(R.id.test_rg_d2);
            test_option5 = itemView.findViewById(R.id.test_none);
            test_rg_option_2 = itemView.findViewById(R.id.test_radio_group_with_option_2);
            test_rg_option_3 = itemView.findViewById(R.id.test_radio_group_with_option_3);
            test_card_view_container = itemView.findViewById(R.id.test_card_view);
            test_result_button_card_view_container = itemView.findViewById(R.id.test_result_button_CV_Container);
            test_result_button = itemView.findViewById(R.id.test_result_button);
        }

    }
}
