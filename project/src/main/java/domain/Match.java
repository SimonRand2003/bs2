package domain;

import exception.DomainException;

public class Match {
    private String thuisploeg, uitploeg, voorspelling;
    int zekerheid;


    public Match(String thuisploeg, String uitploeg, String voorspelling, int zekerheid){
        setThuisploeg(thuisploeg);
        setUitploeg(uitploeg);
        setVoorspelling(voorspelling);
        setZekerheid(zekerheid);
    }


    public Match(){}

    public String getThuisploeg(){
        return thuisploeg;
    }

    public String getUitploeg(){
        return uitploeg;
    }

    public String getVoorspelling(){
        return voorspelling;
    }

    public int getZekerheid(){
        return zekerheid;
    }

    public void setThuisploeg(String thuisploeg) {
        if (thuisploeg==null||thuisploeg.isBlank()){
            throw new DomainException("Ongeldige thuisploeg");
        }

        this.thuisploeg=thuisploeg.toUpperCase();
    }

    public void setUitploeg(String uitploeg) {
        if (uitploeg==null||uitploeg.isBlank()){
            throw new DomainException("Ongeldige uitploeg");
        }
        this.uitploeg=uitploeg.toUpperCase();
    }
    public void setVoorspelling(String voorspelling) {
        if(voorspelling==null||voorspelling.isBlank()){
            throw new DomainException("Kies je voorspelling");
        }
        this.voorspelling=voorspelling;
    }

    public void setZekerheid(int zekerheid) {
        if (zekerheid<1 || zekerheid>10){
            throw new DomainException("Zekerheid moet een waarde zijn tussen 1 en 10");
        }

        this.zekerheid=zekerheid;
    }

    public String toString(){
        return this.thuisploeg +"-"+ this.uitploeg;
    }

}
