package flow.definition.api;

import io.api.IODefinitionData;
import manager.system.operation.FlowInformation;

import java.util.List;
import java.util.Map;

public interface FlowDefinition {

    String getName();
    String getFlowDescription();
    boolean getReadOnly();
    List<StepUsageDeclaration> getStepsInFlow();
    void addStepToFlow(StepUsageDeclaration newStep, Map<IODefinitionData, IODefinitionData> newStepMapping);
    List<IODefinitionData> getFlowFormalOutput();
    List<IODefinitionData> getOutputs();
    Map<StepUsageDeclaration, Map<IODefinitionData, IODefinitionData>> getMappings();
    void addFormalOutput(IODefinitionData output);
    void setFlowReadOnly(boolean ReadOnly);
    void addOutputToFlow(IODefinitionData newOutput);
    List<IODefinitionData> getMandatoryInputs();
    List<IODefinitionData> getOptionalInputs();
    void addMandatoryInput(IODefinitionData newMandatoryInput);
    void addOptionalInput(IODefinitionData newOptionalInput);
    void CheckIfReadOnlyAndSetMember();
    IODefinitionData findValueAccordingToKeyInMappings(StepUsageDeclaration stepUsageDeclaration , IODefinitionData data);
    Map<IODefinitionData, StepUsageDeclaration> getFromOptionalInputToStep();
    Map<IODefinitionData, StepUsageDeclaration> getFromMandatoryInputToStep();
    FlowInformation getFlowInformation();
    StepUsageDeclaration fromMandatoryInputKeyToStep(IODefinitionData data);
    void validateFlowStructure(List<String> errors);

}
