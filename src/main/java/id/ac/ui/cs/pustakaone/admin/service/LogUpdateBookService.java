package id.ac.ui.cs.pustakaone.admin.service;

import org.springframework.stereotype.Service;

@Service
public class LogUpdateBookService extends LogService{
    public String action(Long id){
        return String.format("Berhasil memperbarui buku dengan id %s", id);
    }
}
