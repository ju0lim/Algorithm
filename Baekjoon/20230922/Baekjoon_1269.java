import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.HashSet;

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int aCount = Integer.parseInt(input[0]);
        int bCount = Integer.parseInt(input[1]);
        HashSet<Integer> set = new HashSet<>(aCount+bCount);

        input = reader.readLine().split(" ");
        for (int i = 0; i < aCount; i++)
        {
            set.add(Integer.parseInt(input[i]));
        }

        input = reader.readLine().split(" ");
        for (int i = 0; i < bCount; i++)
        {
            set.add(Integer.parseInt(input[i]));
        }

        System.out.println((set.size() - aCount) + (set.size() - bCount));
    }
}