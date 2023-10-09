import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*

- 문제

https://www.acmicpc.net/problem/1932

- 접근법

1. 두번째 줄부터 각 줄의 첫번째 요소는 이전 줄의 첫번째 값과 자신을 더한다.
2. 마지막 요소는 이전 줄의 마지막 값과 자신을 더한다.
2. 첫번째와 마지막을 제외한 나머지 요소는 이전 줄에서 내려올 수 있는 두 가지 경우 중 최대값을 선택하여 자신과 더한다.
4. 맨 아래층에서 최대값을 찾아준다.

*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[][] triangle = new int[n][];
        int[][] max = new int[n][];
        StringTokenizer st;

        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(reader.readLine());
            triangle[i] = new int[i+1];
            max[i] = new int[i+1];

            for (int j = 0; j < i+1; j++)
            {
                triangle[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        max[0][0] = triangle[0][0];

        for (int i = 1; i < n; i++)
        {
            for (int j = 0; j < i+1; j++)
            {
                int value = 0;
                if (j == 0)
                {
                    value = max[i-1][j] + triangle[i][j];
                }
                else if (j == i)
                {
                    value = max[i-1][j-1] + triangle[i][j];
                }
                else
                {
                    value = Math.max(max[i-1][j-1], max[i-1][j]) + triangle[i][j];
                }

                max[i][j] = value;
            }
        }

        int answer = 0;
        for (int i = 0; i < n; i++)
        {
            answer = Math.max(max[n-1][i], answer);
        }

        System.out.println(answer);
    }
}