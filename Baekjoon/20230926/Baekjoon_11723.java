import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.StringTokenizer;

/*

비어있는 공집합 S가 주어졌을 때, 아래 연산을 수행하는 프로그램을 작성하시오.

add x: S에 x를 추가한다. (1 ≤ x ≤ 20) S에 x가 이미 있는 경우에는 연산을 무시한다.
remove x: S에서 x를 제거한다. (1 ≤ x ≤ 20) S에 x가 없는 경우에는 연산을 무시한다.
check x: S에 x가 있으면 1을, 없으면 0을 출력한다. (1 ≤ x ≤ 20)
toggle x: S에 x가 있으면 x를 제거하고, 없으면 x를 추가한다. (1 ≤ x ≤ 20)
all: S를 {1, 2, ..., 20} 으로 바꾼다.
empty: S를 공집합으로 바꾼다.

입력

첫째 줄에 수행해야 하는 연산의 수 M (1 ≤ M ≤ 3,000,000)이 주어진다.
둘째 줄부터 M개의 줄에 수행해야 하는 연산이 한 줄에 하나씩 주어진다.

출력

check 연산이 주어질때마다, 결과를 출력한다.

*/

public class Main
{
    public static void main(String[] args) throws IOException
    {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(reader.readLine());
        StringBuilder builder = new StringBuilder();
        int bit = 0;

        for (int i = 0; i < n; i++)
        {
            StringTokenizer st = new StringTokenizer(reader.readLine());
            String command = st.nextToken();
            int num;

            switch (command)
            {
                case "add":
                    num = Integer.parseInt(st.nextToken());
                    bit |= (1 << num -1);
                    break;

                case "remove":
                    num = Integer.parseInt(st.nextToken());
                    bit &= ~(1 << num -1);
                    break;

                case "check":
                    num = Integer.parseInt(st.nextToken());
                    builder.append((bit & (1 << (num-1))) == 0 ? "0\n" : "1\n");
                    break;

                case "toggle":
                    num = Integer.parseInt(st.nextToken());
                    bit ^= (1 << num-1);
                    break;

                case "all":
                    bit |= (~0);
                    break;

                case "empty":
                    bit &= 0;
                    break;
            }
        }

        System.out.println(builder);
    }
}