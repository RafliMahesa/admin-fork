package id.ac.ui.cs.pustakaone.admin.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Log {
    
    String action;
    String date;

    public Log(String action, String date){
        this.setAction(action);
        this.setDate(date);
    }
}
