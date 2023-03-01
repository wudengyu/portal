package portal.business;

public class FileInfo {

    private static String Folder_icon="/images/folder.bmp";

    private String filename;
    private int type;
    private String path;
    private String picurl;
    public FileInfo(String filename,String path,int type){
        this.filename=filename;
        this.path=path;
        this.type=type;
        switch(type){
            case 0:
                this.picurl=Folder_icon;
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

    
    
}
