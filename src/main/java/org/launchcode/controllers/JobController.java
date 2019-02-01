package org.launchcode.controllers;

import org.launchcode.models.Employer;
import org.launchcode.models.Job;
import org.launchcode.models.PositionType;
import org.launchcode.models.forms.JobForm;
import org.launchcode.models.data.JobData;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.ArrayList;

/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping(value = "job")
public class JobController {

    private JobData jobData = JobData.getInstance();

    // The detail display for a given Job at URLs like /job?id=17
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(Model model, int id) {

        // TODO #1 - get the Job with the given ID and pass it into the view
        model.addAttribute("job_id", id);

        Job thisJob = jobData.findById(id);

        model.addAttribute("thisJob", thisJob);

        return "job-detail";
    }

    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add(Model model) {
        model.addAttribute(new JobForm());
        return "new-job";
    }

    @RequestMapping(value = "add", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid JobForm newJobForm, Errors errors) {

        // TODO #6 - Validate the JobForm model, and if valid, create a
        // new Job and add it to the jobData data store. Then
        // redirect to the job detail view for the new Job.
        //Employer thisNewEmployer = new Employer();
        //PositionType thisNewPosition = new PositionType(jobData.findById(newJobForm.getPositionID()).getPositionType());

        if (errors.hasErrors()) {
            /*
            Job thisNewJob = new Job(newJobForm.getName(),
                    jobData.findById(newJobForm.getEmployerId()).getEmployer(),
                    jobData.findById(newJobForm.getLocationID()).getLocation(),
                    jobData.findById(newJobForm.getPositionID()).getPositionType(),
                    jobData.findById(newJobForm.getSkillID()).getCoreCompetency());
            System.out.println(" ");
            System.out.println(" ");
            System.out.println("Job Form object passed in: " + newJobForm);
            System.out.println(" ");
            System.out.println(" ");
            System.out.println(errors);
            System.out.println(" ");
            System.out.println("NEW JOB OBJECT FROM ERROR: " + thisNewJob);
            System.out.println(" ");
            System.out.println("NEW POSITION"  + thisNewJob.getPositionType());
            System.out.println("POSITION FROM newJOBFORM " + jobData.findById(newJobForm.getPositionID()).getPositionType());
            System.out.println(" ");
            System.out.println("NEW SKILL " + thisNewJob.getCoreCompetency());
            System.out.println("NEW LOCATION " + thisNewJob.getLocation());
            System.out.println(" ");
            System.out.println(" ");
            */
            model.addAttribute("title", "Fix Job Addition");
            model.addAttribute("errors", errors);
            model.addAttribute("thisjob", newJobForm);
            return "new-job";
        } else {

            //name, employer, location, positiontype, corecomp
            Job thisNewJob = new Job(newJobForm.getName(),
                                        jobData.findById(newJobForm.getEmployerId()).getEmployer(),
                                        jobData.findById(newJobForm.getLocationID()).getLocation(),
                                        jobData.findById(newJobForm.getPositionID()).getPositionType(),
                                        jobData.findById(newJobForm.getSkillID()).getCoreCompetency());
            //System.out.println("NEW JOB OBJECT: " + thisNewJob);

            jobData.add(thisNewJob);

            //return "list-jobs";
            return "redirect:?id=" + thisNewJob.getId();
        }
    }
}
