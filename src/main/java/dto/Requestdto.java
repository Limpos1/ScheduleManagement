package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Requestdto {
    private String managername;
    private String password;
    private String task;

    public Requestdto(String managername, String password, String task) {
        this.managername = managername;
        this.password = password;
        this.task = task;
    }
}
