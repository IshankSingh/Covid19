package developer.exam.live.vi.fragment;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import developer.exam.live.vi.R;
import developer.exam.live.vi.adapter.FaqListAdapter;

import static developer.exam.live.vi.utils.QuestionDataItemFactory.makeGenres;

public class FaqFragment extends Fragment {

    FaqListAdapter faqListAdapter;

    private Context mContext;
    public FaqFragment(Context context) {
        this.mContext = context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.fragment_faq, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        LinearLayoutManager layoutManager = new LinearLayoutManager(mContext);

        RecyclerView.ItemAnimator animator = recyclerView.getItemAnimator();
        if (animator instanceof DefaultItemAnimator) {
            ((DefaultItemAnimator) animator).setSupportsChangeAnimations(false);
        }

        faqListAdapter = new FaqListAdapter(makeGenres(), mContext);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(faqListAdapter);
        return view;
    }

}

