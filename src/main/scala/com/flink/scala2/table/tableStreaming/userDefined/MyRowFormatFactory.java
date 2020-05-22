package com.flink.scala2.table.tableStreaming.userDefined;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.serialization.SerializationSchema;
import org.apache.flink.table.factories.DeserializationSchemaFactory;
import org.apache.flink.table.factories.SerializationSchemaFactory;
import org.apache.flink.table.factories.TableFormatFactoryBase;
import org.apache.flink.types.Row;

import java.util.Map;

public class MyRowFormatFactory extends TableFormatFactoryBase<Row>
        implements SerializationSchemaFactory<Row>, DeserializationSchemaFactory<Row> {

    public MyRowFormatFactory(String type, int version, boolean supportsSchemaDerivation) {
        super(type, version, supportsSchemaDerivation);
    }

    @Override
    public DeserializationSchema<Row> createDeserializationSchema(Map<String, String> properties) {
        return null;
    }

    @Override
    public SerializationSchema<Row> createSerializationSchema(Map<String, String> properties) {
        return null;
    }

}




