package datadefinition.impl.doublenumber;

import datadefinition.api.AbstractDataDefinition;
import datadefinition.api.DataDefinition;
import exception.data.TypeDontMatchException;

public class DoubleDataDefinition extends AbstractDataDefinition {

    public DoubleDataDefinition() {
        super("Double", true, double.class);
    }

    @Override
    public Double scanInput(String data) throws TypeDontMatchException {
        try {
            return Double.parseDouble(data);
        }
        catch (NumberFormatException e) {
            throw new TypeDontMatchException(e.getMessage());
        }
    }
}
