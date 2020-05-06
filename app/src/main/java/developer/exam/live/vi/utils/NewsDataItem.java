package developer.exam.live.vi.utils;

public class NewsDataItem {

    private String id;
    private String title;
    private String article;
    private String url;

    public NewsDataItem(String id, String title, String article, String url) {
        this.id = id;
        this.title = title;
        this.article = article;
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getArticle() {
        return article;
    }

    public String getUrl() {
        return url;
    }
}
