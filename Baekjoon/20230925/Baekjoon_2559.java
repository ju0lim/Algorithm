import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

/*
매일 아침 9시에 학교에서 측정한 온도가 어떤 정수의 수열로 주어졌을 때, 연속적인 며칠 동안의 온도의 합이 가장 큰 값을 알아보고자 한다.

3 -2 -4 -9 0 3 7 13 8 -3
모든 연속적인 이틀간의 온도의 합은 아래와 같다.
이때, 온도의 합이 가장 큰 값은 21이다.

입력
첫째 줄에는 두 개의 정수 N과 K가 한 개의 공백을 사이에 두고 순서대로 주어진다.
첫 번째 정수 N은 온도를 측정한 전체 날짜의 수이다. N은 2 이상 100,000 이하이다.
두 번째 정수 K는 합을 구하기 위한 연속적인 날짜의 수이다. K는 1과 N 사이의 정수이다.
둘째 줄에는 매일 측정한 온도를 나타내는 N개의 정수가 빈칸을 사이에 두고 주어진다. 이 수들은 모두 -100 이상 100 이하이다.

출력
첫째 줄에는 입력되는 온도의 수열에서 연속적인 K일의 온도의 합이 최대가 되는 값을 출력한다.
*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int k = Integer.parseInt(input[1]);
        input = reader.readLine().split(" ");
        int[] tems = new int[n];

        for (int i = 0; i < n; i++)
        {
            tems[i] = Integer.parseInt(input[i]);
        }

        int start = 0;
        int end = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        int count = 0;

        while (true)
        {
            if (count < k)
            {
                sum += tems[end++];
                count++;
            }
            else
            {
                max = Math.max(sum, max);
                sum += tems[end++];
                sum -= tems[start++];
            }

            if (end > n - 1)
            {
                max = Math.max(sum, max);
                break;
            }
        }

        System.out.println(max);
    }
}