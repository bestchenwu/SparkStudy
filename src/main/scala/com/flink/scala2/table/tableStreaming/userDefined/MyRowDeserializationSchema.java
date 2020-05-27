package com.flink.scala2.table.tableStreaming.userDefined;

import com.spark.constants.SymbolConstants;
import org.apache.flink.api.common.serialization.DeserializationSchema;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.api.java.typeutils.RowTypeInfo;
import org.apache.flink.types.Row;
import org.apache.flink.util.Preconditions;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class MyRowDeserializationSchema implements DeserializationSchema<Row> {

    /** Type information describing the result type. */
    private final TypeInformation<Row> typeInfo;
    private  TypeInformation<?>[] fieldTypes;

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
        RowTypeInfo rowTypeInfo = (RowTypeInfo)typeInfo;
        this.fieldTypes = rowTypeInfo.getFieldTypes();
    }

    public MyRowDeserializationSchema(String mySchema){
        this(MyRowSchemaConvertor.decode(mySchema));
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  MyRowDeserializationSchema)){
            return false;
        }
        if(obj == this){
            return true;
        }
        MyRowDeserializationSchema otherSchema = (MyRowDeserializationSchema)obj;
        return this.typeInfo.equals(otherSchema.typeInfo);
    }

    @Override
    public int hashCode() {
        return typeInfo.hashCode();
    }

    @Override
    public Row deserialize(byte[] message) throws IOException {
        String messageBytes = new String(message);
        String[] splitArray = messageBytes.split(SymbolConstants.SYMBOL_DH);
        if(splitArray==null||splitArray.length<0){
            return null;
        }
        Object[] objects = new Object[splitArray.length];
        for(int i = 0;i<splitArray.length;i++){
            TypeInformation<?> fieldType = fieldTypes[i];
            if(fieldType.getClass().equals(Types.INT.getClass())){
                objects[i] = Integer.parseInt(splitArray[i]);
            }else{
                objects[i] = splitArray[i];
            }
        }
        return Row.of(objects);
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
