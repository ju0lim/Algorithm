import java.util.Arrays;
import java.util.Comparator;

/*

- 문제

https://school.programmers.co.kr/learn/courses/30/lessons/172927

- 접근법

1. 장비의 수와 (광물의 수 / 5) + 1의 최소값을 length로 설정한다.
    1-1) 최대로 캘 수 있는 수를 정하기 위함
2. 다이아 곡괭이, 철 곡괭이, 돌 곡괭이로 5개의 광물을 캤을 때의 비용을 costs배열에 저장한다.
3. costs 배열을 다음과 같은 우선순위를 기준으로 정렬한다.
    3-1) 돌 곡괭이로 캤을 때의 값을 기준으로 내림차순
    3-2) 쇠 곡괭이로 컜을 때의 값을 기준으로 내림차순
    3-3) 다이아 곡괭이로 컜을 때의 값을 기준으로 내림차순
4. 주어진 곡괭이의 수를 고려하여 비싼 비용이 드는 순서대로 다이아 -> 쇠 -> 돌 곡괭이로 캐야 최소의 비용

*/

public class Main
{
    public static void main(String[] args)
    {
        int[] picks = {1, 3, 2};
        String[] minerals = {"diamond", "diamond", "diamond", "iron", "iron", "diamond", "iron", "stone"};
        System.out.println(solution(picks, minerals));
    }

    public static int solution(int[] picks, String[] minerals) {

        int equipCount = picks[0] + picks[1] + picks[2];
        int length = Math.min(equipCount, minerals.length/5 + 1);
        int[][] costs = new int[length][3];

        for (int i = 0; i < length; i++)
        {
            int dia = 0;
            int iron = 0;
            int stone = 0;

            for (int j = 5*i; j < 5*i+5; j++)
            {
                if (j >= minerals.length)
                {
                    break;
                }

                switch (minerals[j])
                {
                    case "diamond":
                        dia += 1;
                        iron += 5;
                        stone += 25;
                        break;

                    case "iron":
                        dia += 1;
                        iron += 1;
                        stone += 5;
                        break;

                    case "stone":
                        dia += 1;
                        iron += 1;
                        stone += 1;
                        break;
                }
            }

            costs[i][0] = dia;
            costs[i][1] = iron;
            costs[i][2] = stone;
        }

        Arrays.sort(costs, new Comparator<int[]>(){
            @Override
            public int compare(int[] o1, int[] o2)
            {
                if (o1[2] == o2[2])
                {
                    if (o1[1] == o1[1])
                    {
                        return o2[0] - o1[0];
                    }

                    return o2[1] - o1[1];
                }

                return o2[2] - o1[2];
            }
        });

        int answer = 0;

        for (int i = 0; i < length; i++)
        {
            if (picks[0] > 0)
            {
                answer += costs[i][0];
                picks[0]--;
            }
            else if (picks[1] > 0)
            {
                answer += costs[i][1];
                picks[1]--;
            }
            else
            {
                answer += costs[i][2];
                picks[2]--;
            }
        }

        return answer;
    }
}