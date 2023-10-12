import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

/*

- 문제

https://www.acmicpc.net/problem/1600

- 접근법

1. 주어진 k값(말 움직임을 사용가능한 수)에 따라 방문했는지 아닌지를 판별하는 3차원 boolean 배열을 만든다.
2. [0, 0] 부터 시작하여 다음과 같이 모든 경우의 수에 따라 이동한다.
    2-1) 1칸씩 움직이는 4가지를 이동한다.
    2-2) 만약 말 움직임을 사용할 수 있다면 8가지 모두 확인하여 이동한다.
    2-3) 모두 방문처리 해준다.
3. 가장 먼저 오른쪽 밑에 도달하는 경우가 최소값이다.
*/

public class Main
{
    static class Monkey
    {
        public int moveCount;
        public int x;
        public int y;
        public int horseMoveCount;
        public Monkey(int moveCount, int x, int y, int horseMoveCount)
        {
            this.moveCount = moveCount;
            this.x = x;
            this.y = y;
            this.horseMoveCount = horseMoveCount;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int k = Integer.parseInt(reader.readLine());
        StringTokenizer st = new StringTokenizer(reader.readLine());
        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());
        int[][] map = new int[h][w];

        for (int i = 0; i < h; i++)
        {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < w; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[] dx = {0, -1, 0, 1};
        int[] dy = {-1, 0, 1, 0};
        int[] horseX = {-2, -1, 1, 2, -2, -1, 1, 2};
        int[] horseY = {-1, -2, 2, 1, 1, 2, -2, -1};

        Queue<Monkey> queue = new LinkedList<>();
        boolean[][][] visit = new boolean[h][w][k+1];
        queue.offer(new Monkey(0, 0, 0, k));
        visit[0][0][0] = true;
        Monkey answer = null;

        while (!queue.isEmpty())
        {
            Monkey current = queue.poll();

            if (current.x == h-1 && current.y == w-1)
            {
                answer = current;
                break;
            }

            for (int i = 0; i < 4; i++)
            {
                int nx = current.x + dx[i];
                int ny = current.y + dy[i];

                if (nx < 0 || nx >= h || ny < 0 || ny >= w)
                {
                    continue;
                }

                if (!visit[nx][ny][k-current.horseMoveCount] && map[nx][ny] == 0)
                {
                    queue.offer(new Monkey(current.moveCount + 1, nx, ny, current.horseMoveCount));
                    visit[nx][ny][k-current.horseMoveCount] = true;
                }
            }

            if (current.horseMoveCount > 0)
            {
                for (int i = 0; i < 8; i++)
                {
                    int hnx = current.x + horseX[i];
                    int hny = current.y + horseY[i];

                    if (hnx < 0 || hnx >= h || hny < 0 || hny >= w)
                    {
                        continue;
                    }

                    if (!visit[hnx][hny][k-current.horseMoveCount+1] && map[hnx][hny] == 0)
                    {
                        queue.offer(new Monkey(current.moveCount + 1, hnx, hny, current.horseMoveCount - 1));
                        visit[hnx][hny][k-current.horseMoveCount + 1] = true;
                    }
                }
            }
        }

        if (answer == null)
        {
            System.out.println(-1);
        }
        else
        {
            System.out.println(answer.moveCount);
        }
    }
}