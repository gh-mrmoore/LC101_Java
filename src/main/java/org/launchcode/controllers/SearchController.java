package org.launchcode.controllers;

import org.launchcode.models.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
        return "search";
    }

    // TODO #1 - Create handler to process search request and display results
    @RequestMapping(value="results")
    public String results(Model model, @RequestParam String searchType,
                          @RequestParam String searchTerm) {

        if(searchType.equals("all")) {
            //System.out.println(searchType + " | " + searchTerm);
            ArrayList<HashMap<String, String>> results = JobData.findByValue(searchTerm);
            int size = results.size();
            //System.out.println(results);
            //System.out.println(size);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("title", "All Jobs Search Results");
            model.addAttribute("results", results);
            model.addAttribute("size", size);
            return "search";
        } else {
            //System.out.println(searchType + " | " + searchTerm);
            ArrayList<HashMap<String, String>> results = JobData.findByColumnAndValue(searchType, searchTerm);
            int size = results.size();
            //System.out.println(results);
            //System.out.println(size);
            model.addAttribute("columns", ListController.columnChoices);
            model.addAttribute("title", "Specific Search Results");
            model.addAttribute("results", results);
            model.addAttribute("size", size);
            return "search";
        }

    }

}
