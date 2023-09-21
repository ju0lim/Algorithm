import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < t; i++)
        {
            int n = Integer.parseInt(reader.readLine());
            String[] input = reader.readLine().split(" ");
            int[] prices = new int[n];
            for (int j = 0; j < n; j++)
            {
                prices[j] = Integer.parseInt(input[j]);
            }

            long answer = 0;
            int max = prices[n-1];

            for (int j = n-1; j >= 0; j--)
            {
                if (prices[j] < max)
                {
                    answer += max - prices[j];
                }
                else
                {
                    max = prices[j];
                }
            }
            builder.append(answer).append("\n");
        }

        System.out.println(builder);
    }
}
