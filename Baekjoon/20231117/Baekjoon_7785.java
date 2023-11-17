import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.TreeMap;

/*

- 문제

https://www.acmicpc.net/problem/7785

- 접근법

1. 이름이 나오는 횟수를 TreeMap에 저장
2. value가 홀수면 근무중이고 짝수면 퇴근
3. TreeMap 기준을 역순으로 하였기 때문에 홀수면 StringBuilder에 저장
*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        TreeMap<String, Integer> map = new TreeMap<>(Collections.reverseOrder());
        StringBuilder builder = new StringBuilder();

        for (int i = 0; i < n; i++)
        {
            String[] log = reader.readLine().split(" ");
            map.put(log[0], map.getOrDefault(log[0], 0) + 1);
        }

        for (String name : map.keySet())
        {
            if (map.get(name) % 2 == 1)
            {
                builder.append(name).append("\n");
            }
        }

        System.out.println(builder);
    }
}