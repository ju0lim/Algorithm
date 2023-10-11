import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.StringTokenizer;

/*

- 문제

https://www.acmicpc.net/problem/2512

- 접근법

1. 예산을 오름차순으로 정렬한다.
2) 모든 예산의 합이 전체 국가 예산보다 작으면 최대값을 출력하고 종료한다.
3) 그렇지 않을 경우 아래를 반복한다.
    3-1) 시작값과 마지막값의 중간값을 계산한다.
    3-2) 각 요소를 순회한다
        3-2-1) 예산이 중간값보다 크면 중간값을 더한다.
        3-2-2) 예산이 중간값보다 작으면 그 예산을 더한다.
    3-3)
        3-3-1) 모두 더한 값이 전체 국가 예산보다 작으면 시작값을 중간값 + 1로 설정한다. (상한값 업데이트)
        3-3-2) 모두 더한 값이 전체 국가 예산보다 크면 마지막값을 중간값 - 1로 설정한다.
*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int[] budgets = new int[n];
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int sum = 0;
        for (int i = 0; i < n; i++)
        {
            budgets[i] = Integer.parseInt(st.nextToken());
            sum += budgets[i];
        }

        int totalBudget = Integer.parseInt(reader.readLine());
        Arrays.sort(budgets);

        if (sum <= totalBudget)
        {
            System.out.println(budgets[n-1]);
            return;
        }

        int start = 0;
        int end = budgets[n-1];
        int upper = 0;

        while (start <= end)
        {
            int mid = (start + end) / 2;
            int currentSum = 0;

            for (int budget : budgets)
            {
                if (mid <= budget)
                {
                    currentSum += mid;
                }
                else
                {
                    currentSum += budget;
                }
            }

            if (currentSum <= totalBudget)
            {
                start = mid + 1;
                upper = Math.max(upper, mid);
            }
            else
            {
                end = mid - 1;
            }
        }

        System.out.println(upper);
    }
}