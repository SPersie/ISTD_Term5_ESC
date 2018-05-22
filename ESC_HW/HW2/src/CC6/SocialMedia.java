package CC6;

import java.util.ArrayList;

public class SocialMedia implements iSubject {

    private ArrayList<Observer> observers;
    private ArrayList<post> posts;

    public SocialMedia() {
        observers =new ArrayList<>();
        posts =new ArrayList<>();
    }

    @Override
    public void register(Observer o) {
        observers.add(o);
    }

    @Override
    public void unregister(Observer o) {
        int num =observers.indexOf(o);
        observers.remove(num);
        System.out.println("Observer is unregistered.");
    }

    @Override
    public void notifyObserver(ArrayList<Observer> list, String alert) {
        for (Observer o:list) {
            o.update(alert);
        }
    }


    public void makePost(String init_content, int id) {
        posts.add(new post(init_content, id));
    }

    public void editPost(String new_content, int id, int post_id) {
        posts.get(post_id).editPost(new_content, id);
        ArrayList<Observer> list =new ArrayList<>();
        for (Observer o: observers) {
            if (o.getInterest_post_index().contains(post_id)) {
                list.add(o);
            }
        }
        notifyObserver(list, "Your interested post is edited.");
    }

    public void commentPost(String comment, int post_id) {
        posts.get(post_id).commentPost(comment);
        ArrayList<Observer> list =new ArrayList<>();
        for (Observer o: observers) {
            if (o.getInterest_post_index().contains(post_id)) {
                list.add(o);
            }
        }
        notifyObserver(list, "Your interested post has a comment.");
    }

    public int getNum() {
        return posts.size();
    }

    public void printAll(){
        System.out.println();
        System.out.println("-----------------Post Board-----------------");
        System.out.println("Currently there are " + observers.size() + " Observers");
        for (post post: posts) {
            System.out.println("Post " + posts.indexOf(post) + " (" + "Posted by Observer " + post.init_id +"): " + post.getContent());
            for (int i = 0; i < post.getComments().size(); i++) {
                System.out.println("	Comment " + i + ": " + post.getComments().get(i));
            }
        }
        System.out.println("--------------------------------------------");
        System.out.println();
    }


}
