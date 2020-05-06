package developer.exam.live.vi.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.Cache;
import com.android.volley.DefaultRetryPolicy;
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
import java.util.HashMap;
import java.util.Map;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import developer.exam.live.vi.R;
import developer.exam.live.vi.adapter.HomeAdapter;
import developer.exam.live.vi.classes.MySingleton;
import developer.exam.live.vi.utils.HomeDataItem;


public class HomeFragment extends Fragment implements View.OnClickListener, TextWatcher, TextView.OnEditorActionListener {

    private ArrayList<HomeDataItem> homeList = new ArrayList<>();
    private RecyclerView mHomeRecyclerView;
    private HomeAdapter mHomeAdapter;
    private TextView toolbar_TV;
    private EditText toolbar_search_ET;
    private ImageView  toolbar_back_button, toolbar_icon_search, homePopUpMenu;
    private RelativeLayout toolbar_cancel_button_container, toolbar_back_arrow_container;
    private TextView toolbar_cancel_button;
    private boolean isSearchVisible = false;

    private Context mContext;
    private OnHomeSearchNavigationBackButtonView mListener;
    public HomeFragment(Context context) {
        this.mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_home, container, false);
        mHomeRecyclerView = view.findViewById(R.id.home_recycler_view);

        toolbar_TV = view.findViewById(R.id.toolbar_title);
        toolbar_TV.setText(R.string.app_name);

        toolbar_search_ET = view.findViewById(R.id.home_search_ET);
        toolbar_icon_search = view.findViewById(R.id.search_icon);
        toolbar_icon_search.setOnClickListener(this);
        toolbar_search_ET = view.findViewById(R.id.home_search_ET);
        toolbar_search_ET.setImeActionLabel("", KeyEvent.KEYCODE_ENTER);
        toolbar_search_ET.setOnEditorActionListener(this);

        toolbar_cancel_button = view.findViewById(R.id.toolbar_cancel_button);
        toolbar_cancel_button.setOnClickListener(this);
        toolbar_cancel_button_container = view.findViewById(R.id.toolbar_cancel_button_container);

        toolbar_back_arrow_container = view.findViewById(R.id.back_arrow_container);
        toolbar_back_button = view.findViewById(R.id.toolbar_back_arrow);
        toolbar_back_button.setOnClickListener(this);

        //Performing home menu operation
        homePopUpMenu = view.findViewById(R.id.home_menu);
        homePopUpMenu.setOnClickListener(this);


        // calling get global data function
        getGlobalData();
        getCountryByData();
        toolbar_search_ET.addTextChangedListener(this);
        return view;
    }

    private void getGlobalData() {
        String url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/worldstat.php";

        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            String total_cases = jsonObject.getString("total_cases");
                            String total_deaths = jsonObject.getString("total_deaths");
                            String total_recovered = jsonObject.getString("total_recovered");
                            String new_cases = jsonObject.getString("new_cases");

                            homeList.add(new HomeDataItem("Global Data", total_cases, total_recovered, total_deaths, new_cases));

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-rapidapi-key", "673df6af2bmshb7ad4af9266f86dp17c583jsn44fe3f70cf1c");
                return headers;
            }

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


    private void getCountryByData() {
        String url = "https://coronavirus-monitor.p.rapidapi.com/coronavirus/cases_by_country.php";
        final StringRequest stringRequest = new StringRequest(Request.Method.GET, url,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

                        try{
                            JSONObject jsonObject = new JSONObject(response);
                            JSONArray jsonArray = jsonObject.getJSONArray("countries_stat");
                            for (int i = 0; i < jsonArray.length(); i++) {
                                JSONObject object = jsonArray.getJSONObject(i);
                                String country_name = object.getString("country_name");
                                String total_cases = object.getString("cases");
                                String deaths = object.getString("deaths");
                                String recovered = object.getString("total_recovered");
                                String new_cases = object.getString("new_cases");
                                homeList.add(new HomeDataItem(country_name, total_cases, recovered, deaths, new_cases));
                            }
                            setHomeAdapter(homeList);
                            mHomeAdapter.notifyDataSetChanged();

                        }catch (JSONException e){
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        }) {
            @Override
            public Map<String, String> getHeaders() {
                HashMap<String, String> headers = new HashMap<>();
                headers.put("x-rapidapi-key", "673df6af2bmshb7ad4af9266f86dp17c583jsn44fe3f70cf1c");
                return headers;
            }
                @Override
                protected Response<String> parseNetworkResponse(NetworkResponse response) {
                try {
                    Cache.Entry cacheEntry = HttpHeaderParser.parseCacheHeaders(response);
                    if (cacheEntry == null) {
                        cacheEntry = new Cache.Entry();
                    }
                    final long cacheHitButRefreshed =   3 * 60 * 1000; // in 3 minutes cache will be hit, but also refreshed on background
                    final long cacheExpired = 24 * 60 * 60 * 1000; // in 24 hours this cache entry expires completely
                    long now = System.currentTimeMillis();
                    final long softExpire = now + cacheHitButRefreshed;
                    final long ttl = now + cacheExpired;
                    cacheEntry.data = response.data;
                    cacheEntry.softTtl = softExpire;

                    cacheEntry.ttl = ttl;
                    String headerValue;
                    headerValue = response.headers.get("Date");
                    if (headerValue != null) {
                        cacheEntry.serverDate = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    headerValue = response.headers.get("Last-Modified");
                    if (headerValue != null) {
                        cacheEntry.lastModified = HttpHeaderParser.parseDateAsEpoch(headerValue);
                    }
                    cacheEntry.responseHeaders = response.headers;
                    final String jsonString = new String(response.data,
                            HttpHeaderParser.parseCharset(response.headers));
                    return Response.success((jsonString), cacheEntry);
                } catch (UnsupportedEncodingException e) {
                    return Response.error(new ParseError(e));
                }
            }

                @Override
                protected void deliverResponse(String response) {
                super.deliverResponse(response);
            }

                @Override
                public void deliverError(VolleyError error) {
                super.deliverError(error);
            }

                @Override
                protected VolleyError parseNetworkError(VolleyError volleyError) {
                return super.parseNetworkError(volleyError);
            }
            };
        stringRequest.setRetryPolicy(new DefaultRetryPolicy(0,-1,0));
        MySingleton.getInstance(mContext.getApplicationContext()).addTORequestQueue(stringRequest);
    }


    private void setHomeAdapter(ArrayList<HomeDataItem> homeList) {
        ArrayList<HomeDataItem> newHomeList = new ArrayList<>();
        if (homeList.size() > 300) {
            for (int  i = 0; i < Math.ceil(homeList.size()/2); i++) {
                newHomeList.add(homeList.get(i));
            }
        }
        else {
            newHomeList.addAll(homeList);
        }
        mHomeAdapter = new HomeAdapter(mContext, newHomeList);
        mHomeRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        mHomeRecyclerView.setAdapter(mHomeAdapter);
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
                mListener.onBackButtonListener();
            }
        }

        // Home Pop up menu
        if (v == homePopUpMenu) {
            PopupMenu popupMenu = new PopupMenu(mContext, homePopUpMenu);
            popupMenu.inflate(R.menu.home_menu);
            popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                @Override
                public boolean onMenuItemClick(MenuItem item) {
                    if (item.getItemId() == R.id.app_share){
                        String message = "For inquiries regarding CORONA VIRUS you must go through our app whose link is mentioned below.\n";
                        message = message + "http://play.google.com/store/apps/details?id=developer.ishank.coronavirus.epidemic.fragment";
                        message = message + "\nIn this app you can easily get data of the entire countries and will give you an ease to clarify your confusion regarding the corona virus.  This app will also provide you a live test about COVID-19. So go through this once and get more information about the corona virus.";
                        Intent sendIntent = new Intent();
                        sendIntent.setAction(Intent.ACTION_SEND);
                        sendIntent.putExtra(Intent.EXTRA_TEXT, message);
                        sendIntent.setType("text/plain");
                        mContext.startActivity(Intent.createChooser(sendIntent, "share with"));
                        return true;
                    }
                    return false;
                }
            });
            popupMenu.show();
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
        mHomeAdapter.getFilter().filter(s);
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

    // Interface for search edit text;

    public interface OnHomeSearchNavigationBackButtonView {
         void onBackButtonListener();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        Activity activity = (Activity) context;
        try{
            mListener = (OnHomeSearchNavigationBackButtonView) activity;
        }catch (ClassCastException e) {
            throw new ClassCastException(activity.toString()+ "must override onBackButtonListener");
        }
    }

}
