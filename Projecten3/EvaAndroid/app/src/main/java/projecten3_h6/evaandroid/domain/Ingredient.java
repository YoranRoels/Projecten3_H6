package projecten3_h6.evaandroid.domain;

import java.io.Serializable;

public class Ingredient implements Serializable{

    private static final long serialVersionUID = 5;
    private String name;
    private String amount;

    public Ingredient(String name, String amount) {
        this.name = name;
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }
}
