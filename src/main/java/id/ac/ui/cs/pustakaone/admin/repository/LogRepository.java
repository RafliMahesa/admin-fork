package id.ac.ui.cs.pustakaone.admin.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import id.ac.ui.cs.pustakaone.admin.model.Log;

@Repository
public class LogRepository {
    private static List<Log> logData;
    
    
    public LogRepository(){
        logData = new ArrayList<>();
    }

    public Log createLog(Log log){
        logData.add(log);
        return log;
    }

    public List<Log> allLog(){
        return logData;
    }

}
