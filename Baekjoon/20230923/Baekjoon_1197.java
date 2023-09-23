import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Comparator;

public class Main
{
    static class Node
    {
        public int from;
        public int to;
        public int value;
        public Node(int from, int to, int value)
        {
            this.from = from;
            this.to = to;
            this.value = value;
        }
    }
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String[] input = reader.readLine().split(" ");
        int v = Integer.parseInt(input[0]);
        int e = Integer.parseInt(input[1]);
        Node[] nodes = new Node[e];
        int[] cycle = new int[v+1];

        for (int i = 1; i <= v; i++)
        {
            cycle[i] = i;
        }

        for (int i = 0; i < e; i++)
        {
            input = reader.readLine().split(" ");
            nodes[i] = new Node(Integer.parseInt(input[0]), Integer.parseInt(input[1]), Integer.parseInt(input[2]));
        }

        Arrays.sort(nodes, new Comparator<Node>(){
            @Override
            public int compare(Node lhs, Node rhs)
            {
                return lhs.value - rhs.value;
            }
        });

        int value = 0;
        for (int i = 0; i < e; i++)
        {
            if (!equalRoot(cycle, nodes[i].from, nodes[i].to))
            {
                value += nodes[i].value;
                unionNode(cycle, nodes[i].from, nodes[i].to);
            }
        }

        System.out.println(value);
    }

    public static int getRoot(int[] cycle, int node)
    {
        if (cycle[node] == node)
        {
            return node;
        }

        return getRoot(cycle, cycle[node]);
    }

    public static void unionNode(int[] cycle, int node1, int node2)
    {
        node1 = getRoot(cycle, node1);
        node2 = getRoot(cycle, node2);

        if (node1 < node2)
        {
            cycle[node2] = node1;
        }
        else
        {
            cycle[node1] = node2;
        }
    }

    public static boolean equalRoot(int[] cycle, int node1, int node2)
    {
        if (getRoot(cycle, node1) == getRoot(cycle, node2))
        {
            return true;
        }
        return false;
    }
}