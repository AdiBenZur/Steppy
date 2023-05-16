package flow.execution.log;

import flow.definition.api.StepUsageDeclaration;

import java.util.List;

public interface StepLog {
    String getStepName();
    String getStepLog();
    void setStepLog(String log);
}
