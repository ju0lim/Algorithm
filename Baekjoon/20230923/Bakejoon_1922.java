import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main
{
    static class Connect
    {
        public int from;
        public int to;
        public int cost;
        public Connect(int from, int to, int cost)
        {
            this.from = from;
            this.to = to;
            this.cost = cost;
        }
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        int m = Integer.parseInt(reader.readLine());
        Connect[] connects = new Connect[m];
        int[] root = new int[n+1];

        for (int i = 1; i <= n; i++)
        {
            root[i] = i;
        }

        for (int i = 0; i < m; i++)
        {
            String[] input = reader.readLine().split(" ");
            connects[i] = new Connect(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        }

        Arrays.sort(connects, new Comparator<Connect>(){
            @Override
            public int compare(Connect lhs, Connect rhs)
            {
                return lhs.cost - rhs.cost;
            }
        });

        int totalCost = 0;
        for (int i = 0; i < m; i++)
        {
            if (getRoot(root, connects[i].from) != getRoot(root, connects[i].to))
            {
                totalCost += connects[i].cost;
                union(root, connects[i].from, connects[i].to);
            }
        }

        System.out.println(totalCost);
    }

    public static int getRoot(int[] root, int com)
    {
        if (root[com] == com)
        {
            return com;
        }
        return getRoot(root, root[com]);
    }

    public static void union(int[] root, int com1, int com2)
    {
        com1 = getRoot(root, com1);
        com2 = getRoot(root, com2);

        root[com1] = com2;
    }
}