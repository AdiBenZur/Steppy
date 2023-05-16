package manager.system.operation;

import flow.definition.api.FlowDefinition;
import stepper.Stepper;
import xml.jaxb.schema.generated.STFlow;
import xml.jaxb.schema.generated.STStepper;
import xml.parse.api.Parser;
import xml.parse.impl.FlowParser;
import xml.validate.api.Validator;
import xml.validate.impl.file.ValidateXmlFile;
import xml.validate.impl.flow.ValidateEveryFlowHasUniqueName;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class LoaderManager {
    private Stepper stepper;

    public LoaderManager(Stepper stepper) {
        this.stepper = stepper;
    }

    public List<String> readDataFromXml(String path) {
        List<String> errors = new ArrayList<>();

        // Reset system
        resetSystem();


        // Validate xml
        Validator validateXmlFile = new ValidateXmlFile(path);
        validateXmlFile.validate(errors);
        if(!errors.isEmpty()) {
            return errors;
        }

        // Get stStepper object from Jaxb classes
        STStepper stStepper = getXmlStepperObjFromJaxBClasses(path);

        // Check every flow has unique name
        Validator validateEveryFlowHasUniqueName = new ValidateEveryFlowHasUniqueName(stStepper.getSTFlows());
        validateEveryFlowHasUniqueName.validate(errors);
        if(!errors.isEmpty()) {
            return errors;
        }

        // Start reading data
        for(STFlow stFlow : stStepper.getSTFlows().getSTFlow()) {
            Parser flowParser = new FlowParser(stFlow);
            errors.addAll(flowParser.parse());

            // Check if there are errors
            if(!errors.isEmpty())
                return errors;

            FlowDefinition flowDefinition = flowParser.getObject();
            flowDefinition.validateFlowStructure(errors);
            stepper.addFlowToStepper(flowDefinition);

            if(!errors.isEmpty())
                return errors;


        }
        return errors; // If the list is empty-the flows are valid.
    }

    // Reset system's objects
    public void resetSystem() {
        for(int i = 0; i < stepper.getAllFlows().size(); i ++) {
            stepper.getAllFlows().clear();
        }
    }

    public STStepper getXmlStepperObjFromJaxBClasses(String xmlPath) {
        try {
            InputStream inputStream = new FileInputStream(new File(xmlPath));
            JAXBContext jaxbContext = JAXBContext.newInstance(STStepper.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            return (STStepper) jaxbUnmarshaller.unmarshal(inputStream);

        }
        catch (JAXBException | FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
