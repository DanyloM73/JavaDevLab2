import java.util.ArrayList;
import java.util.List;

public class StringReplacer {
    private static int[][] findWordIndicesByLength(StringBuilder input, int wordLength) {
        List<int[]> indicesList = new ArrayList<>();
        int start = -1;

        for (int i = 0; i <= input.length(); i++) {
            if (i == input.length() || !Character.isLetterOrDigit(input.charAt(i))) {
                if (start != -1 && (i - start) == wordLength) {
                    indicesList.add(new int[]{start, i});
                }
                start = -1;
            } else if (start == -1) {
                start = i;
            }
        }

        return indicesList.toArray(new int[0][]);
    }

    public static StringBuilder replaceWords(StringBuilder input, int wordLength, String replacement) {
        int[][] indices = findWordIndicesByLength(input, wordLength);

        StringBuilder result = new StringBuilder(input);

        for (int i = indices.length - 1; i >= 0; i--) {
            int start = indices[i][0];
            int end = indices[i][1];
            result.replace(start, end, replacement);
        }

        return result;
    }
}
