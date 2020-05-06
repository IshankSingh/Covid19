package developer.exam.live.vi.utils;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class QuestionDataItem extends ExpandableGroup<AnswerDataItem> {
     QuestionDataItem(String title, List<AnswerDataItem> items) {
        super(title, items);
    }
}
