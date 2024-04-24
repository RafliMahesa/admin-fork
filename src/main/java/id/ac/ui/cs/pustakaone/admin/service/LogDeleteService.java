package id.ac.ui.cs.pustakaone.admin.service;

import org.springframework.stereotype.Service;

@Service
public class LogDeleteService extends LogService{
    public String action(String id){
        return String.format("Review dengan id %s berhasil dihapus", id);
    }
}
