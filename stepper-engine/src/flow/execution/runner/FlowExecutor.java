package flow.execution.runner;

import flow.definition.api.StepUsageDeclaration;
import flow.execution.FlowExecution;
import flow.execution.FlowExecutionResult;
import step.api.StepResult;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalTime;

public class FlowExecutor {

    // Running the flow.
    public void executeFlow(FlowExecution flowExecution) {
        Boolean stopRunInTheMiddle = false;
        Boolean isStopRunningFailedButContinue = false;
        Boolean isStepRunResultIsWarning = false;

        // Set Starting time
        Instant flowStartTime = Instant.now();

        LocalTime localTime = LocalTime.now();
        String formatted = localTime.getHour() + ":" + localTime.getMinute() + ":" + localTime.getSecond();
        flowExecution.setStartTimeOfExecution(formatted);

        for( int i = 0; i < flowExecution.getFlowDefinition().getStepsInFlow().size(); i ++) {

            // run steps in order
            StepUsageDeclaration stepUsageDeclaration = flowExecution.getFlowDefinition().getStepsInFlow().get(i);
            flowExecution.getContext().updateCurrentRunningStep(stepUsageDeclaration);

            // Execute step
            Instant stepStartTime = Instant.now();

            StepResult stepResult = stepUsageDeclaration.getStepDefinition().run(flowExecution.getContext());

            Instant stepEndTime = Instant.now();
            Duration duration = Duration.between(stepStartTime, stepEndTime);

            stepUsageDeclaration.getStepDefinition().setStepResult(stepResult);

            // Add to statistics
            flowExecution.addStatistics(stepUsageDeclaration, duration);

            // Done execute step

            // Check current step result
            if(stepResult == StepResult.WARNING) {
                isStepRunResultIsWarning = true;
            }
            else {
                if(stepResult == StepResult.FAILURE) {

                    // Check whether to continue the flow or stop.
                    if(!stepUsageDeclaration.skipIfFail()) {
                        // Stop the running
                        Instant endFlowTimeInterrupted = Instant.now();
                        Duration flowDurationInterrupt = Duration.between(flowStartTime, endFlowTimeInterrupted);
                        flowExecution.setTotalTime(flowDurationInterrupt);
                        flowExecution.addFlowLog("The flow run has stopped. Step name: " + stepUsageDeclaration.getStepName() + " failed.");
                        stopRunInTheMiddle = true;
                        break;
                    }
                    else {
                        isStopRunningFailedButContinue = true;
                        flowExecution.addFlowLog("Step name: " + stepUsageDeclaration.getStepName() + " failed but the flow continue running.");
                    }
                }
            }
        }
        Instant flowEndTime = Instant.now();

        // Update flow's result
        if(stopRunInTheMiddle) {
            flowExecution.setFlowExecutionResult(FlowExecutionResult.FAILURE);
            flowExecution.setFlowSummaryLine("The flow failed due to a failure of step.");
        }
        else {
            // The flow ended, set duration
            Duration flowDuration = Duration.between(flowStartTime, flowEndTime);
            flowExecution.setTotalTime(flowDuration);

            if(isStopRunningFailedButContinue) {
                flowExecution.setFlowExecutionResult(FlowExecutionResult.WARNING);
                flowExecution.setFlowSummaryLine("The flow run ended with warning due to a failure of step.");
            }
            else {
                if(isStepRunResultIsWarning) {
                    flowExecution.setFlowExecutionResult(FlowExecutionResult.WARNING);
                    flowExecution.setFlowSummaryLine("The flow run ended with warning due to a step that ended with warning.");
                }
                else {
                    flowExecution.setFlowExecutionResult(FlowExecutionResult.SUCCESS);
                    flowExecution.setFlowSummaryLine("The flow execute successfully.");
                }
            }
        }

        // SetFlowOutputs
        flowExecution.setAllOutputsData(flowExecution.getContext());

    }
}
