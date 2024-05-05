package id.ac.ui.cs.pustakaone.admin.model;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "Log")
public class Log {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "action")
    String action;

    @Column(name = "date")
    String date;

    public Log(String action, String date){

        this.setAction(action);
        this.setDate(date);
    }
}