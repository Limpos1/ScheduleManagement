package entity;

import dto.Requestdto;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;

@Setter
@Getter
public class Schedule {
    private long id;
    private String task;
    private String password;
    private String managername;
    private String date;
    private String fix_date;

    public Schedule(Requestdto a) {
        this.task = a.getTask();
        this.password = a.getPassword();
        this.managername = a.getManagername();
        this.fix_date = a.getFix_date();
    }


}
