package portal.business;

public class FileInfo {

    private String filename;
    private int type;
    private String path;
    private String picurl;
    public FileInfo(String filename,int type){
        this.filename=filename;
        this.type=type;
    }
    public String getFilename() {
        return filename;
    }
    public int getType() {
        return type;
    }
    public String getPath() {
        return path;
    }
    public String getPicurl() {
        return picurl;
    }

    
    
}
