package TestScenarios;

import Objects.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.time.Duration;


public class CommentsAppTest {

    public  WebDriver driver;

    HomePage homePage;

    public int commentCount;
    public Object[][] dataset = {
            { "John", "Innovative and interconnected systems." },
            { "Alice", "Smart factories, improved efficiency." },
            { "Robert", "AI-powered automation, limitless potential." },
            { "Harry", "Data-driven decision-making, game-changer." },
            { "Bob", "IoT revolutionizing daily life." }
    };

    @BeforeMethod
    public void SetUp(){
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        homePage = new HomePage(driver);
        driver.get("https://qacommentsapp.ccbp.tech/");
        commentCount =0;
    }

    @AfterMethod
    public void TearDown(){
        driver.quit();
    }

    @DataProvider
    public Object[][] commentsData() {
        return dataset;
    }

    public void addCommentsData() {
        for (Object[] data : dataset) {
            String name = String.valueOf(data[0]);
            String comment = String.valueOf(data[1]);
            homePage.addComment(name, comment);
            commentCount++;
        }
    }

    @Test(priority = 1)
    public void  CommentsAppUI(){
        //Navigate to the URL https://qacommentsapp.ccbp.tech/
        //Verify if the App image is displayed - use Assertions,
        //If the App image is not displayed, print "App image is not displayed"

        Assert.assertTrue(homePage.findTheAppImage().isDisplayed(),"App image is not displayed");

        //Verify the Heading text of the app - use Assertions
        //Expected text: Comments
        //If the Heading text does not match the expected text, print "Heading text does not match"

        Assert.assertEquals(homePage.GetAppHeading(),"Comments","Heading text does not match");

        //Verify the Description text of the app - use Assertions
        //Expected text: Say something about 4.0 Technologies
        //If the Description text does not match the expected text, print "Description text does not match"

        Assert.assertEquals(homePage.GetAppDescription(),"Say something about 4.0 Technologies","Description text does not match");

        //Verify the Comments count text of the app - use Assertions
        //Expected text: 0Comments
        //If the Comments count text does not match the expected text, print "Comments count text does not match"

        Assert.assertEquals(homePage.getCommentsCountText(), "0Comments", "Comments count text does not match");
    }

    //Test the comment counter
    //Navigate to the URL https://qacommentsapp.ccbp.tech/
    //Add the comments from the dataset table.
    //For each comment added, verify whether the comment count is updated respectively.
    //If the count does not match the expected count, print "Comments count do not match"
    //Close the browser window.

    @Test(priority = 2)
    public void testCommentsCount() {
        for (Object[] data : dataset) {
            String name = String.valueOf(data[0]);
            String comment = String.valueOf(data[1]);
            homePage.addComment(name, comment);
            commentCount++;

            int actualCount = homePage.getCommentsCount();
            Assert.assertEquals(actualCount, commentCount, "Comments count do not match");
        }
    }

    //Test the comment initials
    //Navigate to the URL https://qacommentsapp.ccbp.tech/
    //Add the comments from the dataset table individually - use DataProvider.
    //Verify whether the username initials are displayed properly.
    //If the initial does not match the expected initial, print "Initial do not match"
    //Close the browser window.

    @Test(priority = 3, dataProvider = "commentsData")
    public void testCommentsInitial(String name, String comment) {
        homePage.addComment(name, comment);

        char initial = homePage.getInitialText();
        Assert.assertEquals(initial, name.charAt(0), "Initial do not match");
    }

    //Test the comment order
    //Navigate to the URL https://qacommentsapp.ccbp.tech/
    //Add the comments from the dataset table.
    //Verify whether the username and the comment are displayed in the order they are added.
    //If the order does not match the expected order, print "Order does not match"
    //If the comment does not correspond to the username, print "Comment does not correspond to the username"
    //Close the browser window.


    @Test(priority = 4)
    public void testCommentsAndOrder() {
        addCommentsData();

        for (int i = 0; i < dataset.length; i++) {
            Object[] data = dataset[i];
            String name = String.valueOf(data[0]);
            String comment = String.valueOf(data[1]);
            Assert.assertEquals(homePage.getUsernameAt(i), name, "Order does not match");
            Assert.assertEquals(homePage.getCommentAt(i), comment, "Comment does not correspond to the username");
        }
    }

    //Test the likes
    //Navigate to the URL https://qacommentsapp.ccbp.tech/
    //Add the comments from the dataset table.
    //For each like button clicked, verify whether the total comments liked are as expected
    //If the liked count does not match the expected count, print "Likes count do not match"
    //Close the browser window.

    @Test(priority = 5)
    public void testCommentLikes() {
        addCommentsData();

        int likesCount = 0;

        for (int i = 0; i < dataset.length; i++) {
            homePage.clickLikeButtonAt(i);

            likesCount++;

            Assert.assertEquals(homePage.getLikedCommentsCount(), likesCount, "Likes count do not match");
        }
    }


    //Test delete functionality
    //Navigate to the URL https://qacommentsapp.ccbp.tech/
    //Add the comments from the dataset table.
    //For each comment deleted, verify whether the total comments count is as expected
    //If the comment count does not match the expected count, print "Comments count do not match"
    //Close the browser window.


    @Test(priority = 6)
    public void testCommentDeletes() {
        addCommentsData();

        for (int i = 0; i < dataset.length; i++) {
            homePage.clickFirstDeleteButton();

            commentCount--;

            int actualCount = homePage.getCommentsCount();
            Assert.assertEquals(actualCount, commentCount, "Comments count do not match");
        }
    }
}
