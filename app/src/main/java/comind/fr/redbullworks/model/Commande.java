package comind.fr.redbullworks.model;

/**
 * Created by Pascal on 22/03/2017.
 *
 */

public class Commande {

    private int table_number;
    private int cans_number;
    private String type;

    public Commande() {
        table_number = 0;
        cans_number = 0;
        type = "";
    }

    public Commande(int table_number, int cans_number) {
        this();
        this.table_number = table_number;
        this.cans_number = cans_number;
    }

    public Commande(int table_number, int cans_number, String type) {
        this(table_number, cans_number);
        this.type = type;
    }

    public int getTable_number() {
        return table_number;
    }

    public void setTable_number(int table_number) {
        this.table_number = table_number;
    }

    public int getCans_number() {
        return cans_number;
    }

    public void setCans_number(int cans_number) {
        this.cans_number = cans_number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "{\"Commande\":{"
                + "\"table_number\":\"" + table_number + "\""
                + ", \"cans_number\":\"" + cans_number + "\""
                + ", \"type\":\"" + type + "\""
                + "}}";
    }
}
