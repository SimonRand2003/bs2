package domain;

import exception.DomainException;

import java.util.ArrayList;

public class MatchDB {
    int sequence;
    private static final ArrayList<Match> matches = new ArrayList<Match>();

    public MatchDB(){
        this.addMatch(new Match("OHL", "GENK", "1", 5));
        this.addMatch(new Match("MAN CITY", "WOLVES", "1", 6));
    }

    public static void addMatch(Match match){
        if (match==null){
            throw new DomainException("Een match kan niet toegevoegd worden als deze null is");
        }
        matches.add(match);
    }
    public ArrayList<Match> getList(){
        return matches;
    }

    public Match getMeestZekereMatch(){
        if (matches.size()==0){
            return null;
        }
        Match meestZekere = matches.get(0);
        for (Match match : matches){
            if (match.getZekerheid()> meestZekere.getZekerheid()){
                meestZekere=match;
            }
        }
        return meestZekere;
    }

    public static Match vind(String ploeg){
        if (ploeg==null || ploeg.isBlank()){
            throw new DomainException("Match mag niet leeg zijn");
        }
        for (Match match: matches){
            if (match.getThuisploeg().toUpperCase().trim().equals(ploeg.toUpperCase().trim()) || match.getUitploeg().toUpperCase().trim().equals(ploeg.toUpperCase().trim())){
                return match;
            }
        }

        return null;
    }

    public static void verwijderItem(String match){
        if (match == null||match.isBlank()){
            throw new DomainException("Item kan enkel verwijderd worden als er string wordt meegegeven");
        }
        Match matchObject = vind(match);
        int index =matches.indexOf(matchObject);
        matches.remove(index);
    }

    public Match getLaatsteItem(){
        if (matches.size()==0){
            return null;
        }
        return matches.get(matches.size()-1);
    }
}
