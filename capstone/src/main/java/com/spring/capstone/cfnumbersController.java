package com.spring.capstone;

import com.spring.capstone.DTO.*;
import com.spring.capstone.DAO.*;
import com.spring.capstone.Service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class cfnumbersController {
    @Autowired
    private cfnumbersDAO cfNumbersDao;
    @Autowired
    private cfnumbersService cfNumbersService;

    @GetMapping("/view/goCfnumbersListView")
    public String getAllCFNumbers(Model model) {
        List<cfnumbersDTO> cfNumbersList = cfNumbersDao.getAllCFNumbers();
        model.addAttribute("cfNumbersList", cfNumbersList);
        return "goCfnumbersListView";
    }

    @GetMapping("/view/filterCfnumbers")
    public String filterCFNumbersByArea(@RequestParam("cf_area") String area, Model model) {
        List<cfnumbersDTO> filteredCfNumbersList = cfNumbersService.getFilteredCFNumbersByArea(area);
        model.addAttribute("cfNumbersList", filteredCfNumbersList);
        return "goCfnumbersListView";
    }
}
