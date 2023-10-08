import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.*;

/*

- 문제

https://www.acmicpc.net/problem/17472

- 접근법

1. 각 섬 별로 map에 표기해준다.
2. 각 섬 별로 다른 섬으로 이동할 수 있는 모든 경우의 수를 찾는다.
3. 이동할 수 있는 다리의 개수를 오름차순으로 정렬한다.
4. 크루스칼 알고리즘을 활용하여 최소의 개수를 구한다.
*/

public class Main
{
    public static int n;
    public static int m;
    public static int[][] map;
    public static boolean[][] visit;
    public static ArrayList<Island> islandList;
    public static int[] dx = {0, -1, 0, 1};
    public static int[] dy = {-1, 0, 1, 0};

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(reader.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];

        for (int i = 0; i < n; i++)
        {
            st = new StringTokenizer(reader.readLine());
            for (int j = 0; j < m; j++)
            {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visit = new boolean[n][m];
        islandList = new ArrayList<>();
        int count = 0;

        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < m; j++)
            {
                if (!visit[i][j] && map[i][j] == 1)
                {
                    islandList.add(new Island(++count));
                    searchIslandRecur(i, j, islandList.size()-1);
                }
            }
        }

        ArrayList<Node> nodes = new ArrayList<>();

        for (int i = 0; i < islandList.size(); i++)
        {
            for (int j = i + 1; j < islandList.size(); j++)
            {
                if (i != j)
                {
                    int bridgeCount = getMinDistance(islandList.get(i), islandList.get(j));
                    if (bridgeCount != -1)
                    {
                        int depart = islandList.get(i).islandIndex;
                        int arrive = islandList.get(j).islandIndex;
                        nodes.add(new Node(depart, arrive, bridgeCount));
                    }
                }
            }
        }

        Collections.sort(nodes, new Comparator<Node>(){
            @Override
            public int compare(Node lhs, Node rhs)
            {
                return lhs.bridgeCount - rhs.bridgeCount;
            }
        });

        int[] parents = new int[islandList.size()+1];

        for (int i = 1; i < parents.length; i++)
        {
            parents[i] = i;
        }

        int bridgeCount = 0;
        int connectCount = 0;
        for (int i = 0; i < nodes.size(); i++)
        {
            int node1 = nodes.get(i).depart;
            int node2 = nodes.get(i).arrive;
            if (!equalParent(parents, node1, node2))
            {
                union(parents, nodes.get(i).depart, nodes.get(i).arrive);
                bridgeCount += nodes.get(i).bridgeCount;
                connectCount++;
            }
        }

        if (bridgeCount == 0 || islandList.size() -1 != connectCount)
        {
            bridgeCount = -1;
        }

        System.out.println(bridgeCount);
    }

    public static boolean equalParent(int[] parents, int node1, int node2)
    {
        if (findParent(parents, node1) == findParent(parents, node2))
        {
            return true;
        }
        return false;
    }

    public static int findParent(int[] parents, int x)
    {
        if (parents[x] == x)
        {
            return x;
        }

        return findParent(parents, parents[x]);
    }

    public static void union(int[] parents, int x, int y)
    {
        x = findParent(parents, x);
        y = findParent(parents, y);

        if (x < y)
        {
            parents[y] = x;
        }
        else
        {
            parents[x] = y;
        }
    }

    public static void searchIslandRecur(int x, int y, int islandIndex)
    {
        visit[x][y] = true;
        islandList.get(islandIndex).addPosition(new int[]{x, y});
        map[x][y] = islandList.get(islandIndex).islandIndex;

        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
            {
                continue;
            }

            if (!visit[nx][ny] && map[nx][ny] == 1)
            {
                searchIslandRecur(nx, ny, islandIndex);
            }
        }
    }

    public static int getMinDistance(Island depart, Island arrive)
    {
        Queue<Point> queue = new LinkedList<>();
        ArrayList<Integer> directions;

        for (int i = 0; i < depart.positions.size(); i++)
        {
            int x = depart.positions.get(i)[0];
            int y = depart.positions.get(i)[1];
            directions = getDirection(x, y);
            for (int j = 0; j < directions.size(); j++)
            {
                queue.offer(new Point(x, y, directions.get(j), 0));
            }
        }

        ArrayList<Integer> distances = new ArrayList<>();
        while (!queue.isEmpty())
        {
            Point current = queue.poll();

            int nx = current.x + dx[current.direction];
            int ny = current.y + dy[current.direction];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
            {
                continue;
            }

            if (map[nx][ny] == 0)
            {
                queue.offer(new Point(nx, ny, current.direction, current.moveDistance + 1));
            }
            else if (map[nx][ny] == arrive.islandIndex)
            {
                if (current.moveDistance > 1)
                {
                    distances.add(current.moveDistance);
                }
            }
        }

        if (distances.isEmpty())
        {
            return -1;
        }

        int min = Integer.MAX_VALUE;
        for (int i = 0; i < distances.size(); i++)
        {
            min = Math.min(min, distances.get(i));
        }

        return min;
    }

    public static ArrayList<Integer> getDirection(int x, int y)
    {
        ArrayList<Integer> directions = new ArrayList<>(4);
        for (int i = 0; i < 4; i++)
        {
            int nx = x + dx[i];
            int ny = y + dy[i];

            if (nx < 0 || nx >= n || ny < 0 || ny >= m)
            {
                continue;
            }

            if (map[nx][ny] == 0)
            {
                directions.add(i);
            }
        }

        return directions;
    }

    static class Island
    {
        public int islandIndex;
        public ArrayList<int[]> positions = new ArrayList<>();
        public Island(int islandIndex)
        {
            this.islandIndex = islandIndex;
        }
        public void addPosition(int[] position)
        {
            positions.add(position);
        }
    }

    static class Point
    {
        public int x;
        public int y;
        public int direction;
        public int moveDistance;
        public Point(int x, int y, int direction, int moveDistance)
        {
            this.x = x;
            this.y = y;
            this.direction = direction;
            this.moveDistance = moveDistance;
        }
    }

    static class Node
    {
        public int depart;
        public int arrive;
        public int bridgeCount;
        public Node(int depart, int arrive, int bridgeCount)
        {
            this.depart = depart;
            this.arrive = arrive;
            this.bridgeCount = bridgeCount;
        }
    }
}