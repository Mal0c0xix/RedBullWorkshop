package comind.fr.redbullworks.model;

import java.util.Date;

/**
 * Created by Pascal on 22/03/2017.
 *
 */

public class Reservation {

    private Date startDate;
    private Date endDate;
    private String userName;
    private String userMail;
    private String userPhone;
    private String type;


    public Reservation() {
        startDate = new Date();
        userName = "";
        userMail = "";
        userPhone = "";
        type = "";
    }

    public Reservation(Date start, Date end, String userName, String userMail, String userPhone) {
        startDate = start;
        endDate = end;
        this.userName = userName;
        this.userMail = userMail;
        this.userPhone = userPhone;
    }

    public Reservation(Date start, Date end, String userName, String userMail, String userPhone, String type) {
        this(start, end, userName, userMail, userPhone);
        this.type = type;
    }


    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMail() {
        return userMail;
    }

    public void setUserMail(String userMail) {
        this.userMail = userMail;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "Reservation{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                ", userName='" + userName + '\'' +
                ", userMail='" + userMail + '\'' +
                ", userPhone='" + userPhone + '\'' +
                '}';
    }
}
