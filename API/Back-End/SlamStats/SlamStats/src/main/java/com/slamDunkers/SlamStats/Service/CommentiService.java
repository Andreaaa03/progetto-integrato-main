package com.slamDunkers.SlamStats.Service;

import com.slamDunkers.SlamStats.Entity.Commenti;
import com.slamDunkers.SlamStats.Repository.CommentiRepository;
import org.springframework.stereotype.Service;

@Service
public class CommentiService {
    public final CommentiRepository commentiRepository;

    public CommentiService(CommentiRepository commentiRepository) {
        this.commentiRepository = commentiRepository;
    }

    public void addCommento(Commenti commento) {

    	commentiRepository.save(commento);
    }
}
