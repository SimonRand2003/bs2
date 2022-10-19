package domain;
// pas naam van de package aan naar je eigen situatie

import exception.DomainException;
import domain.Kunstwerk;

import java.util.ArrayList;
import java.util.List;

public class KunstwerkenDB {
    private int sequence = 0;
    private final List<Kunstwerk> kunstwerken = new ArrayList<>();

    public KunstwerkenDB() {
        this.add(new Kunstwerk("Salvator Mundi", "Leonardo da Vinci", 1500, 38300));
        this.add(new Kunstwerk("Interchange", "Willem de Kooning", 1955, 25500));
        this.add(new Kunstwerk("De Kaartspelers", "Paul Cézanne", 1893, 21200));
        this.add(new Kunstwerk("Nafea Faa Ipoipo", "Paul Gauguin", 1892, 17800));
        this.add(new Kunstwerk("Number 17A", "Jackson Pollock", 1948, 17000));
        this.add(new Kunstwerk("Wasserschlangen II", "Gustav Klimt", 1500, 164000));
        this.add(new Kunstwerk("Schreeuw", "Da vinci", 900, 1000200));

    }

    public void add(Kunstwerk kunstwerk) {
        if (kunstwerk == null)
            throw new DomainException("Geen geldig kunstwerk om toe te voegen");
        this.sequence++;
        kunstwerk.setId(sequence);
        kunstwerken.add(kunstwerk);
    }

    public List<Kunstwerk> getAlleKunstwerken() {
        return kunstwerken;
    }

    public Kunstwerk getKunstwerkMetHoogsteBod() {
        if (kunstwerken.size() == 0)
            return null;
        Kunstwerk kunstwerkMetHoogsteBod = kunstwerken.get(0);
        for (Kunstwerk kunstwerk : kunstwerken) {
            if (kunstwerk.getBod() > kunstwerkMetHoogsteBod.getBod())
                kunstwerkMetHoogsteBod = kunstwerk;
        }
        return kunstwerkMetHoogsteBod;
    }

    @Override
    public String toString() {
        String out = "";
        for (Kunstwerk kunstwerk : getAlleKunstwerken()
        ) {
            out += kunstwerk.toString() + "\n";
        }
        return out;
    }
}
