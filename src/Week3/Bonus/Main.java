package Week3.Bonus;

import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        Network network = new Network();
        LocalDate birthDate1 = LocalDate.of(1985,2,4);
        LocalDate birthDate2 = LocalDate.of(1985,7,20);
        LocalDate birthDate3 = LocalDate.of(1995,3,20);
        LocalDate birthDate4 = LocalDate.of(1980,11,25);

        Programmer programmer1 = new Programmer("Alice", birthDate1, "Java");
        Programmer programmer2 = new Programmer("Bob", birthDate2, "Python");
        Programmer programmer3 = new Programmer("John", birthDate2, "Python");
        Designer designer1 = new Designer("Charlie", birthDate3, "Graphic design");
        Designer designer2 = new Designer("David", birthDate4, "Web design");
        Company company1 = new Company("Acme Inc.");
        Company company2 = new Company("Beta Corp.");

        programmer1.addConnection(programmer2);
        programmer1.addConnection(company1);
        programmer2.addConnection(company1);
        programmer3.addConnection(company1);

        network.addNode(programmer1);
        network.addNode(programmer2);
        network.addNode(company1);
        network.addNode(programmer3);

        network.prinNetwork();
        network.printArticulationNodes();
        network.printBlocks();

    }
}
