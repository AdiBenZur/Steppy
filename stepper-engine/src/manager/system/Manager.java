package manager.system;

import flow.definition.api.FlowDefinition;
import flow.definition.api.StepUsageDeclaration;
import flow.execution.FlowExecution;
import io.impl.UserFreeInputs;
import manager.system.executing.data.api.ExecutingDataManager;
import manager.system.operation.FlowInformation;
import step.api.StepDefinition;
import stepper.Stepper;

import java.time.Duration;
import java.util.List;
import java.util.Map;

public interface Manager {

    // Command no.1
    List<String> loadDataFromXml(String path);

    // Command no.2
    FlowInformation showFlowInformation(String flowName);

    // Command no.3
    FlowExecution flowExecution(FlowDefinition flowDefinition, UserFreeInputs freeInputs);



    List<String> getFlowNamesList();
    Stepper getStepper();
    public UserFreeInputs getFlowUserInputs(FlowDefinition flowDefinition);
    void addNewDataToDataManager(FlowExecution flowExecution);
    void resetManager();
    ExecutingDataManager getDataManager();
    void addStepsStatisticsFromCurrentFlow(Map<StepDefinition, Map<StepUsageDeclaration, Duration>> stepsStatistics);
    void resetStepsStatisticsManager();
    Map<StepDefinition, Map<StepUsageDeclaration, Duration>> getStepsStatisticsManager();
    public Duration getStepDurationFromStepStatisticsManager(StepUsageDeclaration stepUsageDeclaration);

}
