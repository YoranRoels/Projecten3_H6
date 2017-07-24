package projecten3_h6.evaandroid.Domain;

import java.io.Serializable;

/**
 * Created by Mafken on 4/07/2017.
 */
public class Ingredient implements Serializable{

    private static final long serialVersionUID = 5;
    private long shoppinglistId;
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

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public long getShoppinglistId() {
        return this.shoppinglistId;
    }

    public void setShoppinglistId(long shoppinglistId) {
        this.shoppinglistId = shoppinglistId;
    }
}
