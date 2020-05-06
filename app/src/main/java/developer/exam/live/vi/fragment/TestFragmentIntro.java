package developer.exam.live.vi.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import developer.exam.live.vi.R;
import developer.exam.live.vi.classes.Constant;
import developer.exam.live.vi.classes.MySingleton;

public class TestFragmentIntro extends Fragment {
    private OnTestButtonClick mCallBack;
    private TextView mTestCounterTextView;

    private Context mContext;
    public TestFragmentIntro(Context context) {
        this.mContext = context;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_test_fragment_intro, container, false);
        getTestCount();
        Button mTestButton = view.findViewById(R.id.test_button);
        mTestCounterTextView = view.findViewById(R.id.test_live_counter_tv);
        mTestButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCallBack.onTextSentListener(mTestCounterTextView.getText().toString());
                mCallBack.onTestButtonListener();
            }
        });
        mTestButton.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_transition_button_anim));
        return view;
    }

    public interface OnTestButtonClick {
        void onTestButtonListener();
        void onTextSentListener(String count);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;

        try{
            mCallBack = (OnTestButtonClick) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ "must override onTestButtonListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mCallBack = null;
    }

    private void getTestCount() {

        String url = Constant.get_count_url;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            String value = jsonObject.getString("count");
                            mTestCounterTextView.setText(value);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });

        MySingleton.getInstance(mContext.getApplicationContext()).addTORequestQueue(stringRequest);

    }

}
