package CC6;

import java.util.ArrayList;
import java.util.List;

public class TestCases {
    public static void main(String[] args) {
        SocialMedia socialMedia =new SocialMedia();
        Subscriber sub1 =new Subscriber(socialMedia);
        Subscriber sub2 =new Subscriber(socialMedia);
//        Subscriber sub3 =new Subscriber(socialMedia);

        sub1.makNewPost("Hi, this is my first post.");
        sub2.makeComments(0, "Haha.");
        socialMedia.printAll();

        sub2.subscribePost(0);
        sub1.editPost(0, "Hi, I made some changes.");
//        sub1.unSubscrite();

        socialMedia.printAll();

    }
}

class test {

}

