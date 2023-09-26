import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

/*
도현이는 우주의 신이다. 이제 도현이는 아무렇게나 널브러져 있는 n개의 별들을 이어서 별자리를 하나 만들 것이다. 별자리의 조건은 다음과 같다.

- 별자리를 이루는 선은 서로 다른 두 별을 일직선으로 이은 형태이다.
- 모든 별들은 별자리 위의 선을 통해 서로 직/간접적으로 이어져 있어야 한다.

별들이 2차원 평면 위에 놓여 있다. 선을 하나 이을 때마다 두 별 사이의 거리만큼의 비용이 든다고 할 때, 별자리를 만드는 최소 비용을 구하시오.

입력

첫째 줄에 별의 개수 n이 주어진다. (1 ≤ n ≤ 100)
둘째 줄부터 n개의 줄에 걸쳐 각 별의 x, y좌표가 실수 형태로 주어지며, 최대 소수점 둘째자리까지 주어진다. 좌표는 1000을 넘지 않는 양의 실수이다.

출력

첫째 줄에 정답을 출력한다. 절대/상대 오차는 10-2까지 허용한다.

*/

public class Main
{
    static class Star
    {
        public int index;
        public float x;
        public float y;
        public Star(int index, float x, float y)
        {
            this.index = index;
            this.x = x;
            this.y = y;
        }
    }
    static class Distance implements Comparable<Distance>
    {
        public Star start;
        public Star end;
        public double distance;
        public Distance(Star start, Star end)
        {
            this.start = start;
            this.end = end;
            this.distance = Math.sqrt(Math.pow(start.x - end.x, 2) + Math.pow(start.y - end.y, 2));
        }

        @Override
        public int compareTo(Distance o)
        {
            if (this.distance < o.distance)
            {
                return -1;
            }
            return 1;
        }
    }

    public static ArrayList<Star> list;
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        Star[] stars = new Star[n];
        int[] cycle = new int[n];
        list = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            String[] input = reader.readLine().split(" ");
            float x = Float.parseFloat(input[0]);
            float y = Float.parseFloat(input[1]);
            stars[i] = new Star(i, x, y);
            cycle[i] = i;
        }

        ArrayList<Distance> distances = new ArrayList<>();

        for (int i = 0; i < n; i++)
        {
            for (int j = i + 1; j < n; j++)
            {
                distances.add(new Distance(stars[i], stars[j]));
            }
        }
        Collections.sort(distances);

        double answer = 0;

        for (Distance distance : distances)
        {
            if (getRoot(cycle, distance.start.index) != getRoot(cycle, distance.end.index))
            {
                answer += distance.distance;
                union(cycle, distance.start.index, distance.end.index);
            }
        }

        System.out.println(answer);
    }

    public static int getRoot(int[] cycle, int x)
    {
        if (cycle[x] == x)
        {
            return x;
        }

        return getRoot(cycle, cycle[x]);
    }

    public static void union(int[] cycle, int x, int y)
    {
        x = getRoot(cycle, x);
        y = getRoot(cycle, y);

        if (x < y)
        {
            cycle[y] = x;
        }
        else
        {
            cycle[x] = y;
        }
    }
}