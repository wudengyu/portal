package portal.business;

import java.util.Date;

public class Article {
    private int id;
    private int column;
    private String title;
    private String author;
    private Date lastmodifiedtime;
    private User lastmodifieduser;
    private Date approvetime;
    private User approver;
    private int status;
    private String content;

    public Article(int id,String title,String author,Date lastmodifiedtime){
        this.id=id;
        this.title=title;
        this.author=author;
        this.lastmodifiedtime=lastmodifiedtime;
    }

    public Article(int id,String title,String content){
        this.id=id;
        this.title=title;
        this.content=content;
    }

    public int getId() {
        return id;
    }

    public int getColumn() {
        return column;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public User getLastmodifieduser() {
        return lastmodifieduser;
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public User getApprover() {
        return approver;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

}
