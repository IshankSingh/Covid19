package developer.exam.live.vi.classes;

public class StringFormatter {

    public static String getNewsArticleModification (String s) {
        if (s.length() > 200) {
            s = s.substring(0, 200);
            s = s + "...\n";
            return s;
        }
        s = s + "...\n";
        return s;
    }
}
