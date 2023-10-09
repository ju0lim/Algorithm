import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

- 문제

https://www.acmicpc.net/problem/1092

- 접근법

1. 박스와 크레인을 내림차순으로 정렬한다.
2. 크레인의 최대값 < 박스의 최대값 일 경우 -1 반환
3. 다음을 반복한다.
    3-1) 무게한도가 높은 크레인부터 무거운 박스를 확인한다.
    3-2) 박스를 옮길 수 있으면 리스트에서 제거하고 다음 크레인으로 넘어간다.
    3-3) 박스를 옮길 수 없으면 다음 박스를 확인한다.
    3-4) 마지막 박스까지 확인했는데 없으면 옮기고 1분 추가한다.

*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        ArrayList<Integer> cranes = new ArrayList<>(n);

        for (int i = 0; i < n; i++)
        {
            cranes.add(Integer.parseInt(st.nextToken()));
        }

        int m = Integer.parseInt(reader.readLine());
        st = new StringTokenizer(reader.readLine());
        ArrayList<Integer> boxes = new ArrayList<>(m);

        for (int i = 0; i < m; i++)
        {
            boxes.add(Integer.parseInt(st.nextToken()));

        }

        Collections.sort(cranes, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());

        if (cranes.get(0) < boxes.get(0))
        {
            System.out.println(-1);
            return;
        }

        int minute = 0;
        while (!boxes.isEmpty())
        {
            int craneIndex = 0;
            int boxIndex = 0;

            while (craneIndex < n && boxIndex < boxes.size())
            {
                if (boxes.get(boxIndex) <= cranes.get(craneIndex))
                {
                    craneIndex++;
                    boxes.remove(boxIndex);
                }
                else
                {
                    boxIndex++;
                }
            }

            minute++;
        }

        System.out.println(minute);
    }
}