package org.lessons.java.spring.spring_la_mia_pizzeria_crud.service;

import org.lessons.java.spring.spring_la_mia_pizzeria_crud.model.Offert;
import org.lessons.java.spring.spring_la_mia_pizzeria_crud.repository.OffertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OffertService {

    @Autowired
    private OffertRepository offertRepository;

    public Offert create(Offert offert) {
        return offertRepository.save(offert);
    }

    public Offert getById(Integer id) {
        return offertRepository.findById(id).get();
    }
}
