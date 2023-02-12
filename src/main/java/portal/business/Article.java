package portal.business;

import java.sql.Date;

public class Article {
    private int id;
    private int column;
    private String title;
    private String author;
    private Date lastmodifiedtime;
    private String lastmodifieduser;
    private Date approvetime;
    private String approver;
    private int status;
    private String content;

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

    public String getLastmodifieduser() {
        return lastmodifieduser;
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public String getApprover() {
        return approver;
    }

    public int getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }
    
}
