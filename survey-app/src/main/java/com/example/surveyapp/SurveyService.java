package com.example.surveyapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SurveyService {

    @Autowired
    private SurveyRepository surveyRepository;

    public List<Survey> getAllSurveys() {
        return surveyRepository.findAll();
    }

    public Survey getSurveyById(Long id) {
        return surveyRepository.findById(id).orElseThrow(() -> new RuntimeException("Survey not found"));
    }

    public Survey createSurvey(Survey survey) {
        return surveyRepository.save(survey);
    }

    public Survey updateSurvey(Long id, Survey updatedSurvey) {
        Survey existingSurvey = getSurveyById(id);
        updatedSurvey.setId(existingSurvey.getId());
        return surveyRepository.save(updatedSurvey);
    }

    public void deleteSurvey(Long id) {
        surveyRepository.deleteById(id);
    }
}

