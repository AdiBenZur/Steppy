package manager.system.operation;

import flow.definition.api.FlowDefinition;
import flow.execution.FlowExecution;
import flow.execution.runner.FlowExecutor;
import io.impl.UserFreeInputs;

public class ExecuteFlowManager {
    private FlowDefinition flowDefinitionToExecute;

    public ExecuteFlowManager(FlowDefinition flowDefinitionToExecute) {
        this.flowDefinitionToExecute = flowDefinitionToExecute;
    }

    public FlowExecution executeFlow(UserFreeInputs freeInputs) {
        FlowExecution flowToExecute = new FlowExecution(flowDefinitionToExecute, freeInputs);
        FlowExecutor flowExecutor = new FlowExecutor();
        flowExecutor.executeFlow(flowToExecute);
        return flowToExecute;
    }


}
