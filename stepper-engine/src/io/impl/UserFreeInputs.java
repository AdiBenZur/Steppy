package io.impl;

import exception.data.NotUserFriendlyException;
import exception.data.TypeDontMatchException;
import flow.definition.api.StepUsageDeclaration;
import io.api.DataNecessity;
import io.api.IODefinitionData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserFreeInputs {
    private List<IODefinitionData> freeInputs;
    private Map<IODefinitionData, StepUsageDeclaration> fromInputToStep;
    private Map<IODefinitionData, Object> fromInputToObject;

    public UserFreeInputs(List<IODefinitionData> freeInputs, Map<IODefinitionData, StepUsageDeclaration> fromInputToStep) {
        this.freeInputs = freeInputs;
        this.fromInputToStep = fromInputToStep;
        fromInputToObject = new HashMap<>();
    }

    public List<IODefinitionData> getFreeInputs() {
        return freeInputs;
    }

    public Map<IODefinitionData, StepUsageDeclaration> getFromInputToStep() {
        return fromInputToStep;
    }

    public Map<IODefinitionData, Object> getFromInputToObject() {
        return fromInputToObject;
    }

    public Boolean isAllMandatoryInsert() {
        for(IODefinitionData data : freeInputs) {
            if(data.getNecessity() == DataNecessity.MANDATORY)
                if(!isDataInserted(data))
                    return false;
        }
        return true;
    }

    public Boolean isDataInserted(IODefinitionData input) {
        if(fromInputToObject.containsKey(input))
            if(fromInputToObject.get(input) != null)
                return true;
        return false;
    }

    // Add input to fromInputToObject
    public void scanInput(IODefinitionData data, String value) throws TypeDontMatchException {
        try {
            fromInputToObject.put(data, data.getDataDefinition().scanInput(value));
        }
        catch (NotUserFriendlyException e) {}
    }

    public StepUsageDeclaration findStepByInputKey(IODefinitionData key) {
       return fromInputToStep.get(key);
    }
}
