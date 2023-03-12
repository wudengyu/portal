package portal.business;

public enum ArticleStatus {
    draft("草稿"),
    unapproved("待审核"),
    published("发布"),
    archived("归档");

    private final String status;
    
    ArticleStatus(String status){
        this.status=status;
    }
  
    public String getStatus() {
        return status;
    }

    

}
