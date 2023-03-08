package portal.view;

import java.util.Date;

public class ArticleSearch {

    private String title;
    private String publisher;//发布者
    private Date publishtimestart;//发布时间起
    private Date publishtimeend;//发布时间起
    private int status;

    public ArticleSearch(String title, String publisher, Date publishtimestart, Date publishtimeend, int status) {
        this.title = title;
        this.publisher = publisher;
        this.publishtimestart = publishtimestart;
        this.publishtimeend = publishtimeend;
        this.status = status;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public Date getPublishtimestart() {
        return publishtimestart;
    }

    public void setPublishtimestart(Date publishtimestart) {
        this.publishtimestart = publishtimestart;
    }

    public Date getPublishtimeend() {
        return publishtimeend;
    }

    public void setPublishtimeend(Date publishtimeend) {
        this.publishtimeend = publishtimeend;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    

    
    
}
