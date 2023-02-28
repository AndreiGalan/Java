package Week1;

public class Compulsory {
    public static void main(String[] args) {

        System.out.println("Hello world!");
        String languages[] = {"C", "C++", "C#", "Python", "Go", "Rust", "JavaScript", "PHP", "Swift", "Java"};
        int n = (int) (Math.random() * 1_000_000);
        System.out.println("Random number: " + n);
        n = n * 3;
        System.out.println("Random number * 3: " + n);
        n = n + Integer.parseInt("10101", 2);
        System.out.println("Random number * 3 + 10101: " + n);
        n = n + Integer.parseInt("FF", 16);
        System.out.println("Random number * 3 + 10101 + FF: " + n);
        n = n * 6;
        System.out.println("Random number * 3 + 10101 + FF * 6: " + n);

        while (n / 10 != 0) {
            int aux = n;
            n = 0;
            while (aux != 0) {
                n += aux % 10;
                aux /= 10;
            }
            System.out.println("New sum: " + n);
        }
        System.out.println("Final sum: " + n);
        System.out.println("Willy-nilly, this semester I will learn " + languages[n]);


    }
}
