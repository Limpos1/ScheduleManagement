package dto;

import entity.Schedule;
import lombok.Getter;

import java.sql.Timestamp;

@Getter
public class Responsedto {
    private long id;
    private String task;
    private String managername;
    private String date;
    private String fix_date;

    public Responsedto(Schedule schedule) {
        this.id = schedule.getId();
        this.task = schedule.getTask();
        this.managername = schedule.getManagername();
        this.date = schedule.getDate();
        this.fix_date = schedule.getFix_date();
    }
    public Responsedto(long id, String task, String managername, String date, String fix_date) {
        this.id = id;
        this.task = task;
        this.managername = managername;
        this.date = date;
        this.fix_date = fix_date;
    }
}
