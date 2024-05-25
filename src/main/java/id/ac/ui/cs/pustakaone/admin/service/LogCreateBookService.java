package id.ac.ui.cs.pustakaone.admin.service;

import org.springframework.stereotype.Service;

@Service
public class LogCreateBookService extends LogService{
    public String action(Long id){
        return String.format("Berhasil membuat buku dengan id %s", id);
    }
}
