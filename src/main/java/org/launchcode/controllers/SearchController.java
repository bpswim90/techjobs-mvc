package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @RequestMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("checked","all");
        return "search";
    }

    @RequestMapping(value = "/results")
    public String results(@RequestParam String searchType, @RequestParam String searchTerm, Model model) {
        model.addAttribute("columns", ListController.columnChoices);
        model.addAttribute("checked", searchType);

        if (searchType.equals("all")) {
            model.addAttribute("jobs", JobData.findByValue(searchTerm));
        } else {
            model.addAttribute("jobs", JobData.findByColumnAndValue(searchType, searchTerm));
        }
        return "search";
    }

}
