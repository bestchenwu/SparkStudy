package com.flink.scala2.table.tableStreaming.userDefined;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.table.descriptors.DescriptorProperties;
import org.apache.flink.table.factories.DeserializationSchemaFactory;
import org.apache.flink.table.factories.SerializationSchemaFactory;
import org.apache.flink.table.factories.TableFormatFactoryBase;
import org.apache.flink.types.Row;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class MyRowFormatFactory extends TableFormatFactoryBase<Row>
        implements SerializationSchemaFactory<Row>, DeserializationSchemaFactory<Row> {

    public MyRowFormatFactory() {
        super(MyValidator.FORMAT_TYPE_VALUE, 1, true);
    }

    @Override
    protected List<String> supportedFormatProperties() {
        final List<String> properties = new ArrayList<>();
        properties.add(MyValidator.FORMAT_MY_SCHEMA);
        properties.add(MyValidator.FORMAT_SCHEMA);
        properties.add(MyValidator.FORMAT_FAIL_ON_MISSING_FIELD);
        return properties;
    }

    @Override
    public DeserializationSchema<Row> createDeserializationSchema(Map<String, String> properties) {
        final DescriptorProperties descriptorProperties = getValidatedProperties(properties);

        // create and configure
        final MyRowDeserializationSchema schema = new MyRowDeserializationSchema(createTypeInformation(descriptorProperties));

//        descriptorProperties.getOptionalBoolean(MyValidator.FORMAT_FAIL_ON_MISSING_FIELD)
//                .ifPresent(schema::setFailOnMissingField);

        return schema;
    }

    private static DescriptorProperties getValidatedProperties(Map<String, String> propertiesMap) {
        final DescriptorProperties descriptorProperties = new DescriptorProperties();
        descriptorProperties.putProperties(propertiesMap);

        // validate
        new MyValidator().validate(descriptorProperties);

        return descriptorProperties;
    }

    private TypeInformation<Row> createTypeInformation(DescriptorProperties descriptorProperties) {
        if (descriptorProperties.containsKey(MyValidator.FORMAT_SCHEMA)) {
            return (RowTypeInfo) descriptorProperties.getType(MyValidator.FORMAT_SCHEMA);
        } else if (descriptorProperties.containsKey(MyValidator.FORMAT_MY_SCHEMA)) {
            return MyRowSchemaConvertor.decode(descriptorProperties.getString(MyValidator.FORMAT_MY_SCHEMA));
        } else {
            return deriveSchema(descriptorProperties.asMap()).toRowType();
        }
    }

    @Override
    public SerializationSchema<Row> createSerializationSchema(Map<String, String> properties) {
        return null;
    }

}




