/*

- 문제

https://school.programmers.co.kr/learn/courses/30/lessons/178870

- 접근법

1. 시작(start)과 끝(end)을 나타내는 인덱스 변수를 선언한다.
2. 다음을 반복한다.
    2-1) sum < k : sum이 k보다 작으면 하나의 원소[end]를 더한다.
    2-2) else : sum과 k가 같고, minLength보다 작으면 minLength를 초기화해주고 answer에 시작, 끝 인덱스를 저장
    2-3) sum < k으면서 end가 배열의 길이를 넘어서면 종료
*/

public class Main
{
    public static void main(String[] args)
    {
        solution(new int[]{1,2,3,4,5}, 7);
   }

    public static int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];

        int sum = 0;
        int start = 0;
        int end = 0;
        int minLength = Integer.MAX_VALUE;

        while (true)
        {
            if (sum < k)
            {
                if (end > sequence.length - 1)
                {
                    break;
                }

                sum += sequence[end++];
            }
            else
            {
                if (sum == k && minLength > end - start)
                {
                    minLength = end- start;
                    answer[0] = start;
                    answer[1] = end - 1;
                }

                sum -= sequence[start++];
            }
        }

        return answer;
    }
}