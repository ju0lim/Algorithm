import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

/*

- 문제

https://school.programmers.co.kr/learn/courses/30/lessons/176962

- 접근법

1. 주어진 plans 배열의 두 번째 요소인 시간 순으로 정렬한다.
2. 과제를 진행할 수 있는 시간을 계산한다.
3. 다음을 수행한다.
    3-1) 만약, 시간 안에 현재 과제를 진행할 수 있으면 answer에 넣는다.
        3-1-1) 과제를 모두 진행하고 list의 마지막 요소부터 진행한다.
    3-2) 시간 안에 진행할 수 없다면 list에 과제 이름과 남은 시간을 추가한다.
4. 마지막 과제까지 확인했으면 list의 마지막 요소부터 순차적으로 과제를 완료한다.

*/

public class Main
{
    public static void main(String[] args)
    {
        String[][] plans = {{"science", "12:40", "50"}, {"music", "12:20", "40"}, {"history", "14:00", "30"}, {"computer", "12:30", "100"}};
        solution(plans);
    }

    public static String[] solution(String[][] plans) {
        String[] answer = new String[plans.length];

        Arrays.sort(plans, new Comparator<String[]>(){
            @Override
            public int compare(String[] o1, String[] o2)
            {
                return o1[1].compareTo(o2[1]);
            }
        });

        ArrayList<String[]> list = new ArrayList<>(plans.length);
        int answerIndex = 0;

        for (int i = 0; i < plans.length; i++)
        {
            int remainTime = 0;

            if (i != plans.length -1)
            {
                String[] temp = plans[i][1].split(":");
                int current = 60 * Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]);
                temp = plans[i+1][1].split(":");
                int next = 60 * Integer.parseInt(temp[0]) + Integer.parseInt(temp[1]);
                remainTime = next-current;
            }

            int needTime = Integer.parseInt(plans[i][2]);

            if (needTime <= remainTime)
            {
                answer[answerIndex++] = plans[i][0];
                remainTime -= needTime;

                while (!list.isEmpty() && remainTime > 0)
                {
                    String[] plan = list.get(list.size()-1);
                    int befNeedTime = Integer.parseInt(plan[1]);
                    if (befNeedTime <= remainTime)
                    {
                        answer[answerIndex++] = plan[0];
                        list.remove(list.size()-1);
                        remainTime -= befNeedTime;
                    }
                    else
                    {
                        list.set(list.size()-1, new String[]{plan[0], String.valueOf(befNeedTime - remainTime)});
                        remainTime = 0;
                    }
                }
            }
            else
            {
                list.add(new String[]{plans[i][0], String.valueOf(needTime - remainTime)});
            }
        }

        for (int i = list.size()-1; i >= 0; i--)
        {
            answer[answerIndex++] = list.get(i)[0];
        }

        return answer;
    }
}