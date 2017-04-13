package model;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 * Created by Pascal on 23/03/2017.
 *
 */
@Entity
@Table(name = "commande")
@NamedQuery(name="Commande.findAll", query="SELECT a FROM Commande a")
@XmlRootElement
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @XmlTransient
    private int id_commande;

    @XmlElement
    private int table_nb;

    @XmlElement
    private int cans_nb;

    @XmlElement
    private String type;

    public Commande() {
        table_nb = 0;
        cans_nb = 0;
        type = "";
    }

    public Commande(int table_number, int cans_number) {
        this();
        this.table_nb = table_number;
        this.cans_nb = cans_number;
    }

    public Commande(int table_number, int cans_number, String type) {
        this(table_number, cans_number);
        this.type = type;
    }

    public int getTable_number() {
        return table_nb;
    }

    public void setTable_number(int table_number) {
        this.table_nb = table_number;
    }

    public int getCans_number() {
        return cans_nb;
    }

    public void setCans_number(int cans_number) {
        this.cans_nb = cans_number;
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
                + "\"table_nb\":\"" + table_nb + "\""
                + ", \"cans_nb\":\"" + cans_nb + "\""
                + ", \"type\":\"" + type + "\""
                + "}}";
    }
}
