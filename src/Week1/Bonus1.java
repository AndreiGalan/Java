package Week1;

import java.util.Arrays;
public class Bonus1 {
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println(
                    "Usage: number, number, one or more characters");
            System.exit(-1);
        }
        int n=0;
        try{
            n = Integer.parseInt(args[0]);
        }
        catch(NumberFormatException e)
        {
            System.out.println("You must provide an integer!");
            return;
        }
        int m[][] = new int[n][n];
        int next=1;
        int last=n-1;
        for(int i=0; i<n; i++)
        {
            m[i][next]=1;
            m[i][last]=1;
            next=(next+1)%n;
            last=(last+1)%n;
        }
        System.out.println("Initial adjacency matrix:");
        for (int i = 0; i < n; i++)
        {
            for(int j=0; j<n; j++)
                System.out.print(m[i][j]+" ");
            System.out.println();
        }
        int a[][] = new int[n][n];
        for (int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                a[i][j] = m[i][j];

        int c[][] = new int[n][n];

        for(int x=2;x<=n;x++)
        {
            System.out.println("Power:" + x);
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    c[i][j] = 0;
                    for (int k = 0; k < n; k++)
                        c[i][j] += a[i][k] * m[k][j];
                    System.out.print(c[i][j] + " ");
                }
                System.out.println();
            }
            System.out.println();
            for (int i = 0; i < n; i++)
                a[i] = c[i].clone();
        }
    }
}
