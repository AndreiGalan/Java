package Week3.Compulsory;

public class Company implements Comparable<Company>, Node {
    private String name;

    public Company(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int compareTo(Company other) {
        return this.name.compareTo(other.name);
    }
}
