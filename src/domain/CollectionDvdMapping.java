package domain;

public class CollectionDvdMapping {

    private int id;
    private int fkCollection;
    private int fkDvd;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkCollection() {
        return fkCollection;
    }

    public void setFkCollection(int fkCollection) {
        this.fkCollection = fkCollection;
    }

    public int getFkDvd() {
        return fkDvd;
    }

    public void setFkDvd(int fkDvd) {
        this.fkDvd = fkDvd;
    }

}
