package id.ac.ui.cs.pustakaone.admin.service;
import id.ac.ui.cs.pustakaone.admin.repository.LogRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import id.ac.ui.cs.pustakaone.admin.model.Log;

public abstract class LogService {
    LogRepository logRepository; 

    @Autowired
    public LogService(){
        logRepository = new LogRepository();
    }
    
    public Log createLog(String id){
        String date = getCurrentDate();
        String action = action(id);
        Log log = new Log(action, date);
        return logRepository.createLog(log);
    }

    public abstract String action(String id);

    public String getCurrentDate(){
        LocalDate today = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return today.format(formatter);
    }

    public List<Log> getAllLog(){
        return logRepository.allLog();
    }

}
