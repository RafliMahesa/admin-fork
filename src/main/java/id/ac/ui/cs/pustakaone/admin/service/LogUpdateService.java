package id.ac.ui.cs.pustakaone.admin.service;

import org.springframework.stereotype.Service;

@Service
public class LogUpdateService extends LogService{
    public String action(String id){
        return String.format("Cart dengan id %s berhasil diupdate", id);
    }
}
