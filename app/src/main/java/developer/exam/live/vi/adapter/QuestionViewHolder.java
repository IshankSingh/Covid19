package developer.exam.live.vi.adapter;

import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;
import com.thoughtbot.expandablerecyclerview.viewholders.GroupViewHolder;

import developer.exam.live.vi.R;

import static android.view.animation.Animation.RELATIVE_TO_SELF;

public class QuestionViewHolder extends GroupViewHolder {

    private TextView faq_question_tv;
    private ImageView arrow;

    public QuestionViewHolder(View itemView) {
        super(itemView);
        faq_question_tv = itemView.findViewById(R.id.faq_parent_TV);
        arrow = itemView.findViewById(R.id.faq_parent_arrow);
    }

    public void setGenreTitle(ExpandableGroup QuestionDataItem) {
        faq_question_tv.setText(QuestionDataItem.getTitle());
    }
    @Override
    public void expand() {
        animateExpand();
    }

    @Override
    public void collapse() {
        animateCollapse();
    }

    private void animateExpand() {
        RotateAnimation rotate =
                new RotateAnimation(360, 180, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }

    private void animateCollapse() {
        RotateAnimation rotate =
                new RotateAnimation(180, 360, RELATIVE_TO_SELF, 0.5f, RELATIVE_TO_SELF, 0.5f);
        rotate.setDuration(300);
        rotate.setFillAfter(true);
        arrow.setAnimation(rotate);
    }
}
