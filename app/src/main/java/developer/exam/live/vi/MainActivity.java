package developer.exam.live.vi;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import developer.exam.live.vi.fragment.ExitDialogFragment;
import developer.exam.live.vi.fragment.FaqFragment;
import developer.exam.live.vi.fragment.HomeFragment;
import developer.exam.live.vi.fragment.NetworkFragment;
import developer.exam.live.vi.fragment.NewsFragment;
import developer.exam.live.vi.fragment.TestFragment;
import developer.exam.live.vi.fragment.TestFragmentIntro;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.MenuItem;
import com.google.android.material.bottomnavigation.BottomNavigationView;


public class MainActivity extends AppCompatActivity implements NetworkFragment.OnNetworkCallback,
        ExitDialogFragment.ExitDialogListener, TestFragmentIntro.OnTestButtonClick,
        HomeFragment.OnHomeSearchNavigationBackButtonView, NewsFragment.OnNewsSearchNavigationBackButtonView {

    public static boolean isAtHomeFragment = true;
    public static boolean isAtTestFragment = false;
    private BottomNavigationView bottomNavigationView;
    private ExitDialogFragment mExitDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        checkNetworkConnection();
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
            Fragment fragment;
            switch (menuItem.getItemId()) {
                case R.id.navigation_home: {
                    fragment = new HomeFragment(MainActivity.this);
                    loadFragment(fragment);
                    isAtHomeFragment = true;
                    if (isAtTestFragment) {
                        isAtTestFragment = false;
                    }
                    return true;
                }
                case R.id.navigation_faq: {
                    fragment = new FaqFragment(MainActivity.this);
                    loadFragment(fragment);
                    isAtHomeFragment = false;
                    if (isAtTestFragment) {
                        isAtTestFragment = false;
                    }
                    return true;
                }
                case R.id.navigation_news: {
                    fragment = new NewsFragment(MainActivity.this);
                    loadFragment(fragment);
                    isAtHomeFragment = false;
                    if (isAtTestFragment) {
                        isAtTestFragment = false;
                    }
                    return true;
                }
                case R.id.navigation_test: {
                    fragment = new TestFragmentIntro(MainActivity.this);
                    loadFragment(fragment);
                    isAtHomeFragment = false;
                    if (isAtTestFragment) {
                        isAtTestFragment = false;
                    }
                    return true;
                }
            }
            return false;
        }
    };

    public void loadFragment(Fragment fragment) {
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.container, fragment);
        fragmentTransaction.commit();
    }


    public void checkNetworkConnection() {

        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeInfo = connectivityManager.getActiveNetworkInfo();

        if (activeInfo != null && activeInfo.isConnected()) {
            Fragment homeFragment = new HomeFragment(MainActivity.this);
            loadFragment(homeFragment);
            isAtHomeFragment = true;
        } else {
            NetworkFragment networkFragment = new NetworkFragment();
            loadFragment(networkFragment);
            isAtHomeFragment = false;
        }
    }

    @Override
    public void onBackPressed() {
        if (!isAtTestFragment) {
            if (isAtHomeFragment) {
                onBackButtonListener();
            }else {
                Fragment fragment = new HomeFragment(MainActivity.this);
                loadFragment(fragment);
                bottomNavigationView.getMenu().getItem(0).setChecked(true);
                isAtHomeFragment = true;
            }
        }else {
            Fragment fragment = new TestFragmentIntro(MainActivity.this);
            loadFragment(fragment);
            isAtTestFragment = false;
            isAtHomeFragment = false;
        }
    }

    @Override
    public void onNetworkCallback() {
        checkNetworkConnection();
    }

    public void showExitDialog() {
        mExitDialogFragment = new ExitDialogFragment();
        mExitDialogFragment.show(getSupportFragmentManager(), "exitDialog");
    }

    @Override
    public void onDialogPositiveClick(DialogFragment dialog) {
        finish();
    }

    @Override
    public void onDialogNegativeClick(DialogFragment dialog) {
        mExitDialogFragment.dismiss();
    }



    String counter;
    @Override
    public void onTestButtonListener() {
        Fragment testFragment = new TestFragment(MainActivity.this, counter);
        loadFragment(testFragment);
        isAtTestFragment = true;
    }

    @Override
    public void onTextSentListener(String count) {
        counter = count;

    }

    @Override
    public void onBackButtonListener() {
        showExitDialog();
    }

    @Override
    public void onNewsBackButtonListener() {
        if (!isAtHomeFragment) {
            Fragment homeFragment = new HomeFragment(MainActivity.this);
            loadFragment(homeFragment);
            isAtHomeFragment = true;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}

