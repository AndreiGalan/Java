package Week1;

public class Bonus2 {
    static int choose(int[] m, int n, int x)
    {
        int max=0, ok=0, imax=0;
        for(int i=0;i<n;i++)
            if(m[i]>max && i!=x)
                max=m[i];
        for(int i=0;i<n && ok==0;i++)
            if(m[i]==max && i!=x)
            {
                imax=i;
                ok=1;
            }
        return imax;
    }
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println(
                    "Usage: number, number, one or more characters");
            System.exit(-1);
        }
        int n =0;
        int k =0;
        try {
            n = Integer.parseInt(args[0]);
            k = Integer.parseInt(args[1]);
        }
        catch(NumberFormatException e) {
            System.out.println("You must provide an integer!");
            return;
        }
        if((n * k) % 2 != 0)
        {
            System.out.println(
                    "Incorect data! Number of nodes * vertex degree should be even!");
            System.exit(-1);
        }
        int matrix[][] = new int[n][n];
        int vertexDegree[] = new int[n];
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++)
                matrix[i][j] = 0;
        for (int i = 0; i < n; i++)
            vertexDegree[i] = k;

        for (int i = 0; i < n; i++)
            while (vertexDegree[i] != 0) {
                int j = choose(vertexDegree, n, i);
                matrix[i][j] = 1;
                matrix[j][i] = 1;
                vertexDegree[i]--;
                vertexDegree[j]--;
            }
        for(int i=0;i<n;i++)
        {
            for(int j=0;j<n;j++)
                System.out.print(matrix[i][j]+" ");
            System.out.println();
        }
    }
}
