import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*

수열 A가 주어졌을 때, 가장 긴 증가하는 부분 수열을 구하는 프로그램을 작성하시오.

예를 들어, 수열 A = {10, 20, 10, 30, 20, 50} 인 경우에 가장 긴 증가하는 부분 수열은 A = {10, 20, 30, 50} 이고, 길이는 4이다.

입력

첫째 줄에 수열 A의 크기 N (1 ≤ N ≤ 1,000)이 주어진다.
둘째 줄에는 수열 A를 이루고 있는 Ai가 주어진다. (1 ≤ Ai ≤ 1,000)

출력

첫째 줄에 수열 A의 가장 긴 증가하는 부분 수열의 길이를 출력한다.

*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int count = Integer.parseInt(reader.readLine());
        int[] nums = new int[count];
        StringTokenizer st = new StringTokenizer(reader.readLine());

        for (int i = 0; i < count; i++)
        {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        int[] counts = new int[count];

        for (int i = 0; i < count; i++)
        {
            counts[i] = 1;
            for (int j = 0; j < count; j++)
            {
                if (nums[j] < nums[i])
                {
                    counts[i] = Math.max(counts[i], counts[j] + 1);
                }
            }
        }

        int max = 0;

        for (int i = 0; i < count; i++)
        {
            max = Math.max(counts[i], max);
        }

        System.out.println(max);
    }
}