package CC6;

import java.util.ArrayList;

public class post {

    private String content;
    private ArrayList<String> comments =new ArrayList<>();
    public int init_id;

    public post(String init_content, int init_id) {
        this.content =init_content;
        this.init_id =init_id;
    }

    public void editPost(String new_content, int id) {
        if (id ==init_id) {
            content =new_content;
        } else {
            System.out.println("You are unauthorized to edit the post.");
        }
    }

    public void commentPost(String comment) {
        comments.add(comment);
    }

    public String getContent() {
        if (content.equals("")) {
            return "No Content";
        } else {
            return content;
        }
    }

    public ArrayList<String> getComments() {
        return comments;
    }
}
