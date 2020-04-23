package amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class SignInPage {
    WebDriver wb;

    public void signin() throws InterruptedException {

        System.setProperty("webdriver.chrome.driver", "C:\\Chrome Driver\\chromedriver.exe");
        wb = new ChromeDriver();
        wb.get("https://www.amazon.co.uk/");
        wb.manage().window().maximize();


        Actions actions = new Actions(wb);
        WebElement objAccountandList = wb.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]"));
        actions.moveToElement(objAccountandList).build().perform();

        // Passing value in the textbox
        wb.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/span[1]")).click();
        wb.findElement(By.xpath("//*[@id=\"ap_email\"]")).click();
        wb.findElement(By.xpath("//*[@id=\"ap_email\"]")).sendKeys("automationjava2706@gmail.com");
        Thread.sleep(1500);
        wb.findElement(By.xpath("//*[@id=\"continue\"]")).click();
        Thread.sleep(1500);
        wb.findElement(By.xpath("//*[@id=\"ap_password\"]")).sendKeys("shivani2706");
        Thread.sleep(1500);

        //Keep me signed check button
        wb.findElement(By.xpath("//*[@id=\"authportal-main-section\"]/div[2]/div/div/div/form/div/div[2]/div/div/label/div/label/input")).click();

        wb.findElement(By.xpath("//*[@id=\"signInSubmit\"]")).click();
        Thread.sleep(20000);
        // clicking don't need otp on this browser
        wb.findElement(By.xpath("//*[@id=\"auth-mfa-form\"]/div/div/div[2]/div/label/span")).click();
        // Firing SignInButton
        wb.findElement(By.xpath("//*[@id=\"auth-signin-button\"]")).click();

    }

    public static void main(String[] args) throws InterruptedException {
        SignInPage objSignInPage = new SignInPage();
        objSignInPage.signin();
    }
}
