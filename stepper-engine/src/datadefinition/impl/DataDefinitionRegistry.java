package datadefinition.impl;

import datadefinition.api.DataDefinition;
import datadefinition.impl.doublenumber.DoubleDataDefinition;
import datadefinition.impl.file.FileDataDefinition;
import datadefinition.impl.list.definition.*;
import datadefinition.impl.mapping.MappingDataDefinition;
import datadefinition.impl.number.NumberDataDefinition;
import datadefinition.impl.relation.RelationDataDefinition;
import datadefinition.impl.string.StringDataDefinition;
import exception.data.NotUserFriendlyException;
import exception.data.TypeDontMatchException;

public enum  DataDefinitionRegistry implements DataDefinition {
    STRING(new StringDataDefinition()),
    Double(new DoubleDataDefinition()),
    NUMBER(new NumberDataDefinition()),
    RELATION(new RelationDataDefinition()),
    FILE(new FileDataDefinition()),
    MAPPING(new MappingDataDefinition()),
    NUMBER_LIST(new NumberListDefinition()),
    STRING_LIST(new StringListDefinition()),
    DOUBLE_LIST(new DoubleListDefinition()),
    FILE_LIST(new FileListDefinition()),
    RELATION_LIST(new RelationListDefinition()),
    MAPPING_LIST(new MappingListDefinition())
    ;

    private final DataDefinition dataDefinition;

    DataDefinitionRegistry(DataDefinition dataDefinition) {
        this.dataDefinition = dataDefinition;
    }


    @Override
    public String getName() { return dataDefinition.getName(); }
    @Override
    public boolean isUserFriendly() { return dataDefinition.isUserFriendly(); }

    @Override
    public Class<?> getType(){ return dataDefinition.getType(); }

    @Override
    public <T> T scanInput(String data) throws NotUserFriendlyException, TypeDontMatchException {
        return dataDefinition.scanInput(data);
    }


}
