package Week3.Homework;

import java.time.LocalDate;

public class Programmer extends Person{
    private String launguageUsed;

    public Programmer(String name, LocalDate birthDate, String launguageUsed)
    {
        super(name,birthDate);
        this.launguageUsed = launguageUsed;
    }

    public String getLaunguageUsed() {
        return launguageUsed;
    }
}
