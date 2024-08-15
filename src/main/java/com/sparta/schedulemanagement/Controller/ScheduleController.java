package com.sparta.schedulemanagement.Controller;

import dto.Requestdto;
import dto.Responsedto;
import entity.Schedule;
import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/schedule")
public class ScheduleController {
    private final JdbcTemplate jdbcTemplate;
    public ScheduleController(JdbcTemplate jdbcTemplate) {this.jdbcTemplate = jdbcTemplate;}

    @PostMapping("/regist")
    public Responsedto taskregist(@ModelAttribute Requestdto requestdto){

        Schedule schedule = new Schedule(requestdto);

        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO schedule(task,password,managername,date,fix_date) VALUES(?,?,?,NOW(),NOW())";
        jdbcTemplate.update( con -> {
            PreparedStatement preparedStatement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStatement.setString(1, requestdto.getTask());
            preparedStatement.setString(2, requestdto.getPassword());
            preparedStatement.setString(3, requestdto.getManagername());
            return preparedStatement;
        },keyHolder);

        Long id = keyHolder.getKey().longValue();
        schedule.setId(id);
        String date_sql = "SELECT date, fix_date FROM schedule WHERE id=?";
        Map<String, Object> result = jdbcTemplate.queryForMap(date_sql,id);
        LocalDateTime date = (LocalDateTime)result.get("date");
        LocalDateTime fix_date = (LocalDateTime)result.get("fix_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        schedule.setDate(date.format(formatter));
        schedule.setFix_date(fix_date.format(formatter));

        Responsedto responsedto = new Responsedto(schedule);
        return responsedto;

    }

    @GetMapping("/inquiry")
    public Responsedto taskinquiry(@ModelAttribute Requestdto requestdto){
        String sql = "SELECT * FROM schedule WHERE id=?";

        Map<String, Object> result = jdbcTemplate.queryForMap(sql,requestdto.getId());
        long id = (long)result.get("id");
        String task = (String)result.get("task");
        String managername = (String)result.get("managername");
        LocalDateTime date = (LocalDateTime)result.get("date");
        LocalDateTime fix_date = (LocalDateTime)result.get("fix_date");
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        Responsedto responsedto = new Responsedto(id,task,managername,date.format(formatter),fix_date.format(formatter));
        return responsedto;
    }

    @GetMapping("/condition")
    public List<Responsedto> taskcondition(@ModelAttribute Requestdto requestdto){
        Schedule schedule = new Schedule(requestdto);
        if(schedule.getFix_date() != null && schedule.getManagername() != null){
            String sql = "SELECT * FROM schedule WHERE DATE(fix_date)=? AND managername=? ORDER BY fix_date DESC";
            return jdbcTemplate.query(sql,new Object[]{requestdto.getFix_date(),requestdto.getManagername()}, new RowMapper<Responsedto>(){
                @Override
                public Responsedto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long id = rs.getLong("id");
                    String task = rs.getString("task");
                    String managername = rs.getString("managername");
                    String date = rs.getString("date");
                    String fix_date = rs.getString("fix_date");

                    return new Responsedto(id,task,managername,date,fix_date);
                }
            });
        }
        else if(schedule.getFix_date() == null && schedule.getManagername() != null ){
            String sql = "SELECT * FROM schedule WHERE managername=? ORDER BY fix_date DESC";
            return jdbcTemplate.query(sql,new Object[]{requestdto.getManagername()}, new RowMapper<Responsedto>(){
                @Override
                public Responsedto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long id = rs.getLong("id");
                    String task = rs.getString("task");
                    String managername = rs.getString("managername");
                    String date = rs.getString("date");
                    String fix_date = rs.getString("fix_date");

                    return new Responsedto(id,task,managername,date,fix_date);
                }
            });
        }
        else if(schedule.getFix_date() != null && schedule.getManagername() == null){
            String sql = "SELECT * FROM schedule WHERE DATE(fix_date)=? ORDER BY fix_date DESC";
            return jdbcTemplate.query(sql,new Object[]{requestdto.getFix_date()}, new RowMapper<Responsedto>(){
                @Override
                public Responsedto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long id = rs.getLong("id");
                    String task = rs.getString("task");
                    String managername = rs.getString("managername");
                    String date = rs.getString("date");
                    String fix_date = rs.getString("fix_date");

                    return new Responsedto(id,task,managername,date,fix_date);
                }
            });
        }
        else{
            String sql = "SELECT * FROM schedule ORDER BY fix_date DESC";
            return jdbcTemplate.query(sql,new Object[]{}, new RowMapper<Responsedto>(){
                @Override
                public Responsedto mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Long id = rs.getLong("id");
                    String task = rs.getString("task");
                    String managername = rs.getString("managername");
                    String date = rs.getString("date");
                    String fix_date = rs.getString("fix_date");

                    return new Responsedto(id,task,managername,date,fix_date);
                }
            });
        }
    }

}
