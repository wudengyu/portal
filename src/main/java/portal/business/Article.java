package portal.business;

import java.util.Date;

public class Article {
    private int id;
    private int column;
    private String title;
    private String author;//作者
    private String publisher;//发布者
    private Date publishtime;//发布时间
    private String mender;//修改人
    private Date lastmodifiedtime;//最后修改时间
    private String approver;//批准人
    private Date approvetime;//批准时间
    private ArticleStatus status;
    private String content;

    

    public Article(int id, int column, String title, String author, String publisher, Date publishtime, 
               Date lastmodifiedtime, int statusvalue) {
        this.id = id;
        this.column = column;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.publishtime = publishtime;
        this.lastmodifiedtime = lastmodifiedtime;
        this.status = ArticleStatus.values()[statusvalue];
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

    public String getPublisher() {
        return publisher;
    }

    public Date getPublishtime() {
        return publishtime;
    }

    public String getMender() {
        return mender;
    }

    public Date getLastmodifiedtime() {
        return lastmodifiedtime;
    }

    public String getApprover() {
        return approver;
    }

    public Date getApprovetime() {
        return approvetime;
    }

    public ArticleStatus getStatus() {
        return status;
    }

    public String getContent() {
        return content;
    }

    

}
