import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        double a = scanner.nextInt();
        double b = scanner.nextInt();
        double c = scanner.nextInt();

        double p = (a + b + c) / 2;

        double inside = p * (p - a) * (p - b) * (p - c);
        double result = Math.sqrt(inside);

        System.out.println(result);
    }
}