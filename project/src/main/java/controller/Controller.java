package controller;

import domain.KunstwerkenDB;
import domain.Match;
import domain.MatchDB;
import exception.DomainException;

import java.io.*;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

import static domain.MatchDB.verwijderItem;
import static domain.MatchDB.vind;

@WebServlet("/Controller")
public class Controller extends HttpServlet {
    private final MatchDB matchdb= new MatchDB();
    private final KunstwerkenDB kunstwerkdb= new KunstwerkenDB();
    private static final long serialVersionUID = 1L;


    public Controller() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        processRequest(request, response);


    }
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String destination;
        String command = request.getParameter("command");
        if (command==null){
            command= "add";
        }
        switch (command) {
            case "overzicht":
                destination = overzicht(request, response);
                break;
            case "voegtoe":
                destination=voegtoe(request,response);
                break;
            case "add":
                destination = add(request,response);
                break;
            case "zoek":
                destination = zoek(request,response);
                break;
            case "verwijder":
                destination =verwijder(request,response);
                break;
            case "pasAan":
                destination= pasAan(request,response);
                break;
            case "kunstwerk":
                destination = kunstwerk(request,response);
                break;
            default:
                destination=index(request,response);
                break;
        }
        RequestDispatcher view = request.getRequestDispatcher(destination);
        view.forward(request,response);
    }

    private String kunstwerk(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("kunstwerken", kunstwerkdb.getAlleKunstwerken());
        request.setAttribute("hoogstebod", kunstwerkdb.getKunstwerkMetHoogsteBod());
        return "overzichtKunstwerken.jsp";
    }

    private String pasAan(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("thuisploegPreviousValue", matchdb.getLaatsteItem().getThuisploeg());
        request.setAttribute("uitploegPreviousValue", matchdb.getLaatsteItem().getUitploeg());
        request.setAttribute("zekerheidPreviousValue", matchdb.getLaatsteItem().getZekerheid());
        verwijderItem(matchdb.getLaatsteItem().getThuisploeg());

        return "VoegToe.jsp";
    }

    private String voegtoe(HttpServletRequest request, HttpServletResponse response) {

        request.setAttribute("laatsteMatch", matchdb.getLaatsteItem());
        return "VoegToe.jsp";
    }

    private String index(HttpServletRequest request, HttpServletResponse response) {

        return "index.jsp";
    }

    private String verwijder(HttpServletRequest request, HttpServletResponse response) {
        String thuisploeg = request.getParameter("thuisploeg");
        HttpSession session = request.getSession();

        session.setAttribute("laatstVerwijderdeMatch",vind(thuisploeg));
        verwijderItem(thuisploeg);

        request.setAttribute("matches", matchdb.getList());
        request.setAttribute("meestZekere", matchdb.getMeestZekereMatch());
        return "overzicht.jsp";
    }

    private String zoek(HttpServletRequest request, HttpServletResponse response) {
        String ploeg = request.getParameter("zoekopdracht");
        Cookie c = new Cookie("ploeg", ploeg);
        response.addCookie(c);
        try{
            Match match = matchdb.vind(ploeg);
            request.setAttribute("match", match);
        }
        catch (DomainException exc){
            request.setAttribute("error",exc.getMessage());
            return "index.jsp";

        }

        return "zoek.jsp";
    }

    private void cookies(HttpServletRequest request, HttpServletResponse response){

        Cookie cookie = getCookieWithKey(request, "ploeg");

        request.setAttribute("laatstOpgezocht", cookie.getValue());


    }


    private Cookie getCookieWithKey(HttpServletRequest request, String key) {
        Cookie[] cookies = request.getCookies();
        if (cookies==null){
            return null;
        }
        for (Cookie cookie: cookies){
            if (cookie.getName().equals(key)){
                return cookie;
            }
        }
        return null;
    }

    private String overzicht(HttpServletRequest request, HttpServletResponse response) {
        request.setAttribute("matches", matchdb.getList());
        request.setAttribute("meestZekere", matchdb.getMeestZekereMatch());
        return "overzicht.jsp";
    }
    private String add (HttpServletRequest request, HttpServletResponse response){
        ArrayList<String> errors = new ArrayList<String>();

        Match match = new Match();
        setThuisploeg(match,request, errors);
        setUitploeg(match,request, errors);
        setVoorspelling(match,request, errors);
        setZekerheid(match,request, errors);

        if (errors.size() == 0) {
            try {
                matchdb.addMatch(match);
                return overzicht(request, response);
            } catch (DomainException exc) {
                errors.add(exc.getMessage());
            }
        }

        request.setAttribute("errors", errors);
        return "VoegToe.jsp";
    }
    private void setThuisploeg(Match match, HttpServletRequest request, ArrayList<String> errors) {
        String thuisploeg = request.getParameter("thuisploeg");
        boolean thuisploegHasErrors = false;
        try {
            request.setAttribute("thuisploegPreviousValue", thuisploeg);
            match.setThuisploeg(thuisploeg);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            thuisploegHasErrors = true;
        } finally {
            request.setAttribute("thuisploegHasErrors", thuisploegHasErrors);
        }
    }
    private void setUitploeg(Match match, HttpServletRequest request, ArrayList<String> errors) {
        String uitploeg = request.getParameter("uitploeg");
        boolean uitploegHasErrors = false;
        try {
            request.setAttribute("uitploegPreviousValue", uitploeg);
            match.setUitploeg(uitploeg);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            uitploegHasErrors = true;
        } finally {
            request.setAttribute("naamHasErrors", uitploegHasErrors);
        }
    }

    private void setVoorspelling(Match match, HttpServletRequest request, ArrayList<String> errors) {
        String voorspelling = request.getParameter("voorspelling");
        boolean voorspellingHasErrors = false;
        try {
            request.setAttribute("voorspellingPreviousValue", voorspelling);
            match.setVoorspelling(voorspelling);
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            voorspellingHasErrors = true;
        } finally {
            request.setAttribute("voorspellingHasErrors", voorspellingHasErrors);
        }
    }

    private void setZekerheid(Match match, HttpServletRequest request, ArrayList<String> errors) {
        String zekerheid = request.getParameter("zekerheid");
        boolean zekerheidHasErrors = false;
        try {
            request.setAttribute("zekerheidPreviousValue", zekerheid);
            match.setZekerheid(Integer.parseInt(zekerheid));
        } catch (NumberFormatException exc) {
            errors.add("Zekerheid moet een waarde zijn tussen 1 en 10");
            zekerheidHasErrors = true;
        } catch (DomainException exc) {
            errors.add(exc.getMessage());
            zekerheidHasErrors = true;
        } finally {
            request.setAttribute("zekerheidHasErrors", zekerheidHasErrors);
        }
    }

    public void destroy() {
    }
}