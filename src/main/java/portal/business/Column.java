package portal.business;

import java.util.List;

public class Column {
    private int id;
    private String title;
    private List<Article> articlelist;

    public Column(int id){
        this.id=id;
    }

    public Column(int id,String title){
        this.id=id;
        this.title=title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<Article> getArticlelist() {
        return articlelist;
    }

    public void setArticlelist(List<Article> articlelist) {
        this.articlelist = articlelist;
    }

}
