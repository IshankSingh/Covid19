package developer.exam.live.vi.fragment;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.NetworkResponse;
import com.android.volley.ParseError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import developer.exam.live.vi.R;
import developer.exam.live.vi.adapter.NewsDataAdapter;
import developer.exam.live.vi.classes.Constant;
import developer.exam.live.vi.classes.MySingleton;
import developer.exam.live.vi.utils.NewsDataItem;


public class NewsFragment extends Fragment implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener {

    private RecyclerView mNewsRecyclerView;

    private TextView toolbar_TV;
    private EditText toolbar_search_ET;
    private ImageView toolbar_back_button, toolbar_icon_search;
    private RelativeLayout toolbar_cancel_button_container, toolbar_back_arrow_container;
    private TextView toolbar_cancel_button;
    private boolean isSearchVisible = false;
    private NewsDataAdapter newsDataAdapter;

    private OnNewsSearchNavigationBackButtonView mListener;

    private Context mContext;
    private ArrayList<NewsDataItem> newsDataList = new ArrayList<>();
    public NewsFragment(Context context) {
        this.mContext = context;
    }


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_news, container, false);
        mNewsRecyclerView = view.findViewById(R.id.news_recycler_view);

        toolbar_TV = view.findViewById(R.id.news_toolbar_title);

        toolbar_search_ET = view.findViewById(R.id.news_home_search_ET);
        toolbar_icon_search = view.findViewById(R.id.news_search_icon);
        toolbar_icon_search.setOnClickListener(this);
        toolbar_search_ET = view.findViewById(R.id.news_home_search_ET);
        toolbar_search_ET.setImeActionLabel("", KeyEvent.KEYCODE_ENTER);
        toolbar_search_ET.setOnEditorActionListener(this);
        toolbar_search_ET.addTextChangedListener(this);

        toolbar_cancel_button = view.findViewById(R.id.news_toolbar_cancel_button);
        toolbar_cancel_button.setOnClickListener(this);
        toolbar_cancel_button_container = view.findViewById(R.id.news_toolbar_cancel_button_container);

        toolbar_back_arrow_container = view.findViewById(R.id.news_back_arrow_container);
        toolbar_back_button = view.findViewById(R.id.news_toolbar_back_arrow);
        toolbar_back_button.setOnClickListener(this);

        getNewsJson();
        return view;
    }

    private void getNewsJson() {

        String url = Constant.news_url;
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("news");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject newsJsonObject = jsonArray.getJSONObject(i);
                                String id = newsJsonObject.getString("id");
                                String title = newsJsonObject.getString("title");
                                String article = newsJsonObject.getString("article");
                                String url = newsJsonObject.getString("url");


                                newsDataList.add(new NewsDataItem(id, title, article, url));
                            }
                            int size = jsonObject.getInt("size");

                          setNewsAdapter(newsDataList, size);

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }){
        @Override
        protected Response<String> parseNetworkResponse(NetworkResponse response) {

            Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
            if (cacheEntry == null) {
                cacheEntry = new Cache.Entry();
            }

            final long cacheHitButRefreshed = 3 * 60 * 1000;
            final long cacheExpired = 24 * 60 * 60 * 1000;

            long now = System.currentTimeMillis();
            final long softExpire = now + cacheHitButRefreshed;

            final long ttl = now + cacheExpired;
            cacheEntry.data = response.data;
            cacheEntry.softTtl = softExpire;
            cacheEntry.ttl = ttl;

            String headerValue = response.headers.get("date");

            if (headerValue != null) {
                cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
            }
            headerValue = response.headers.get("Last-Modified");
            if (headerValue != null) {
                cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
            }
            cacheEntry.responseHeaders = response.headers;
            try {
                String json = new String(cacheEntry.data, HttpHeaderParser.parseCharset(response.headers));
                return Response.success(json, cacheEntry);
            } catch (UnsupportedEncodingException e) {
                return Response.error(new ParseError(e));
            }
        }
        };

        MySingleton.getInstance(mContext.getApplicationContext()).addTORequestQueue(stringRequest);

    }

    private void setNewsAdapter(ArrayList<NewsDataItem> newsDataList, int size) {
        ArrayList<NewsDataItem> newNewsDataList = new ArrayList<>();
            for (int  i = 0; i < size; i++) {
                newNewsDataList.add(newsDataList.get(i));
            }

        newsDataAdapter = new NewsDataAdapter(mContext, newNewsDataList);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false);
        mNewsRecyclerView.setLayoutManager(linearLayoutManager);
        mNewsRecyclerView.setAdapter(newsDataAdapter);
    }

    @Override
    public void onClick(View v) {
        if (v == toolbar_icon_search) {
            if (!isSearchVisible) {
                setSearchVisibility();

            } else {
                removeSearchVisibility();
            }
        }

        if (v == toolbar_cancel_button) {
            toolbar_search_ET.setText("");
            removeSearchVisibility();
        }

        if (v == toolbar_back_button) {
            if (!toolbar_search_ET.getText().toString().isEmpty()) {
                toolbar_search_ET.setText("");
            }else {
                mListener.onNewsBackButtonListener();
            }
        }
    }

    private void setSearchVisibility() {
        // toolbar_search_ET.setAnimation(AnimationUtils.loadAnimation(mContext, R.anim.fade_scale_animation));
        toolbar_search_ET.setVisibility(View.VISIBLE);
        toolbar_search_ET.requestFocus();
        toolbar_TV.setVisibility(View.GONE);

        toolbar_back_arrow_container.setVisibility(View.GONE);
        toolbar_cancel_button_container.setVisibility(View.VISIBLE);
        isSearchVisible = true;
    }

    private void removeSearchVisibility() {
        toolbar_search_ET.setVisibility(View.GONE);
        toolbar_TV.setVisibility(View.VISIBLE);

        toolbar_back_arrow_container.setVisibility(View.VISIBLE);
        toolbar_cancel_button_container.setVisibility(View.GONE);
        isSearchVisible = false;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        newsDataAdapter.getFilter().filter(s);
    }

    @Override
    public void afterTextChanged(Editable s) {

    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (v == toolbar_search_ET) {
            if (actionId == EditorInfo.IME_ACTION_DONE) {
                removeSearchVisibility();
                InputMethodManager imm = (InputMethodManager) mContext.getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
            }
            return true;
        }
        return false;
    }

    public interface OnNewsSearchNavigationBackButtonView {
        void onNewsBackButtonListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try{
            mListener = (OnNewsSearchNavigationBackButtonView) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ "must override onBackButtonListener");
        }
    }
}
