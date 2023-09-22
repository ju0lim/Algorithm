import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        String[] dnas = new String[n];
        int[][] charcter = new int[m][26];
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++)
        {
            dnas[i] = reader.readLine();

            for (int j = 0; j < m; j++)
            {
                charcter[j][dnas[i].charAt(j)-'A']++;
            }
        }

        for (int i = 0; i < m; i++)
        {
            int max = charcter[i][0];
            char target = 'A';

            for (int j = 0; j < 26; j++)
            {
                if (max < charcter[i][j])
                {
                    max = charcter[i][j];
                    target = (char) ( j + 'A');
                }
            }
            builder.append(target);
        }

        int hammingDistance = 0;
        for (int i = 0; i < n; i++)
        {
            String dna = dnas[i];
            for (int j = 0; j < m; j++)
            {
                if (dna.charAt(j) != builder.charAt(j))
                {
                    hammingDistance++;
                }
            }
        }

        System.out.println(builder);
        System.out.println(hammingDistance);
    }
}