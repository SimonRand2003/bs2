package domain;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ControllerTest {
    private WebDriver driver;
    private WebElement thuisploeg;
    private WebElement uitploeg;
    private WebElement zekerheid;
    private WebElement verstuurknop;
    private WebElement voorspelling;

    @Before
    public void setUp() throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        driver =  new ChromeDriver();
        driver.get("http://localhost:8081/project_war_exploded/");
        driver.findElement(By.id("voegtoe")).click();
        thuisploeg = driver.findElement(By.id("thuisploeg"));
        uitploeg = driver.findElement(By.id("uitploeg"));
        voorspelling = driver.findElement(By.id("1"));
        zekerheid = driver.findElement(By.id("zekerheid"));
        verstuurknop = driver.findElement(By.id("submitForm"));


    }
    @After
    public void clean(){
        driver.close();
    }


    @Test
    public void test_form_met_alles_lege_waarde_geeft_error_messages() throws InterruptedException {


        verstuurknop.click();
        TimeUnit.SECONDS.sleep(1);

        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis,"Ongeldige thuisploeg"));
        assertTrue(containsWebElementsWithText(lis,"Ongeldige uitploeg"));
        assertTrue(containsWebElementsWithText(lis,"Kies je voorspelling"));
        assertTrue(containsWebElementsWithText(lis,"Zekerheid moet een waarde zijn tussen 1 en 10"));
    }


    @Test
    public void test_form_met_juiste_thuisploeg_en_alles_lege_waarde_geeft_error_messages() throws InterruptedException {
        thuisploeg.sendKeys("Union");


        verstuurknop.click();
        TimeUnit.SECONDS.sleep(1);

        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis,"Ongeldige uitploeg"));
        assertTrue(containsWebElementsWithText(lis,"Kies je voorspelling"));
        assertTrue(containsWebElementsWithText(lis,"Zekerheid moet een waarde zijn tussen 1 en 10"));

    }


    @Test
    public void test_form_met_juiste_thuisploeg_en_uitploeg_en_lege_waarde_geeft_error_messages() throws InterruptedException {
        thuisploeg.sendKeys("Union");
        uitploeg.sendKeys("Club Brugge");


        verstuurknop.click();
        TimeUnit.SECONDS.sleep(1);

        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis,"Kies je voorspelling"));
        assertTrue(containsWebElementsWithText(lis,"Zekerheid moet een waarde zijn tussen 1 en 10"));

    }

    @Test
    public void test_form_met_juiste_thuisploeg_uitploeg_en_voorspelling_lege_waarde_zekerheid_geeft_error_message() throws InterruptedException {
        thuisploeg.sendKeys("Union");
        uitploeg.sendKeys("Club Brugge");
        voorspelling.click();

        verstuurknop.click();
        TimeUnit.SECONDS.sleep(1);

        ArrayList<WebElement> lis = (ArrayList<WebElement>) driver.findElements(By.tagName("li"));
        assertTrue(containsWebElementsWithText(lis,"Zekerheid moet een waarde zijn tussen 1 en 10"));

    }

    @Test
    public void test_form_met_juiste_waardes_voegt_toe() throws InterruptedException {
        thuisploeg.sendKeys("Liverpool");
        uitploeg.sendKeys("Real Madrid");
        voorspelling.click();
        zekerheid.sendKeys("9");

        verstuurknop.click();

        String match = driver.findElement(By.xpath("/html/body/main/table/tbody/tr[last()]")).getText();



        assertTrue(match.contains("LIVERPOOL"));

    }


    @Test
    public void test_verwijderItem_verwijderd_item() throws InterruptedException {
        driver.findElement(By.id("overzicht")).click();
        WebElement verwijderdknop = driver.findElement(By.className("verwijder"));
        String match = driver.findElement(By.xpath("/html/body/main/table/tbody/tr[1]")).getText();

        verwijderdknop.click();

        Alert alert = driver.switchTo().alert();
        alert.accept();


        assertTrue(!(match.contains(driver.findElement(By.xpath("/html/body/main/table/tbody/tr[1]")).getText())));

    }

    @Test
    public void test_klik_item_toevoegen_naar_overzichtjsp(){
        thuisploeg.sendKeys("Union");
        uitploeg.sendKeys("Club Brugge");
        voorspelling.click();
        zekerheid.sendKeys("5");

        verstuurknop.click();
        assertEquals(driver.getTitle(),"Overzicht");
    }

    @Test
    public void test_klik_overzicht_ga_naar_overzicht(){
        driver.findElement(By.id("overzicht")).click();
        assertEquals(driver.getTitle(),"Overzicht");
    }



    private boolean containsWebElementsWithText(ArrayList<WebElement> lis, String text) {
        for (int i=0;i<lis.size();i++){
            if (lis.get(i).getText().equals(text)){
                return true;
            }
        }
        return false;
    }


}
