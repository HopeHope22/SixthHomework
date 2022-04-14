package pages;

import com.codeborne.selenide.Selenide;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class POForYouTube {
    //selectors
    public SelenideElement searchInput = $("input#search");
    public SelenideElement channelNameButton = $("ytd-channel-name#channel-title");
    public SelenideElement firstActiveButton = $x("//tp-yt-paper-tab[@class=\"style-scope ytd-c4-tabbed-header-renderer\"][1]");
    public SelenideElement communityTab = $x("//tp-yt-paper-tab[@class=\"style-scope ytd-c4-tabbed-header-renderer\"][3]");

    //variables
    public String desiredValue = "ёрий дудь";
    public SelenideElement videoNameOnCard = $x("//h1/yt-formatted-string");

    //actions
    public POForYouTube setChannelName(String chanelName){
        searchInput.shouldBe(visible).setValue(chanelName).pressEnter();
        return this;
    }

    public POForYouTube openChannel(){
        channelNameButton.click();
        return this;
    }

    public POForYouTube openTabVideo(){
        firstActiveButton.click();
        return this;
    }

    public String openVideoByGuest(String guestName){
        Selenide.sleep(1500);
        SelenideElement element = Selenide.$x("//a[contains(text(),'" + guestName + "')]").should(visible);
        String titleOfVideo = element.getText();
        element.click();
        return titleOfVideo;
    }

    public String getVideoNameOnCard(){
        Selenide.sleep(1500);
        return videoNameOnCard.should(visible).getText();
    }

    public POForYouTube openTabCommunity(){
        sleep(1500);
        communityTab.click();
        return this;
    }

    public String findPostByLikes(String numberOfLikes) {
        Selenide.sleep(1500);
        SelenideElement element = Selenide.$x("//span[@id=\"vote-count-middle\" and contains(text(),'" + numberOfLikes + "')]").should(visible);
        String post = element.getText();
        element.scrollTo();
        return post;
    }
}
