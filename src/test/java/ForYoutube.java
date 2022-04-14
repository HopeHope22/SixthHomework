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

    @ValueSource(strings = {"����", "Utopia", "������"})
    @ParameterizedTest(name = "��������� ����� ������ \"{0}\"")
    void checkChanelName(String chanelName) {
        POForYouTube
                .setChannelName(chanelName)
                .openChannel();
        }

    @ValueSource(strings = {"���������", "������", "������������"})
    @ParameterizedTest(name = "��������� ������������ �������� ����� � ������ \"{0}\"� ������� � � �������� ")
    void checkVideoName(String guestName) {
        POForYouTube
                .setChannelName("���� ����")
                .openChannel()
                .openTabVideo();
        String videoNameOnTab = POForYouTube.openVideoByGuest(guestName);
        String videoNameOnCard = POForYouTube.getVideoNameOnCard();

        Assertions.assertEquals(videoNameOnTab, videoNameOnCard, "�������� ����� �� ������������� ���� �����");
    }

    @ValueSource(strings = {"11", "23", "27"})
    @ParameterizedTest(name = "��������� ������� ������ � ���������� � \"{0}\" ������")
    void findACommunityPost(String communityPost) {
        POForYouTube
                .setChannelName("���� ����")
                .openChannel()
                .openTabCommunity()
                .findPostByLikes(communityPost);
    }
}