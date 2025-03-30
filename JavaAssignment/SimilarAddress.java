import java.util.*;

public class SimilarAddress {
    public static void main(String[] args) {
        // List of stored addresses
        List<String> addresses = Arrays.asList(
                "123 Main Street, New York, NY 10001",
                "456 Elm Avenue, Los Angeles, CA 90012",
                "123 Main St, New York, NY 10001",
                "789 Pine Road, Chicago, IL 60605",
                "124 Main Street, New York, NY 10001"
        );

        Scanner scanner = new Scanner(System.in);
        System.out.println("📍 Enter a new address to check for duplicates:");
        String newAddress = scanner.nextLine();

        System.out.println("\n🔍 Searching for similar addresses...\n");

        boolean foundSimilar = false;
        for (String storedAddress : addresses) {
            if (isSimilar(newAddress, storedAddress)) {
                System.out.println("✅ Similar Address Found: " + storedAddress);
                foundSimilar = true;
            }
        }

        if (!foundSimilar) {
            System.out.println("❌ No similar addresses found.");
        }

        scanner.close();
    }

    // Function to check similarity
    public static boolean isSimilar(String addr1, String addr2) {
        addr1 = addr1.toLowerCase().trim();
        addr2 = addr2.toLowerCase().trim();

        // Check if one contains the other
        if (addr1.contains(addr2) || addr2.contains(addr1)) {
            return true;
        }

        // Check if Levenshtein Distance is within 3 changes (small typo differences)
        return getLevenshteinDistance(addr1, addr2) <= 3;
    }

    // Levenshtein Distance Algorithm - Checks for small typo differences
    public static int getLevenshteinDistance(String s1, String s2) {
        int len1 = s1.length(), len2 = s2.length();
        int[][] dp = new int[len1 + 1][len2 + 1];

        for (int i = 0; i <= len1; i++) {
            for (int j = 0; j <= len2; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    int cost = (s1.charAt(i - 1) == s2.charAt(j - 1)) ? 0 : 1;
                    dp[i][j] = Math.min(dp[i - 1][j] + 1, Math.min(dp[i][j - 1] + 1, dp[i - 1][j - 1] + cost));
                }
            }
        }
        return dp[len1][len2];
    }
}
