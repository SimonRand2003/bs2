package domain;

import exception.DomainException;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class MatchTest {
    private String geldigethuisploeg, geldigeuitploeg, geldigevoorspelling;
    int  geldigezekerheid;
    private Match match;

    @org.junit.Before

    public void setUp() throws Exception{
        this.geldigethuisploeg ="OHL";
        this.geldigeuitploeg = "GENK";
        this.geldigevoorspelling ="1";
        this.geldigezekerheid = 5;
        match = new Match(geldigethuisploeg,geldigeuitploeg,geldigevoorspelling,geldigezekerheid);


    }

    @Test (expected = DomainException.class)
    public void test_met_thuisploeg_als_naam_null_gooit_exception(){
        new Match(null,geldigeuitploeg,geldigevoorspelling,geldigezekerheid);
    }

    @Test (expected = DomainException.class)
    public void test_met_uitploeg_als_naam_null_gooit_exception(){
        new Match(geldigethuisploeg,null,geldigevoorspelling,geldigezekerheid);
    }


    @Test (expected = DomainException.class)
    public void test_met_voorspelling_null_gooit_exception(){
        new Match(geldigethuisploeg,geldigeuitploeg,null,geldigezekerheid);
    }


    @Test (expected = DomainException.class)
    public void test_met_thuisploeg_als_naam_spaties_gooit_exception(){
        new Match("   ",geldigeuitploeg,geldigevoorspelling,geldigezekerheid);
    }


    @Test (expected = DomainException.class)
    public void test_met_uitploeg_als_naam_spaties_gooit_exception(){
        new Match(geldigethuisploeg,"   ",geldigevoorspelling,geldigezekerheid);
    }


    @Test (expected = DomainException.class)
    public void test_met_zekerheid_negatief_gooit_exception(){
        new Match(geldigethuisploeg,geldigeuitploeg,geldigevoorspelling,-1);
    }

    @Test (expected = DomainException.class)
    public void test_met_zekerheid_0_gooit_exception(){
        new Match(geldigethuisploeg,geldigeuitploeg,geldigevoorspelling,0);
    }

    @Test
    public void test_match_met_geldige_waarde_maakt_object(){
        Match match = new Match(geldigethuisploeg,geldigeuitploeg,geldigevoorspelling,geldigezekerheid);
        assertEquals(geldigethuisploeg,match.getThuisploeg());
        assertEquals(geldigeuitploeg,match.getUitploeg());
        assertEquals(geldigevoorspelling,match.getVoorspelling());
        assertEquals(geldigezekerheid,match.getZekerheid());

    }

    @Test (expected = DomainException.class)
    public void test_setThuisploeg_met_waarde_null_gooit_exception(){
        match.setThuisploeg(null);
    }

    @Test (expected = DomainException.class)
    public void test_setThuisploeg_met_waarde_spaties_gooit_exception(){
        match.setThuisploeg("    ");
    }


    @Test (expected = DomainException.class)
    public void test_setUitploeg_met_waarde_null_gooit_exception(){
        match.setUitploeg(null);
    }

    @Test (expected = DomainException.class)
    public void test_setUit_met_waarde_spaties_gooit_exception(){
        match.setUitploeg("    ");
    }

    @Test (expected = DomainException.class)
    public void test_setVoorspelling_met_waarde_null_gooit_exception(){
        match.setVoorspelling(null);
    }

    @Test (expected = DomainException.class)
    public void test_setZekerheid_met_waarde_nul_gooit_exception(){
        match.setZekerheid(0);
    }

    @Test (expected = DomainException.class)
    public void test_setZekerheid_met_waarde_negatief_gooit_exception(){
        match.setZekerheid(-1);
    }



















}
