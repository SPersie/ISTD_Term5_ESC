public class test {
    public static void main(String[] args) {
        String s ="this is your father";
        byte[] bytes;
        bytes =s.getBytes();
        System.out.println(bytes[0]);
        System.out.println(~bytes[0] &0xFF);
        System.out.println(Integer.reverse(bytes[0] <<24) &0xff);
        System.out.println(2 ^(1 <<0));
    }
}
