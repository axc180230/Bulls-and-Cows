import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextDouble();
        double b = scanner.nextDouble();
        double c = scanner.nextDouble();

        double discriminant = Math.sqrt(Math.pow(b, 2) - 4 * a * c);
        double ans1 = (-b + discriminant) / (2 * a);
        double ans2 = (-b - discriminant) / (2 * a);

        // printing answers in ascending order
        if (ans1 > ans2) {
            printFormattedAnswer(ans2);
            System.out.print(" ");
            printFormattedAnswer(ans1);
        } else {
            printFormattedAnswer(ans1);
            System.out.print(" ");
            printFormattedAnswer(ans2);
        }
    }

    static void printFormattedAnswer(double ans) {
        double temp = ans - (int) ans;

        if (temp == 0.0) {
            System.out.print((int) ans);
        } else {
            System.out.printf("%.6f", ans);
        }
    }
}