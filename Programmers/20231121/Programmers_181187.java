/*

- 문제

https://school.programmers.co.kr/learn/courses/30/lessons/181187

- 접근법

1. 1사분면의 정수 쌍 개수를 구한다.
2. 원의 방정식 (x^2 + y^2 = r^2)을 이용한다
    2-1) x = 0 ~ x = r2까지 작은 원에 해당하는 y값을 구한다.
    2-2) x = 0 ~ x = r2까지 큰 원에 해당하는 y값을 구한다.
    2-3) 큰 원의 y값은 내림(해당 y값 보다 작거나 같은 정수여야 하므로) 작은 원의 올림(해당 y값 보다 크거나 같은 정수여야 하므로)한다.
    2-4) 만약, 작은 원의 반지름보다 x가 크면 작은 원의 y값을 0으로 대입한다.
3. 1사분면의 정수 쌍 개수를 모두 구하면 * 4를 한다.
4. x, y축 위에 점들은 두 번씩 반복되므로 빼준다.

*/

public class Main
{
    public static void main(String[] args)
    {
        solution(2, 3);
   }

    public static long solution(int r1, int r2) {
        long pointCount = 0;
        long r1Square = (long)r1 * r1;
        long r2Square = (long)r2 * r2;

        for (int x = 0; x <= r2; x++)
        {
            long xSquare = (long)x * x;
            double smallY = Math.sqrt(r1Square - xSquare);
            double largeY = Math.sqrt(r2Square - xSquare);

            if (r1Square - xSquare < 0)
            {
                smallY = 0;
            }

            pointCount += Math.floor(largeY) - Math.ceil(smallY) + 1;
        }

        long lineCount = (r2 - r1 + 1) * 4;

        return pointCount * 4 - lineCount;
    }
}