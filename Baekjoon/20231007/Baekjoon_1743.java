import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.StringTokenizer;

/*

- 문제

코레스코 콘도미니엄 8층은 학생들이 3끼의 식사를 해결하는 공간이다.
그러나 몇몇 비양심적인 학생들의 만행으로 음식물이 통로 중간 중간에 떨어져 있다.
이러한 음식물들은 근처에 있는 것끼리 뭉치게 돼서 큰 음식물 쓰레기가 된다.

이 문제를 출제한 선생님은 개인적으로 이러한 음식물을 실내화에 묻히는 것을 정말 진정으로 싫어한다.
참고로 우리가 구해야 할 답은 이 문제를 낸 조교를 맞추는 것이 아니다.

통로에 떨어진 음식물을 피해가기란 쉬운 일이 아니다.
따라서 선생님은 떨어진 음식물 중에 제일 큰 음식물만은 피해 가려고 한다.

선생님을 도와 제일 큰 음식물의 크기를 구해서 “10ra"를 외치지 않게 도와주자.

- 입력

첫째 줄에 통로의 세로 길이 N(1 ≤ N ≤ 100)과 가로 길이 M(1 ≤ M ≤ 100) 그리고 음식물 쓰레기의 개수 K(1 ≤ K ≤ N×M)이 주어진다.
그리고 다음 K개의 줄에 음식물이 떨어진 좌표 (r, c)가 주어진다.
좌표 (r, c)의 r은 위에서부터, c는 왼쪽에서부터가 기준이다. 입력으로 주어지는 좌표는 중복되지 않는다.

- 출력

첫째 줄에 음식물 중 가장 큰 음식물의 크기를 출력하라.

*/

public class Main
{
    private static int n;
    private static int m;
    private static int foodSize;
    private static int[] dx = {0, -1, 0, 1};
    private static int[] dy = {-1, 0, 1, 0};
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] area = new int[n][m];

        ArrayList<int[]> foodPos = new ArrayList<>(k);
        for (int i = 0; i < k; i++)
        {
            st = new StringTokenizer(reader.readLine());
            int row = Integer.parseInt(st.nextToken()) - 1;
            int col = Integer.parseInt(st.nextToken()) - 1;
            area[row][col] = 1;
            foodPos.add(new int[]{row, col});
        }

        boolean[][] visit = new boolean[n][m];
        int max = 0;
        for (int[] current : foodPos)
        {
            if (!visit[current[0]][current[1]])
            {
                foodSize = 0;
                searchRecursive(area, visit, current);
                max = Math.max(foodSize, max);
            }
        }

        System.out.println(max);
    }

    private static void searchRecursive(int[][] area, boolean[][] visit, int[] pos)
    {
        visit[pos[0]][pos[1]] = true;
        foodSize++;

        for (int i = 0; i < 4; i++)
        {
            int nx = pos[0] + dx[i];
            int ny = pos[1] + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
            {
                continue;
            }

            if (area[nx][ny] == 1 && !visit[nx][ny])
            {
                searchRecursive(area, visit, new int[]{nx, ny});
            }
        }
    }
}