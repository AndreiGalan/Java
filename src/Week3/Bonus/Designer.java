package Week3.Bonus;

import java.time.LocalDate;

public class Designer extends Person {
    private String designArea;

    public Designer(String name, LocalDate birthDate, String designArea)
    {
        super(name, birthDate);
        this.designArea = designArea;
    }

    public String getDesignArea() {
        return designArea;
    }
}
