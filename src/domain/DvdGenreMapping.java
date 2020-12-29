package domain;

public class DvdGenreMapping {

    private int id;
    private int fkDvd;
    private int fkGenre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getFkDvd() {
        return fkDvd;
    }

    public void setFkDvd(int fkDvd) {
        this.fkDvd = fkDvd;
    }

    public int getFkGenre() {
        return fkGenre;
    }

    public void setFkGenre(int fkGenre) {
        this.fkGenre = fkGenre;
    }


}
