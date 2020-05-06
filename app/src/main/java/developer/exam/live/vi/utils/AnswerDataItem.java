package developer.exam.live.vi.utils;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

public class AnswerDataItem implements Parcelable {

    private String answer;

    public AnswerDataItem(String answer) {
        this.answer = answer;
    }

    protected AnswerDataItem(Parcel in) {
        answer = in.readString();
    }

    public static final Creator<AnswerDataItem> CREATOR = new Creator<AnswerDataItem>() {
        @Override
        public AnswerDataItem createFromParcel(Parcel in) {
            return new AnswerDataItem(in);
        }

        @Override
        public AnswerDataItem[] newArray(int size) {
            return new AnswerDataItem[size];
        }
    };

    public String getAnswer() {
        return answer;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(answer);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnswerDataItem that = (AnswerDataItem) o;
        return Objects.equals(answer, that.answer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answer);
    }

}
