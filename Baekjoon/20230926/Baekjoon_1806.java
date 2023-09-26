import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
10,000 이하의 자연수로 이루어진 길이 N짜리 수열이 주어진다.
이 수열에서 연속된 수들의 부분합 중에 그 합이 S 이상이 되는 것 중, 가장 짧은 것의 길이를 구하는 프로그램을 작성하시오.

입력
첫째 줄에 N (10 ≤ N < 100,000)과 S (0 < S ≤ 100,000,000)가 주어진다.
둘째 줄에는 수열이 주어진다. 수열의 각 원소는 공백으로 구분되어져 있으며, 10,000이하의 자연수이다.

출력
첫째 줄에 구하고자 하는 최소의 길이를 출력한다.
만일 그러한 합을 만드는 것이 불가능하다면 0을 출력하면 된다.
*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int target = Integer.parseInt(input[1]);
        int[] nums = new int[n];
        input = reader.readLine().split(" ");

        for (int i = 0; i < n; i++)
        {
            nums[i] = Integer.parseInt(input[i]);
        }

        int start = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;
        int sum = 0;

        while (true)
        {
            if (sum < target)
            {
                if (end > n-1)
                {
                    break;
                }
                sum += nums[end++];
            }
            else
            {
                minLength = Math.min(minLength, end-start);
                sum -= nums[start++];
            }
        }

        if (minLength == Integer.MAX_VALUE)
        {
            minLength = 0;
        }
        System.out.println(minLength);
    }
}