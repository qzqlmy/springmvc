public class Test {
    public static void main(String[] args) {
        double balance_once=3.856;
        String balance = String.valueOf(balance_once);
        String str[] = balance.split("\\.");
        System.out.println(str[0]);
        System.out.println(str[1]);
        System.out.println(str[1].substring(0,2));
    }
}
