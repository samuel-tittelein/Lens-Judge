import java.util.Scanner;

public class TimeThroughTheGlass {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int h = sc.nextInt();
        int m = sc.nextInt();
        System.out.println((12 - h) % 12 + " " + (60 - m) % 60);
    }

}
