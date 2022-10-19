package domain;

import exception.DomainException;

public class Kunstwerk {
    private int id;
    private String naam;
    private String artiest;
    private int jaar;
    private int bod;

    public Kunstwerk(String naam, String artiest, int jaar, int bod) {
        this.naam = naam;
        this.artiest = artiest;
        this.jaar = jaar;
        this.bod = bod;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNaam() {
        return naam;
    }

    public void setNaam(String naam) {
        if (naam == null || naam.isEmpty())
            throw new DomainException("De naam van het kunstwerk mag niet leeg zijn");
        this.naam = naam;
    }

        public String getArtiest() {
        return artiest;
    }

    public void setArtiest(String artiest) {
        if (artiest == null || artiest.isEmpty())
            throw new DomainException("De artiest mag niet leeg zijn");
        this.artiest = artiest;
    }


    public int getJaar() {
        return jaar;
    }

    public void setJaar(int jaar) {
        if (jaar < 0)
            throw new DomainException("Het jaartal moet een positief getal zijn");
        this.jaar = jaar;
    }

    public int getBod() {
        return bod;
    }

    public void setBod(int bod) {
        if (bod <= 0)
            throw new DomainException("Het bod moet een positief getal zijn");
        this.bod = bod;
    }

    @Override
    public String toString() {
        return getNaam() + " van " + getArtiest() + " (" + getJaar() + ") hoogste bod: €" + getBod() + ".";
    }
}
