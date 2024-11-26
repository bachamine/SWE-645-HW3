package com.example.surveyapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/surveys")
public class SurveyController {

    @Autowired
    private SurveyService surveyService;

    @GetMapping
    public List<Survey> getAllSurveys() {
        return surveyService.getAllSurveys();
    }

    @GetMapping("/{id}")
    public Survey getSurveyById(@PathVariable Long id) {
        return surveyService.getSurveyById(id);
    }

    @PostMapping
    public Survey createSurvey(@RequestBody Survey survey) {
        return surveyService.createSurvey(survey);
    }

    @PutMapping("/{id}")
    public Survey updateSurvey(@PathVariable Long id, @RequestBody Survey survey) {
        return surveyService.updateSurvey(id, survey);
    }

    @DeleteMapping("/{id}")
    public void deleteSurvey(@PathVariable Long id) {
        surveyService.deleteSurvey(id);
    }
}

