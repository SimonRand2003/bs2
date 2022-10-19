package domain;

import exception.DomainException;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class MatchDBTest {

    private static final ArrayList<Match> matches = new ArrayList<Match>();
    private MatchDB matchDB;
    private Match match;

    @org.junit.Before

    public void setUp() throws Exception{
        matchDB= new MatchDB();
        match = new Match("Arsenal","Spurs","1",5);
    }

    @Test(expected = DomainException.class)
    public void test_addMatch_met_waarde_null_gooit_exception(){
        matchDB.addMatch(null);
    }
    @Test
    public void test_addMatch_met_object_Match_voegt_match_toe(){
        matchDB.addMatch(match);
        assertEquals(matchDB.getList().get(matchDB.getList().size()-1),match);
    }

    @Test(expected = DomainException.class)
    public void test_vind_met_waarde_null_gooit_exception(){
        matchDB.vind(null);
    }

    @Test(expected = DomainException.class)
    public void test_vind_met_waarde_spaties_gooit_exception(){
        matchDB.vind("   ");
    }


    @Test
    public void test_vind_met_geldige_string_vind_ploeg(){
        Match match = new Match("Dortmund","Wolfsburg","1",5);
        matchDB.addMatch(match);
        assertEquals(matchDB.vind("Dortmund"),match);
        assertEquals(matchDB.vind("djldjn"),null);
    }

    @Test(expected = DomainException.class)
    public void test_verwijderItem_met_waarde_null_gooit_exception(){
        matchDB.verwijderItem(null);
    }

    @Test(expected = DomainException.class)
    public void test_verwijderItem_met_spaties_gooit_exception(){
        matchDB.verwijderItem("    ");
    }
}
