package CC6;

import java.util.ArrayList;

public class Subscriber implements Observer {

    private ArrayList<post> post_content;
    private ArrayList<Integer> interest_post_index;

    private static int observerIDCounter =0;

    private int ObserverID;

    private SocialMedia socialMedia;

    public Subscriber(SocialMedia SocialMedia) {
        post_content =new ArrayList<>();
        interest_post_index =new ArrayList<>();

        this.socialMedia =SocialMedia;

        this.ObserverID =++observerIDCounter;

        SocialMedia.register(this);
        System.out.println("Observer " +ObserverID +" is registered.");
    }

    public void unSubscrite() {
        socialMedia.unregister(this);
    }

    public void makNewPost(String content) {
        socialMedia.makePost(content, ObserverID);
        interest_post_index.add(socialMedia.getNum());
        System.out.println(content);
    }

    public void subscribePost(int post_index) {
        if (post_index >socialMedia.getNum() ||post_index <0) {
            System.out.println("This post is not available.");
        } else if (interest_post_index.contains(post_index)) {
            System.out.println("You have already subscribed this post.");
        } else {
            interest_post_index.add(post_index);
        }
    }

    public void makeComments(int post_index, String content) {
        if (post_index >socialMedia.getNum() ||post_index <0) {
            System.out.println("This post is not available.");
        } else {
            socialMedia.commentPost(content, post_index);
        }
    }

    public void leavePost(int post_index) {
        if (post_index >socialMedia.getNum() ||post_index <0) {
            System.out.println("This post is not available.");
        } else {
            interest_post_index.remove(post_index);
        }
    }

    public void editPost(int post_index, String content) {
        socialMedia.editPost(content, ObserverID, post_index);
    }

    @Override
    public void update(String alert) {
        System.out.println("Observer " +ObserverID +" received: " +alert);
    }

    @Override
    public ArrayList<Integer> getInterest_post_index() {
        return interest_post_index;
    }

    public void printSubscriberInfo(){
        System.out.println();
        System.out.println("#############Info of Subscriber " + ObserverID  + "#############");

        if (interest_post_index.size() > 0) {
            System.out.println("Current interest:" + interest_post_index.toString());
        }

        for (post post: post_content){
            System.out.println("Observer " + ObserverID + ":");
            System.out.println("Post Content: " + post.getContent());
            if (post.getComments().size() > 0){
                for (int i = 0; i < post.getComments().size(); i++) {
                    System.out.println("	Comment " + i + ": " + post.getComments().get(i));
                }
            }
        }

        System.out.println("#############End of Info#############");
        System.out.println();
    }
}