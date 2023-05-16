package stepper;

import flow.definition.api.FlowDefinition;
import java.util.ArrayList;
import java.util.List;

public class Stepper {

    private List<FlowDefinition> allFlows;

    public Stepper() { allFlows = new ArrayList<>(); }

    public void setFlows(List<FlowDefinition> allFlows) {
        this.allFlows = allFlows;
    }

    public List<FlowDefinition> getAllFlows() {
        return allFlows;
    }

    public void addFlowToStepper(FlowDefinition newFlow) {
        allFlows.add(newFlow); };

    public FlowDefinition getFlowDefinitionByName(String name) {
        for (int i = 0; i < allFlows.size(); i ++) {
            if(allFlows.get(i).getName().equals(name))
                return allFlows.get(i);
        }
        return null;
    }


}
