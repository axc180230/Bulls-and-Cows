import java.util.Scanner;

class Main {
    public static void main(String[] args) {
        // put your code here
        Scanner scanner = new Scanner(System.in);

        int ui = scanner.nextInt();
        int uj = scanner.nextInt();
        int vi = scanner.nextInt();
        int vj = scanner.nextInt();

        double uLength = getVectorLength(ui, uj);
        double vlength = getVectorLength(vi, vj);
        double uvDotProduct = getDotProduct(ui, uj, vi, vj);
        double cosineAngle = getCosineAngelFromVector(uvDotProduct, uLength, vlength);
        double angle = Math.acos(cosineAngle);
        int finalAngle = (int) Math.toDegrees(angle);

        System.out.println(finalAngle);
    }

    public static double getVectorLength(int i, int j) {
        return Math.sqrt(Math.pow(i, 2) + Math.pow(j, 2));
    }

    public static int getDotProduct(int ui, int uj, int vi, int vj) {
        return ui * vi + uj * vj;
    }

    public static double getCosineAngelFromVector(double dotProduct, double uLength, double vLength) {
        return dotProduct / (uLength * vLength);
    }


}