import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Queue;
import java.util.LinkedList;

/*

- 문제

https://www.acmicpc.net/problem/4179

- 접근법

1. 지훈이와 불의 현재 위치를 나타내는 Queue를 각각 만든다.
2. 매 분 (가장 초기의 while문) 당 지훈이와 불의 Queue Size만큼 각각 이동
3. 다음을 수행
    3-1) 가장자리에 도달할 수 있으면 시간을 출력하고 early exit
    3-2) 가강자리에 도달하지 못하면 IMPOSSIBLE 출력
*/

public class Main
{
    static class Jihoon
    {
        public int x;
        public int y;
        public int time;
        public Jihoon(int x, int y, int time)
        {
            this.x = x;
            this.y = y;
            this.time = time;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int r = Integer.parseInt(input[0]);
        int c = Integer.parseInt(input[1]);
        char[][] map = new char[r][c];
        boolean[][][] visit = new boolean[r][c][2];
        Queue<Jihoon> jihoonQ = new LinkedList<>();
        Queue<int[]> fireQ = new LinkedList<>();

        for (int i = 0; i < r; i++)
        {
            String row = reader.readLine();
            for (int j = 0; j < row.length(); j++)
            {
                map[i][j] = row.charAt(j);

                if (map[i][j] == 'J')
                {
                    jihoonQ.offer(new Jihoon(i, j, 0));
                    visit[i][j][0] = true;
                }

                if (map[i][j] == 'F')
                {
                    fireQ.offer(new int[]{i, j});
                    visit[i][j][1] = true;
                }
            }
        }

        int[] dx = {-1, 0, 1, 0};
        int[] dy = {0, -1, 0, 1};

        while (!jihoonQ.isEmpty())
        {
            int fireCount = fireQ.size();
            while (fireCount-- > 0)
            {
                int[] f = fireQ.poll();

                for (int i = 0; i < 4; i++)
                {
                    int nx = f[0] + dx[i];
                    int ny = f[1] + dy[i];

                    if (nx < 0 || nx >= r || ny < 0 || ny >= c)
                    {
                        continue;
                    }

                    if (map[nx][ny] != '#' && !visit[nx][ny][1])
                    {
                        fireQ.offer(new int[]{nx, ny});
                        map[nx][ny] = 'F';
                        visit[nx][ny][1] = true;
                    }
                }
            }

            int jhCount = jihoonQ.size();
            while (jhCount-- > 0)
            {
                Jihoon current = jihoonQ.poll();
                for (int i = 0; i < 4; i++)
                {
                    int nx = current.x + dx[i];
                    int ny = current.y + dy[i];

                    if (nx < 0 || nx >= r || ny < 0 || ny >= c)
                    {
                        System.out.println(current.time + 1);
                        return;
                    }

                    if (map[nx][ny] == '.' && !visit[nx][ny][0])
                    {
                        jihoonQ.offer(new Jihoon(nx, ny, current.time+1));
                        map[nx][ny] = 'J';
                        visit[nx][ny][0] = true;
                    }
                }
            }
        }

        System.out.println("IMPOSSIBLE");
    }
}