package model;

import javax.persistence.*;
import java.util.Date;

/**
 * Created by Pascal on 23/03/2017.
 *
 */

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int Id;

    @Temporal(TemporalType.DATE)
    private Date startDate;

    @Temporal(TemporalType.DATE)
    private Date endDate;

    private String startHour;
    private String endHour;


    public Reservation() {
        startDate = new Date();
    }

    public Reservation(Date start, Date end, String userName, String userMail, String userPhone, String startHour, String endHour) {
        startDate = start;
        endDate = end;
        this.startHour = startHour;
        this.endHour = endHour;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getStartHour() {
        return startHour;
    }

    public void setStartHour(String startHour) {
        this.startHour = startHour;
    }

    public String getEndHour() {
        return endHour;
    }

    public void setEndHour(String endHour) {
        this.endHour = endHour;
    }


    @Override
    public String toString() {
        return "{\"Reservation\":{"
                + "\"startDate\":" + startDate
                + ", \"endDate\":" + endDate
                + ", \"startHour\":\"" + startHour + "\""
                + ", \"endHour\":\"" + endHour + "\""
                + "}}";
    }
}
