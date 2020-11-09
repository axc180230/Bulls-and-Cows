import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        int a = scanner.nextInt();
        int b = scanner.nextInt();
        int n = scanner.nextInt();
        int k = scanner.nextInt();

        int minSeedValue = 0;
        int minSeedRand = k;

        // go thorough the seed value
        for (int i = a; i <= b; i++) {
            int tempSeedRandomMax = 0;
            int tempSeedRandomCurrent = 0;
            Random random = new Random(i);

            // going through seed random generated values and grab max one
            for (int j = 0; j < n; j++) {
                tempSeedRandomCurrent  = random.nextInt(k);
                tempSeedRandomMax = Math.max(tempSeedRandomMax, tempSeedRandomCurrent);
            }

            // If max value of seed random values is less than current min
            // replace it and note what seed that was
            if (tempSeedRandomMax < minSeedRand) {
                minSeedRand = tempSeedRandomMax;
                minSeedValue = i;
            }
        }

        System.out.println(minSeedValue);
        System.out.println(minSeedRand);
    }
}