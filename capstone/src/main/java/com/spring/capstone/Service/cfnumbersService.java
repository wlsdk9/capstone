package com.spring.capstone.Service;

import com.spring.capstone.DAO.cfnumbersDAO;
import com.spring.capstone.DTO.cfnumbersDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class cfnumbersService {
    @Autowired
    private cfnumbersDAO cfNumbersDao;

    public List<cfnumbersDTO> getAllCFNumbers() {
        return cfNumbersDao.getAllCFNumbers();
    }

    public List<cfnumbersDTO> getFilteredCFNumbersByArea(String area) {
        List<cfnumbersDTO> allCfNumbers = cfNumbersDao.getAllCFNumbers();
        List<cfnumbersDTO> filteredCfNumbers = new ArrayList<>();

        for (cfnumbersDTO cfNumber : allCfNumbers) {
            if (cfNumber.getCfArea().equals(area)) {
                filteredCfNumbers.add(cfNumber);
            }
        }

        return filteredCfNumbers;
    }
}
