package ohtu;

import java.util.Random;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Tester {
    
    private static WebDriver driver;
    private static WebElement element;

    public static void main(String[] args) {
        driver = new ChromeDriver();
        Random random = new Random();

        driver.get("http://localhost:4567");
        
        sleep(2);

        selectLogin();
        sleep(2);
        
        login("pekka", "akkep");
        sleep(3);
        
        logout();
        sleep(2);
        
        selectLogin();
        sleep(2);
        
        login("pekka", "pekka");
        sleep(2);
        login("akkep", "pekka");
        sleep(2);
        element = driver.findElement(By.linkText("back to home"));
        element.click();
        sleep(2);
        register("jukka" + random.nextInt(), "akkuj");
        
        sleep(2);
        element = driver.findElement(By.linkText("continue to application mainpage"));
        element.click();
        
        sleep(2);
        logout();
        
        driver.quit();
    }
    
    private static void sleep(int n){
        try{
            Thread.sleep(n*1000);
        } catch(Exception e){}
    }
    
    private static void login(String username, String password) {
        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("login"));
        
        sleep(2);
        element.submit();
    }
    
    private static void register(String username, String password) {
        element = driver.findElement(By.linkText("register new user"));
        element.click();

        sleep(2);

        element = driver.findElement(By.name("username"));
        element.sendKeys(username);
        element = driver.findElement(By.name("password"));
        element.sendKeys(password);
        element = driver.findElement(By.name("passwordConfirmation"));
        element.sendKeys(password);
        element = driver.findElement(By.name("signup"));
        
        sleep(2);
        element.submit();
    }
    
    private static void selectLogin() {
        element = driver.findElement(By.linkText("login"));
        element.click();
    }
    
    private static void logout() {
        element = driver.findElement(By.linkText("logout"));
        element.click();
    }
}
