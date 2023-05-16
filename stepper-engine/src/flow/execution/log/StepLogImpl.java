package flow.execution.log;

import flow.definition.api.StepUsageDeclaration;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class StepLogImpl implements StepLog{
    private String stepName;
    private String log;

    public StepLogImpl(String stepName, String log) {
        this.stepName = stepName;
        this.log = log;
    }


    @Override
    public String getStepName() {
        return stepName;
    }

    @Override
    public String getStepLog() {
        return log;
    }

    @Override
    public void setStepLog(String log) {
        this.log = log;
    }


}
