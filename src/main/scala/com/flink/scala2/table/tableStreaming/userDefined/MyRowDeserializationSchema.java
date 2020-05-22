package com.flink.scala2.table.tableStreaming.userDefined;

import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.types.Row;
import org.apache.flink.util.Preconditions;

import java.io.IOException;

public class MyRowDeserializationSchema implements DeserializationSchema<Row> {

    /** Type information describing the result type. */
    private final TypeInformation<Row> typeInfo;

    /**
     * Creates a JSON deserialization schema for the given type information.
     *
     * @param typeInfo Type information describing the result type. The field names of {@link Row}
     *                 are used to parse the JSON properties.
     */
    public MyRowDeserializationSchema(TypeInformation<Row> typeInfo) {
        Preconditions.checkNotNull(typeInfo, "Type information");
        this.typeInfo = typeInfo;

        if (!(typeInfo instanceof RowTypeInfo)) {
            throw new IllegalArgumentException("Row type information expected.");
        }
    }

    public MyRowDeserializationSchema(String mySchema){
        this(MyRowSchemaConvertor.decode(mySchema));
    }

    @Override
    public Row deserialize(byte[] message) throws IOException {
        return null;
    }

    @Override
    public boolean isEndOfStream(Row nextElement) {
        return false;
    }

    @Override
    public TypeInformation<Row> getProducedType() {
        return typeInfo;
    }
}
