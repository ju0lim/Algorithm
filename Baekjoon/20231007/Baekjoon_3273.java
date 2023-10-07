import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;
import java.util.Arrays;

/*

- 문제

n개의 서로 다른 양의 정수 a1, a2, ..., an으로 이루어진 수열이 있다. ai의 값은 1보다 크거나 같고, 1000000보다 작거나 같은 자연수이다.
자연수 x가 주어졌을 때, ai + aj = x (1 ≤ i < j ≤ n)을 만족하는 (ai, aj)쌍의 수를 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 수열의 크기 n이 주어진다.
다음 줄에는 수열에 포함되는 수가 주어진다.
셋째 줄에는 x가 주어진다. (1 ≤ n ≤ 100000, 1 ≤ x ≤ 2000000)

- 출력

문제의 조건을 만족하는 쌍의 개수를 출력한다.

*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        // 시간 복잡도, 공간 복잡도 모두 방법 1이 더 효율적

        System.out.println(getCount1());
        System.out.println(getCount2());
    }

    // 방법 1 : Array
    private static int getCount1() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] index = new int[1_000_001];
        int[] nums = new int[n];

        for (int i = 0; i < n; i++)
        {
            int num = Integer.parseInt(st.nextToken());
            index[num] = 1;
            nums[i] = num;
        }

        int target = Integer.parseInt(reader.readLine());
        int count = 0;

        for (int num : nums)
        {
            if (0 < target-num && target-num < 1_000_001 && num + num != target && index[target-num] == 1)
            {
                count++;
                index[target-num] = 0;
                index[num] = 0;
            }
        }

        return count;
    }

    // 방법 2 : Two Pointer
    private static int getCount2() throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] nums = new int[n];

        for (int i = 0; i < n; i++)
        {
            nums[i] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(nums);

        int target = Integer.parseInt(reader.readLine());
        int count = 0;
        int left = 0;
        int right = n-1;

        while (left < right)
        {
            int sum = nums[left] + nums[right];

            if (sum == target)
            {
                left++;
                right--;
                count++;
            }
            else if (sum < target)
            {
                left++;
            }
            else
            {
                right--;
            }
        }

        return count;
    }
}