package Objects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;


public class HomePage {

    //Define the locators for the Home page

    //App image
    By AppImageLocator = By.cssSelector("img[class*='image']");

    //App heading

    By AppHeadingLocator = By.cssSelector("h1[class*='app-heading']");

    //App description

    By AppDescriptionLocator = By.cssSelector("p[class*='form-description']");

    //Name input field

    By NameInputLocator = By.cssSelector("input[class*='name-input']");


    //Name input field










    //Comment input field
    By commentInputLocator = By.className("comment-input");

    //"Add Comment" button
    By addCommentButtonLocator = By.className("add-button");

    //Comment counter text
    By commentCounterLocatorText = By.className("heading");

    //Comment counter element()
    By commentCounterLocator = By.className("comments-count");

    //Initial text
    By initialLocator = By.className("initial-container");

    //Comment username text
    By usernamesLocator = By.className("username");

    //Comment text
    By commentsLocator = By.className("comment");

    //List of like buttons
    By likeButtonsLocator = By.xpath("//button[text() = 'Like']");


    //List of liked comments
    By likedCommentsLocator = By.className("active");

    //List of delete buttons
    By deleteButtonsLocator = By.xpath("//img[@class = 'delete']/parent::button");


    //___________________________________________________________________________________________________

    WebDriver driver;

    public HomePage(WebDriver driver){

        this.driver = driver;

    }

    //Define the methods for performing actions on the elements

    //Find the app image
    public WebElement findTheAppImage(){
              return driver.findElement(AppImageLocator);
    }

    //Get the text content of the app heading

    public String GetAppHeading(){
        return driver.findElement(AppHeadingLocator).getText();

    }

    //Get the text content of the app description

    public String GetAppDescription(){
        return driver.findElement(AppDescriptionLocator).getText();
    }


    //Enter a text in the name input field

    public void enterName(String name) {
        driver.findElement(NameInputLocator).sendKeys(name);
    }


    //Enter a text in the comment input field
    public void enterComment(String comment) {
        driver.findElement(commentInputLocator).sendKeys(comment);
    }

    //Click the "Add Comment" button
    public void clickOnAddCommentButton(){
        driver.findElement(addCommentButtonLocator).click();
    }


    //Add a comment with a specified name and comment
    public void addComment(String name, String comment) {
        enterName(name);
        enterComment(comment);
        clickOnAddCommentButton();
    }

    //Get the text content of the comment counter text
    public String getCommentsCountText() {
        return driver.findElement(commentCounterLocatorText).getText();
    }


    //Get the comment count
    public int getCommentsCount() {
        return Integer.parseInt(driver.findElement(commentCounterLocator).getText());
    }


    //Get the text content of the initial text

    public char getInitialText() {
        return driver.findElement(initialLocator).getText().charAt(0);
    }

    //Get the username text at a specified index

    public String getUsernameAt(int index) {
        return driver.findElements(usernamesLocator).get(index).getText();
    }


    //Get the comment text at a specified index

    public String getCommentAt(int index) {
        return driver.findElements(commentsLocator).get(index).getText();
    }

    //Click the like button at a specified index

    public void clickLikeButtonAt(int index) {
        driver.findElements(likeButtonsLocator).get(index).click();
    }

    //Get the count of the liked comments

    public int getLikedCommentsCount() {
        return driver.findElements(likedCommentsLocator).size();
    }

    //Click the first delete button
    public void clickFirstDeleteButton() {
        driver.findElements(deleteButtonsLocator).get(0).click();
    }
}
