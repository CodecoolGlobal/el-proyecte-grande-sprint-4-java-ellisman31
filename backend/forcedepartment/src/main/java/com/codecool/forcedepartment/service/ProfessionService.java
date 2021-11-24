package com.codecool.forcedepartment.service;

import com.codecool.forcedepartment.dal.ProfessionRepository;
import com.codecool.forcedepartment.model.Profession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProfessionService {

    private ProfessionRepository professionRepository;

    @Autowired
    public ProfessionService(ProfessionRepository professionRepository) {
        this.professionRepository = professionRepository;
    }

    public List<String> getAllProfession() {
        return professionRepository.findAll().stream()
                .map(Profession::getProfession_name)
                .collect(Collectors.toList());
    }
}
