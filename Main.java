import java.io.*;
import java.util.Arrays;

public class Main {
    private static final int MEMBERS_NUMBER = 10;

    public static void main(String[] args) throws IOException {
        int[][] teams = {
                { 45, 31, 24, 22, 20, 17, 14, 13, 12, 10 },
                { 31, 18, 15, 12, 10, 8, 6, 4, 2, 1 },
                { 51, 30, 10, 9, 8, 7, 6, 5, 2, 1 }
        };

        int[] nationalTeam = mergeAll(teams);
        System.out.println(Arrays.toString(nationalTeam)); // [51, 45, 31, 31, 30, 24, 22, 20, 18, 17]
    }

    /** Метод для слияния всех команд в одну национальную */
    public static int[] mergeAll(int[][] teams) {
        int[] res = teams[0];

        for (int i = 1; i < teams.length; i++) {
            res = merge(res, teams[i]);
        }

        return res;
    }

    /** Метод для слияния двух команд в одну */
    public static int[] merge(int[] teamA, int[] teamB) {
        int iA = 0;
        int iB = 0;
        int iRes = 0;
        int[] res = new int[MEMBERS_NUMBER];

        // Защита от бесконечного цикла.
        if (teamA.length + teamB.length < MEMBERS_NUMBER) {
            throw new IllegalArgumentException("not enough members in merged teams");
        }

        while (iRes < MEMBERS_NUMBER) {
            int toAdd;

            if (iA < teamA.length && iB < teamB.length) {
                if (teamA[iA] >= teamB[iB]) {
                    toAdd = teamA[iA];

                    iA++;
                } else {
                    toAdd = teamB[iB];

                    iB++;
                }
            } else if (iA < teamA.length) {
                toAdd = teamA[iA];

                iA++;
            } else {
                toAdd = teamB[iB];

                iB++;
            }

            res[iRes] = toAdd;

            iRes++;
        }

        return res;
    }
}