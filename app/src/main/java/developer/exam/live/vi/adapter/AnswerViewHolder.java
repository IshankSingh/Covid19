package developer.exam.live.vi.adapter;

import android.view.View;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.viewholders.ChildViewHolder;

import developer.exam.live.vi.R;

public class AnswerViewHolder extends ChildViewHolder {

    private TextView childTextView;

    public AnswerViewHolder(View itemView) {
        super(itemView);
        childTextView = (TextView) itemView.findViewById(R.id.faq_child_TV);
    }

    public void setArtistName(String name) {
        childTextView.setText(name);
    }
}

