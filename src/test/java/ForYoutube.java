import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import pages.POForYouTube;

import static com.codeborne.selenide.Selenide.*;

public class ForYoutube {

    POForYouTube POForYouTube = new POForYouTube();

    @BeforeEach
    void openPage() {
        Configuration.browserSize = "1920x1080";
        open("https://youtube.com/");
        sleep(1500);
    }

    @AfterEach
    void closeBrowser(){
        closeWebDriver();
    }

    @ValueSource(strings = {"Дудь", "Utopia", "Топлес"})
    @ParameterizedTest(name = "Проверить поиск канала \"{0}\"")
    void checkChanelName(String chanelName) {
        POForYouTube
                .setChannelName(chanelName)
                .openChannel();
        }

    @ValueSource(strings = {"Эйдельман", "Акунин", "Комиссаренко"})
    @ParameterizedTest(name = "Проверить соответствие названия видео с гостем \"{0}\"в разделе и в карточке ")
    void checkVideoName(String guestName) {
        POForYouTube
                .setChannelName("Юрий дудь")
                .openChannel()
                .openTabVideo();
        String videoNameOnTab = POForYouTube.openVideoByGuest(guestName);
        String videoNameOnCard = POForYouTube.getVideoNameOnCard();

        Assertions.assertEquals(videoNameOnTab, videoNameOnCard, "Названия видео не соответствуют друг другу");
    }

    @ValueSource(strings = {"11", "23", "27"})
    @ParameterizedTest(name = "Проверить наличие записи в сообществе с \"{0}\" лайков")
    void findACommunityPost(String communityPost) {
        POForYouTube
                .setChannelName("Юрий дудь")
                .openChannel()
                .openTabCommunity()
                .findPostByLikes(communityPost);
    }
}