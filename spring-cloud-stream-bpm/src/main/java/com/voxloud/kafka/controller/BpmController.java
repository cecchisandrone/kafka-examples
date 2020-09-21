package com.voxloud.kafka.controller;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.ProcessEngine;
import org.camunda.bpm.engine.repository.ProcessDefinition;
import org.camunda.bpm.engine.runtime.ProcessInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class BpmController {

    @Autowired
    private ProcessEngine processEngine;

    @PostMapping("/bpm/start-simple")
    public void startSimple() {
        List<ProcessDefinition> processDefinitions = processEngine.getRepositoryService().createProcessDefinitionQuery().list();
        log.info("Installed process definitions: " + processDefinitions);
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("simple");
        log.info("Started process " + processInstance.getId());
    }

    @DeleteMapping("/bpm/delete-history")
    public String deleteHistory() {
        return processEngine.getHistoryService().cleanUpHistoryAsync(true).getId();
    }
}
