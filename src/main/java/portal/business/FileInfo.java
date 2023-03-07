package portal.business;

public class FileInfo {

    private static String Folder_icon="/images/icons8-folder-96.png";
    private static String Return_icon="/images/icons8-symlink-directory-96.png";
    
    private String path;
    private String filename;
    private int type;
    private String picurl;
    private String anchor;
    public FileInfo(String filename,String path,int type){
        this.filename=filename;
        this.path=path;
        this.type=type;
        switch(type){
            case 0:
                this.picurl=Folder_icon;
                break;
            case 99:
                this.picurl=Return_icon;
                break;
            default:
                this.picurl=path+"/"+filename;
                break;
        }
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
    public String getAnchor() {
        return anchor;
    }
    public void setAnchor(String anchor) {
        this.anchor = anchor;
    }
    

    
    
}
