package portal.business;

import java.util.List;

public class Menuitem {
    private int id;
    private String text;
    private String url;
    private List<Menuitem> child;

    public Menuitem(int id,String text,String url){
        this.id=id;
        this.text=text;
        this.url=url;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public String getUrl() {
        return url;
    }

    public List<Menuitem> getChild() {
        return child;
    }

    public void setChild(List<Menuitem> lMenuitems){
        this.child=lMenuitems;
    }

}
