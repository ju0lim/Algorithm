import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.Arrays;

/*

- 문제

숫자 카드는 정수 하나가 적혀져 있는 카드이다.
상근이는 숫자 카드 N개를 가지고 있다. 정수 M개가 주어졌을 때, 이 수가 적혀있는 숫자 카드를 상근이가 몇 개 가지고 있는지 구하는 프로그램을 작성하시오.

- 입력

첫째 줄에 상근이가 가지고 있는 숫자 카드의 개수 N(1 ≤ N ≤ 500,000)이 주어진다.

둘째 줄에는 숫자 카드에 적혀있는 정수가 주어진다.
숫자 카드에 적혀있는 수는 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.

셋째 줄에는 M(1 ≤ M ≤ 500,000)이 주어진다.

넷째 줄에는 상근이가 몇 개 가지고 있는 숫자 카드인지 구해야 할 M개의 정수가 주어지며, 이 수는 공백으로 구분되어져 있다.
이 수도 -10,000,000보다 크거나 같고, 10,000,000보다 작거나 같다.

- 출력

첫째 줄에 입력으로 주어진 M개의 수에 대해서, 각 수가 적힌 숫자 카드를 상근이가 몇 개 가지고 있는지를 공백으로 구분해 출력한다.

*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        String[] input = reader.readLine().split(" ");
        int[] cards = new int[n];

        for (int i = 0; i < n; i++)
        {
            cards[i] = Integer.parseInt(input[i]);
        }

        Arrays.sort(cards);

        int m = Integer.parseInt(reader.readLine());
        input = reader.readLine().split(" ");
        String[] answer = new String[m];

        for (int i = 0; i < m; i++)
        {
            int target = Integer.parseInt(input[i]);
            answer[i] = String.valueOf(getUpperBound(cards, target) - getLowerBound(cards, target));
        }

        System.out.println(String.join(" ", answer));
    }

    public static int getLowerBound(int[] cards, int target)
    {
        int low = 0;
        int high = cards.length;

        while (low < high)
        {
            int mid = (low + high) / 2;

            if (target <= cards[mid])
            {
                high = mid;
            }
            else
            {
                low = mid + 1;
            }
        }

        return low;
    }

    public static int getUpperBound(int[] cards, int target)
    {
        int low = 0;
        int high = cards.length;

        while (low < high)
        {
            int mid = (low + high) / 2;

            if (target < cards[mid])
            {
                high = mid;
            }
            else
            {
                low = mid + 1;
            }
        }

        return low;
    }
}