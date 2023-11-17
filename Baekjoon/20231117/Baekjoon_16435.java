import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.TreeMap;

/*

- 문제

https://www.acmicpc.net/problem/16435

- 접근법

1. 과일 높이를 오름차순 정렬
2. l보다 과일 높이가 작거나 같으면 1씩 증가
3. l 출력
*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int l = Integer.parseInt(input[1]);
        int[] fruits = new int[n];

        input = reader.readLine().split(" ");
        for (int i = 0; i < n; i++)
        {
            fruits[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(fruits);
        for (int height : fruits)
        {
            if (l >= height)
            {
                l++;
            }
        }

        System.out.println(l);
    }
}