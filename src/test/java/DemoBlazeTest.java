import io.github.bonigarcia.wdm.WebDriverManager;
import io.github.bonigarcia.wdm.managers.ChromeDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertEquals;

public class DemoBlazeTest {
    private static WebDriverManager wdm = new ChromeDriverManager();
    private static WebDriver driver;
    private static WebDriverWait wdwait;

    @BeforeTest
    public void beforeTest() {
        wdm.setup();
        driver = new ChromeDriver();
        driver.navigate().to("https://demoblaze.com/");
    }

    @BeforeMethod
    public void setUp(){
        driver.manage().window().maximize();
    }

    @Test (enabled = false)  //enabled = false  ako zelimo da se test ne izvrsava dok radimo druge
    public void signUp() throws InterruptedException {
        WebElement signUpButton = driver.findElement(new By.ById("signin2"));
        signUpButton.click();
        WebElement username = driver.findElement(new By.ById("sign-username"));
        username.sendKeys("milica99");  //menjati username
        WebElement password = driver.findElement(new By.ById("sign-password"));
        password.sendKeys("comibg");
        WebElement registerBtn = driver.findElement(new By.ByCssSelector("#signInModal > div > div > div.modal-footer > button.btn.btn-primary"));
        registerBtn.click();
        Thread.sleep(1000);
        driver.switchTo().alert().getText();
        System.out.println(driver.switchTo().alert().getText());
        assertEquals(driver.switchTo().alert().getText(), "Sign up successful.");
        driver.switchTo().alert().accept(); //da se pritisne ok na prozoru koji iskoci
    }

    @Test (enabled = false)
    public void logIn() throws InterruptedException {
        WebElement logInButton = driver.findElement(new By.ById("login2"));
        logInButton.click();
        WebElement username = driver.findElement(new By.ById("loginusername"));
        Thread.sleep(1000);
        username.sendKeys("milica99");  //menjati username
        WebElement password = driver.findElement(new By.ById("loginpassword"));
        password.sendKeys("comibg");
        List<WebElement> buttons = driver.findElements(new By.ByTagName("button"));
        WebElement loginBtn = driver.findElement(new By.ByTagName("button"));
        for(WebElement we : buttons) {
            System.out.println(we + " " + we.getAttribute("onclick"));
            if (we.getAttribute("onclick") != null){
                if(we.getAttribute("onclick").equals("logIn()")){
                    loginBtn = we;
                    break;
                }
            }

        }

       loginBtn.click();
        Thread.sleep(5000);
        WebElement nameOfUser = driver.findElement(new By.ById("nameofuser"));
        System.out.println(nameOfUser+ " " + nameOfUser.getText()); //da ispise koji text izlazi
         assertEquals(nameOfUser.getText(), "Welcome milica99");
    }

    @Test (enabled = false)
    public void logOut() throws InterruptedException {
        WebElement logInButton = driver.findElement(new By.ById("login2"));
        logInButton.click();
        WebElement username = driver.findElement(new By.ById("loginusername"));
        Thread.sleep(1000);
        username.sendKeys("milica99");  //menjati username
        WebElement password = driver.findElement(new By.ById("loginpassword"));
        password.sendKeys("comibg");
        List<WebElement> buttons = driver.findElements(new By.ByTagName("button"));
        WebElement loginBtn = driver.findElement(new By.ByTagName("button"));
        for(WebElement we : buttons) {
            System.out.println(we + " " + we.getAttribute("onclick"));
            if (we.getAttribute("onclick") != null){
                if(we.getAttribute("onclick").equals("logIn()")){
                    loginBtn = we;
                    break;
                }
            }

        }

        loginBtn.click();
        Thread.sleep(3000);
        WebElement logOutButton = driver.findElement(new By.ById("logout2"));
        logOutButton.click();
        Thread.sleep(3000);
        List <WebElement> loginButton = driver.findElements(new By.ById("login2"));
        assertEquals(loginButton.isEmpty(), false); //prvi nacin - proveravamo da li login postoji ili ne
        assertEquals(!(loginButton.isEmpty()),true); //drugi nacin (ako ne postoji ocekujemo false, ako postoji true

    }

    @Test
    public void addToCart() throws InterruptedException {
        WebElement logInButton = driver.findElement(new By.ById("login2"));
        logInButton.click();
        WebElement username = driver.findElement(new By.ById("loginusername"));
        Thread.sleep(1000);
        username.sendKeys("milica99");  //menjati username
        WebElement password = driver.findElement(new By.ById("loginpassword"));
        password.sendKeys("comibg");
        List<WebElement> buttons = driver.findElements(new By.ByTagName("button"));
        WebElement loginBtn = driver.findElement(new By.ByTagName("button"));
            for(WebElement we : buttons) {
               // System.out.println(we + " " + we.getAttribute("onclick"));
                if (we.getAttribute("onclick") != null){
                    if(we.getAttribute("onclick").equals("logIn()")){
                        loginBtn = we;
                        break;
                    }
                }

            }
            loginBtn.click();
            Thread.sleep(2000);
            List<WebElement> categories = driver.findElements(new By.ById("itemc"));
            for(WebElement we : categories){
                if(we.getAttribute("onclick") != null){
                }
                if(we.getAttribute("onclick").equals("byCat('monitor')")){
                    we.click();
                    break;
                }
            }
            Thread.sleep(2000);
            List<WebElement> monitorLinks = driver.findElements(new By.ByTagName("a"));
            for(WebElement we : monitorLinks){
                System.out.println(we + " " + we.getAttribute("href"));
                if(we.getAttribute("href").equals("https://demoblaze.com/prod.html?idp_=14")){
                    System.out.println(we + " " + we.getText());
                    Thread.sleep(3000);
                we.click();
                break;
            }
        }
            Thread.sleep(3000);
            List<WebElement> links = driver.findElements(new By.ByTagName("a"));
            for(WebElement we : links){
            if(we.getText().equals("Add to cart")){
                we.click();
                break;
            }
        }
            Thread.sleep(2000);
            assertEquals(driver.switchTo().alert().getText(), "Product added.");
            driver.switchTo().alert().accept();


    }
    @AfterTest
    public void cleanup() throws InterruptedException {
        Thread.sleep(2000);
        driver.quit();
    }

}
