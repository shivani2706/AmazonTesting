package amazonPages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

public class HomePage {

    WebDriver wb;

    @Test
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\Chrome Driver\\chromedriver.exe");
        wb = new ChromeDriver();
        wb.get("https://www.amazon.co.uk/");
        wb.manage().window().maximize();

    }

    @Test
    public void checkLogo() throws InterruptedException {

        //checking logo
        WebElement logo = wb.findElement(By.xpath("//*[@id=\"nav-logo\"]/a[1]"));
        if (logo.isDisplayed()) {
            System.out.println("Logo is displayed");
            Assert.assertTrue(logo.isDisplayed(), "Logo is not displaying");
        } else {
            System.out.println("Logo is not displayed");

        }

    }

    @Test
    public void checkScrolling() throws InterruptedException {
        //scrolling
        for (int i = 0; i <= 2; i++) {

            JavascriptExecutor js = (JavascriptExecutor) wb;
            js.executeScript("window.scrollBy(0,3900)", "");
            Thread.sleep(3000);
            //This will scroll the page Horizontally till the element is found
            js.executeScript("window.scrollBy(0,-4900)");
            Thread.sleep(3000);
        }
    }

    @Test
    public void checkMenuBar() throws InterruptedException {


        //Opens Menu bar
        wb.findElement(By.xpath("//*[@id=\"nav-hamburger-menu\"]")).click();
        Thread.sleep(5000);

        //closes menu bar
        wb.findElement(By.xpath("//*[@id=\"hmenu-canvas-background\"]/div")).click();

    }

    @Test
    public void checkDropdown() throws InterruptedException {
        //check dropdown
        Select sec1 = new Select(wb.findElement(By.xpath("//*[@id=\"searchDropdownBox\"]")));
        Thread.sleep(5000);
        // It will select 8th index value from dropdown
        sec1.selectByIndex(8);
        Thread.sleep(2000);

        //clicks search button of dropdown selected
        wb.findElement(By.xpath("//*[@id=\"nav-search\"]/form/div[2]/div/input")).click();
        Thread.sleep(2000);



    }

    @Test
    public void MouseHover() throws InterruptedException {
        //checking mouse hovering in header
        Actions actions = new Actions(wb);
        WebElement objAccountandList = wb.findElement(By.xpath("//*[@id=\"nav-link-accountList\"]/span[1]"));
        actions.moveToElement(objAccountandList).build().perform();
        Thread.sleep(2000);
        WebElement objReturnandOrder = wb.findElement(By.xpath("//*[@id=\"nav-orders\"]/span[1]"));
        actions.moveToElement(objReturnandOrder).build().perform();
        Thread.sleep(2000);
        WebElement objTryPrime = wb.findElement(By.xpath("//*[@id=\"nav-link-prime\"]"));
        actions.moveToElement(objTryPrime).build().perform();
        Thread.sleep(2000);
        WebElement objBasket = wb.findElement(By.xpath("//*[@id=\"nav-cart\"]"));
        actions.moveToElement(objBasket).build().perform();
        Thread.sleep(2000);

        WebElement objSelectYourAddress = wb.findElement(By.xpath("//*[@id=\"glow-ingress-line1\"]"));
        actions.moveToElement(objSelectYourAddress).build().perform();
        Thread.sleep(2000);
        WebElement objDeliveryCovid = wb.findElement(By.xpath("//*[@id=\"navSwmHoliday\"]/a/img\n"));
        actions.moveToElement(objDeliveryCovid).build().perform();
        Thread.sleep(2000);
    }

    @Test
    public void checkFooter() throws InterruptedException {

        // Check all the link in footer
        WebElement footer = wb.findElement(By.xpath("//*[@id=\"navFooter\"]"));
        // Get Footer element which contains all footer links
        System.out.println(footer.findElements(By.tagName("a")).size());
        int i = footer.findElements(By.tagName("a")).size(); //Get number of links

        // This loop will check all links one by one till the end
        for (int j = 0; j < i; j++) {
            //create loop based upon number of links to traverse all links
            System.out.println(j);
            footer = wb.findElement(By.xpath("//*[@id=\"navFooter\"]"));
            // We are re-creating "footer" web element as DOM changes after navigate.back()
            footer.findElements(By.tagName("a")).get(j).getText();
            footer.findElements(By.tagName("a")).get(j).click();
            Thread.sleep(3000);
            System.out.println(wb.getTitle());


            //To see if any link is broken
            if (wb.getTitle().contains("404")) {
                System.out.println("404 Found");
            }
            wb.navigate().back();
            Thread.sleep(4000);
        }
    }


    @Test
    public void checkHeader() throws InterruptedException {


        WebElement header = wb.findElement(By.xpath("//*[@id=\"nav-xshop\"]"));
        System.out.println(header.findElements(By.tagName("a")).size());
        int i = header.findElements(By.tagName("a")).size(); //Get number of links



        for (int j = 0; j < i; j++) {
            //create loop based upon number of links
            System.out.println(j);
            header = wb.findElement(By.xpath("//*[@id=\"nav-xshop\"]"));
            // Checking by anchor tag "a"
            header.findElements(By.tagName("a")).get(j).getText();
            header.findElements(By.tagName("a")).get(j).click();
            Thread.sleep(2000);
            System.out.println(wb.getTitle());

            //Checking broken links
            if (wb.getTitle().contains("404")) {
                System.out.println("404 Found");
            }
            wb.navigate().back();
            Thread.sleep(4000);
        }

    }


    public static void main(String[] args) throws InterruptedException {

        HomePage objHomePage = new HomePage();
        objHomePage.setUp();
        objHomePage.checkLogo();
        objHomePage.checkMenuBar();
        objHomePage.checkDropdown();
        objHomePage.checkScrolling();
        objHomePage.MouseHover();
        objHomePage.checkFooter();
        objHomePage.checkHeader();


        // Signing up testing
        SignInPage objSignInPage = new SignInPage();
        objSignInPage.signin();


    }
}

