import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;

public class Main
{
    static class Path implements Comparable<Path>
    {
        public int from;
        public int to;
        public int cost;
        public Path(int from, int to, int cost)
        {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }

        @Override
        public int compareTo(Path path)
        {
            return this.cost - path.cost;
        }
    }

    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int n = Integer.parseInt(input[0]);
        int m = Integer.parseInt(input[1]);
        ArrayList<Path> paths = new ArrayList<>(m);
        int[] root = new int[n+1];

        for (int i = 1; i <= n; i++)
        {
            root[i] = i;
        }

        for (int i = 0; i < m; i++)
        {
            input = reader.readLine().split(" ");
            paths.add(new Path(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2])));
        }

        Collections.sort(paths);

        int totalCost = 0;
        int max = 0;

        for (Path path : paths)
        {
            if (getRoot(root, path.from) != getRoot(root, path.to))
            {
                totalCost += path.cost;
                max = path.cost;
                union(root, path.from, path.to);
            }
        }

        System.out.println(totalCost - max);
    }

    public static int getRoot(int[] root, int house)
    {
        if (root[house] == house)
        {
            return house;
        }
        return getRoot(root, root[house]);
    }

    public static void union(int[] root, int house1, int house2)
    {
        house1 = getRoot(root, house1);
        house2 = getRoot(root, house2);

        if (house1 < house2)
        {
            root[house2] = house1;
        }
        else
        {
            root[house1] = house2;
        }
    }
}