package filemodel.fileentity;

import java.sql.Date;

public class Fileenti {
    private String name;
    private String path;
    private double size;
    private Date lastdatemodified;
    private Date datecreated;
    private long id;
    private long In_Folder;

    public long getIn_Folder() {
        return In_Folder;
    }

    public void setIn_Folder(long in_Folder) {
        In_Folder = in_Folder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public Date getLastdatemodified() {
        return lastdatemodified;
    }

    public void setLastdatemodified(Date lastdatemodified) {
        this.lastdatemodified = lastdatemodified;
    }

    public Date getDatecreated() {
        return datecreated;
    }

    public void setDatecreated(Date datecreated) {
        this.datecreated = datecreated;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}