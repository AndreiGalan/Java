package Week1;

public class Homework {
    public static void main(String[] args) {

        if (args.length < 1) {
            System.out.println(
                    "Usage: number, number, one or more characters");
            System.exit(-1);
        }
        int k = Integer.parseInt(args[0]);

        int m[][] = new int[k][k];
        int x;

        String rez="";
        if(k>=1_000)
        {
            long startTime = System.currentTimeMillis();
            for (int i = 0; i < k; i++) {
                x=i;
                for (int j = 0; j < k; j++) {
                    m[i][j] = (x+1)%k;
                    if((x+1)%k==0)
                        m[i][j]=k;
                    x++;
                }
            }
            long endTime = System.currentTimeMillis();
            long totalTime = endTime - startTime;
            System.out.println(totalTime);
        }
        else
        {
            for (int i = 0; i < k; i++) {
                x=i;
                for (int j = 0; j < k; j++) {
                    m[i][j] = (x+1)%k;
                    if((x+1)%k==0)
                        m[i][j]=k;
                    x++;
                }
            }
            for (int i = 0; i < k; i++) {
                for (int j = 0; j < k; j++) {
                    rez = String.valueOf(rez + " " + m[i][j]);
                }
                System.out.print(rez);
                rez = "";
                System.out.println();
            }
        }
    }
}
