package developer.exam.live.vi.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;
import developer.exam.live.vi.R;
import developer.exam.live.vi.utils.AnswerDataItem;
import developer.exam.live.vi.utils.QuestionDataItem;

public class FaqListAdapter extends ExpandableRecyclerViewAdapter<QuestionViewHolder, AnswerViewHolder> {


    Context mContext;
    public FaqListAdapter(List<? extends ExpandableGroup> groups, Context context) {
        super(groups);
        this.mContext = context;
    }

    @Override
    public QuestionViewHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.faq_parent_list_item, parent, false);
        return new QuestionViewHolder(view);
    }

    @Override
    public AnswerViewHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext)
                .inflate(R.layout.faq_child_list_item, parent, false);
        return new AnswerViewHolder(view);
    }

    @Override
    public void onBindChildViewHolder(AnswerViewHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {

        final AnswerDataItem answerDataItem = ((QuestionDataItem) group).getItems().get(childIndex);
        holder.setArtistName(answerDataItem.getAnswer());
    }

    @Override
    public void onBindGroupViewHolder(QuestionViewHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setGenreTitle(group);
    }
}
