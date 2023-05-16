package manager.system;


import flow.definition.api.FlowDefinition;
import flow.definition.api.StepUsageDeclaration;
import flow.execution.FlowExecution;
import io.api.IODefinitionData;
import io.impl.UserFreeInputs;
import manager.system.executing.data.api.ExecutingDataManager;
import manager.system.executing.data.impl.ExecutingDataManagerImpl;
import manager.system.operation.ExecuteFlowManager;
import manager.system.operation.FlowInformation;
import manager.system.operation.LoaderManager;
import step.api.StepDefinition;
import stepper.Stepper;

import java.time.Duration;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ManagerImpl implements Manager{
    private Stepper stepper;
    private ExecutingDataManager executingDataManager; // Save past executions
    private Map<StepDefinition, Map<StepUsageDeclaration, Duration>> stepsStatisticManager; // Save all steps statistics


    public ManagerImpl() {
        this.executingDataManager = new ExecutingDataManagerImpl();
        this.stepper = new Stepper();
        stepsStatisticManager = new HashMap<>();
    }


    @Override
    public List<String> loadDataFromXml(String path) {
        LoaderManager loader = new LoaderManager(stepper);
        List<String> errors = loader.readDataFromXml(path);
        return errors; // to Ui
    }


    @Override
    public FlowInformation showFlowInformation(String flowName) {
        // send to ui- flow information object
        // Get flow object by its name
        for(FlowDefinition flowDefinition : stepper.getAllFlows()) {
            if(flowDefinition.getName().equals(flowName))
                return flowDefinition.getFlowInformation();
        }
        return null;
    }


    @Override
    public FlowExecution flowExecution(FlowDefinition flowDefinition, UserFreeInputs freeInputs) {
        ExecuteFlowManager executeFlowManager = new ExecuteFlowManager(flowDefinition);
        FlowExecution flowExecution = executeFlowManager.executeFlow(freeInputs);
        return flowExecution;
    }



    @Override
    public List<String> getFlowNamesList() {
        List<String> names = new ArrayList<>();
        for(int i = 0; i < stepper.getAllFlows().size(); i ++) {
            names.add( stepper.getAllFlows().get(i).getName());
        }
        return names;
    }

    @Override
    public Stepper getStepper() {
        return stepper;
    }

    @Override
    public UserFreeInputs getFlowUserInputs(FlowDefinition flowDefinition) {
        List<IODefinitionData> freeInputs = new ArrayList<>();
        freeInputs.addAll(flowDefinition.getMandatoryInputs());
        freeInputs.addAll(flowDefinition.getOptionalInputs());

        Map<IODefinitionData, StepUsageDeclaration> fromInputToStep = new HashMap<>();
        freeInputs.forEach(data -> {
            fromInputToStep.put(data, flowDefinition.fromMandatoryInputKeyToStep(data));
        });

        return new UserFreeInputs(freeInputs, fromInputToStep);
    }

    @Override
    public void addNewDataToDataManager(FlowExecution flowExecution) {
        executingDataManager.addToDataManager(flowExecution);
    }

    @Override
    public void resetManager() {
        executingDataManager.resetManager();
    }

    @Override
    public ExecutingDataManager getDataManager() {
        return executingDataManager;
    }

    @Override
    public void addStepsStatisticsFromCurrentFlow(Map<StepDefinition, Map<StepUsageDeclaration, Duration>> stepsStatistics) {
        // Iterate over the key-value pairs in stepsStatistics
        for (Map.Entry<StepDefinition, Map<StepUsageDeclaration, Duration>> outerEntry : stepsStatistics.entrySet()) {
            StepDefinition stepDefinition = outerEntry.getKey();
            Map<StepUsageDeclaration, Duration> innerMapToAdd = outerEntry.getValue();

            // Check if the StepDefinition already exists in the statistics manager map
            if (stepsStatisticManager.containsKey(stepDefinition)) {

                // If it does, add the new entries to the inner map
                Map<StepUsageDeclaration, Duration> innerMap = stepsStatisticManager.get(stepDefinition);
                innerMap.putAll(innerMapToAdd);
            } else {

                // If it doesn't, add the new entry to the outer map
                stepsStatisticManager.put(stepDefinition, innerMapToAdd);
            }
        }
    }

    @Override
    public void resetStepsStatisticsManager() {
        stepsStatisticManager.clear();
    }

    @Override
    public Map<StepDefinition, Map<StepUsageDeclaration, Duration>> getStepsStatisticsManager() {
        return stepsStatisticManager;
    }

    @Override
    public Duration getStepDurationFromStepStatisticsManager(StepUsageDeclaration stepUsageDeclaration) {
        // Iterate over the key-value pairs in the statistics map
        for (Map.Entry<StepDefinition, Map<StepUsageDeclaration, Duration>> outerEntry : stepsStatisticManager.entrySet()) {
            Map<StepUsageDeclaration, Duration> innerMap = outerEntry.getValue();

            // Check if the StepUsageDeclaration exists in the inner map
            if (innerMap.containsKey(stepUsageDeclaration)) {
                // If it does, return the Duration associated with it
                return innerMap.get(stepUsageDeclaration);
            }
        }

        return null;
    }


}
