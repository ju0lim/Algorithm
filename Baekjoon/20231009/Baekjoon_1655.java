import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Collections;
import java.util.PriorityQueue;

/*

- 문제

https://www.acmicpc.net/problem/1655

- 접근법

1. 큰 값이 우선 순위를 갖는 PriorityQueue와 작은 값이 우선 순위를 갖는 PriorityQueue 두 개를 선언한다.
2. 최대값 우선 큐의 사이즈 <= 최소값 우선 큐의 사이즈 : 최대값 우선순위 큐에 넣기
3. 그의 반대의 경우 : 최소값 우선순위 큐에 넣기
4. 두 개의 큐의 peek값을 비교하여 최대값 우선 순위 큐 peek값 > 최소값 우선 순위 큐 peek값일 경우 두 개를 스왑해준다.
5. 한 번의 숫자를 넣을때마다 두 개의 큐의 size를 비교하여 중간값을 찾아준다.

ex)
input
6 -> n
10
7
5
4
5
-1

최대값 우선 Queue : 최소값 우선 Queue
1) [ 10 ] : [  ] -> builder에 10 저장
2) [ 10 ] : [ 7 ] ->  최대값 우선 순위 큐 peek값 (10) > 최소값 우선 순위 큐 peek값(7) -> swap -> [ 7 ] : [ 10 ] -> builder에 7 저장
3) [ 5, 7 ] : [ 10 ] -> builder에 7 저장
4) [ 5, 7 ] : [ 4, 10 ] ->  최대값 우선 순위 큐 peek값(7) > 최소값 우선 순위 큐 peek값(4) -> swap -> [ 4, 5 ] : [ 7, 10 ] -> builder에 5 저장
5) [ 4, 5, 5 ] : [7, 10] -> builder에 5 저장
6) [ 4, 5, 5 ] : [-1, 7, 10] -> 최대값 우선 순위 큐 peek값(5) > 최소값 우선 순위 큐 peek값(-1) -> swap -> [ -1, 4, 5 ] : [ 5, 7, 10 ] -> builder에 5 저장

따라서 output은
10
7
7
5
5
5
*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();
        PriorityQueue<Integer> maxQueue = new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> minQueue = new PriorityQueue<>();

        for (int i = 0; i < n; i++)
        {
            int num = Integer.parseInt(reader.readLine());

            if (maxQueue.size() <= minQueue.size())
            {
                maxQueue.offer(num);
            }
            else
            {
                minQueue.offer(num);
            }

            if (!minQueue.isEmpty() && minQueue.peek() < maxQueue.peek())
            {
                maxQueue.offer(minQueue.poll());
                minQueue.offer(maxQueue.poll());
            }

            int value = maxQueue.size() < minQueue.size() ? minQueue.peek() : maxQueue.peek();
            builder.append(value).append("\n");
        }

        System.out.println(builder);
    }
}