import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*

- 문제

https://www.acmicpc.net/problem/1912

- 접근법

1. 입력받은 숫자를 가진 배열 num, 메모이제이션을 활용한 합을 나타내는 배열 sum
2. 다음과 같이 sum 배열에 메모이제이션 실행
    2-1) sum 배열의 이전 요소가 양수 -> 더하기
    2-2) sum 배열의 이전 요소가 음수 -> 자기 자신
    2-3) max 변수에 최대값을 저장
3. 최대값 출력
*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int[] num = new int[n];
        int[] sum = new int[n];

        for (int i = 0; i < n; i++)
        {
            num[i] = Integer.parseInt(st.nextToken());
        }

        sum[0] = num[0];
        int max = sum[0];
        for (int i = 1; i < n; i++)
        {
            sum[i] = Math.max(sum[i-1] + num[i], num[i]);
            max = Math.max(max, sum[i]);
        }

        System.out.println(max);
    }
}