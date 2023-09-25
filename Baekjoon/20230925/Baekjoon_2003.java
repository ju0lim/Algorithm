import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
N개의 수로 된 수열 A[1], A[2], …, A[N] 이 있다.
이 수열의 i번째 수부터 j번째 수까지의 합 A[i] + A[i+1] + … + A[j-1] + A[j]가 M이 되는 경우의 수를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N(1 ≤ N ≤ 10,000), M(1 ≤ M ≤ 300,000,000)이 주어진다.
다음 줄에는 A[1], A[2], …, A[N]이 공백으로 분리되어 주어진다. 각각의 A[x]는 30,000을 넘지 않는 자연수이다.

출력
첫째 줄에 경우의 수를 출력한다.
*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        input = reader.readLine().split(" ");
        int[] nums = new int[n];

        for (int i = 0; i < n; i++)
        {
            nums[i] = Integer.parseInt(input[i]);
        }

        int answer = 0;
        int start = 0;
        int end = 0;
        int sum = 0;

        while (true)
        {
            if (sum >= m)
            {
                sum -= nums[start++];
            }
            else if (end > n - 1)
            {
                break;
            }
            else
            {
                sum += nums[end++];
            }

            if (sum == m)
            {
                answer++;
            }
        }

        System.out.println(answer);
    }
}