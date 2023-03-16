package Week3.Compulsory;

public class Person implements Comparable<Person>, Node {
    private String name;

    public Person(String name) {
        this.name = name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    public int compareTo(Person other) {
        return this.name.compareTo(other.name);
    }
}
