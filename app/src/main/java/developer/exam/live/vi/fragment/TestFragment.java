package developer.exam.live.vi.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import developer.exam.live.vi.R;
import developer.exam.live.vi.adapter.TestAdapter;
import developer.exam.live.vi.utils.TestDataItem;

/**
 * A simple {@link Fragment} subclass.
 */
public class TestFragment extends Fragment {

    private RecyclerView mTestRecyclerView;
    private Context mContext;
    private String count;

    public TestFragment(Context mContext, String count) {
        this.mContext = mContext;
        this.count = count;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_test, container, false);
        mTestRecyclerView = view.findViewById(R.id.test_recycler_view);
        addData();
        return view;
    }

    private void setTestAdapter(ArrayList<TestDataItem> testDataList) {
        TestAdapter testAdapter = new TestAdapter(mContext, testDataList, count);
        mTestRecyclerView.setLayoutManager(new LinearLayoutManager(mContext, RecyclerView.VERTICAL, false));
        mTestRecyclerView.setAdapter(testAdapter);
    }


    private void addData() {
        ArrayList<TestDataItem> mTestDataList = new ArrayList<>();
        mTestDataList.add(new TestDataItem("Are you suffering from any of these:- \n1. High blood Pressure\n2. Diabetes\n3. Cancer",
                "Yes", "No", 0.8f, 0.0f, true));

        mTestDataList.add(new TestDataItem("Any recent foreign travel?",
                "Yes", "No", 1.5f, 0.0f, true));

        mTestDataList.add(new TestDataItem("Have you met with foreigner or any person who has taken recent foreign trip?",
                "Yes", "No", 1.0f, 0.0f, true));

        mTestDataList.add(new TestDataItem("Are you visiting public places frequently?",
                "Yes", "No", 1.0f, 0.0f, true));

        mTestDataList.add(new TestDataItem("Are you suffering from fever?",
                "Yes", "No", 0.81f, 0.0f, true));

        mTestDataList.add(new TestDataItem("Are you suffering from joint pain?",
                "Yes", "No", 0.81f,0.0f, true));

        mTestDataList.add(new TestDataItem("Are you suffering from muscle pain?",
                "Yes", "No", 0.69f,0.0f, true));

        mTestDataList.add(new TestDataItem("Are you suffering from chest pain?",
                "Yes", "No", 0.64f,0.0f, true));

        mTestDataList.add(new TestDataItem("Are you having any difficulty in breathing?",
                "Yes", "No", 0.81f,0.0f, true));

        mTestDataList.add(new TestDataItem("Are you suffering from cough?",
                "Dry Cough", "Wet Cough","None", 0.81f, 0.69f,0.0f, false));

        mTestDataList.add(new TestDataItem("Are you suffering from nasal problem?",
                "Nasal Congestion", "Runny Nose","None", 0.56f, 0.65f,0.0f, false));

        mTestDataList.add(new TestDataItem("Are you suffering from sneezing?",
                "Frequent Sneezing", "Infrequent Sneezing","None", 0.5f, 0.36f,0.0f, false));


        setTestAdapter(mTestDataList);
    }
}
