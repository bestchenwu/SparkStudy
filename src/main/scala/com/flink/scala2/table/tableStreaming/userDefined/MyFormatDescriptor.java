package com.flink.scala2.table.tableStreaming.userDefined;

import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.table.descriptors.DescriptorProperties;
import org.apache.flink.table.descriptors.FormatDescriptor;
import org.apache.flink.table.utils.TypeStringUtils;
import org.apache.flink.types.Row;
import org.apache.flink.util.Preconditions;

import java.util.Map;

import static com.flink.scala2.table.tableStreaming.userDefined.MyValidator.FORMAT_MY_SCHEMA;
import static com.flink.scala2.table.tableStreaming.userDefined.MyValidator.FORMAT_SCHEMA;
import static com.flink.scala2.table.tableStreaming.userDefined.MyValidator.FORMAT_TYPE_VALUE;
import static org.apache.flink.table.descriptors.FormatDescriptorValidator.FORMAT_DERIVE_SCHEMA;

/**
 * 自定义flink table 外部系统的format<br/>
 * 自定义的schema是name:type,name:type的形式
 *
 * @author chenwu on 2020.5.21
 */
public class MyFormatDescriptor extends FormatDescriptor {

    private Boolean failOnMissingField;
    private Boolean deriveSchema;
    private String mySchema;
    private String schema;

    /**
     * Format descriptor for JSON.
     */
    public MyFormatDescriptor() {
        super(FORMAT_TYPE_VALUE, 1);
    }

    /**
     * Sets flag whether to fail if a field is missing or not.
     *
     * @param failOnMissingField If set to true, the operation fails if there is a missing field.
     *                           If set to false, a missing field is set to null.
     */
    public MyFormatDescriptor failOnMissingField(boolean failOnMissingField) {
        this.failOnMissingField = failOnMissingField;
        return this;
    }

    /**
     * Sets the JSON schema string with field names and the types according to the JSON schema
     * specification [[http://json-schema.org/specification.html]].
     *
     * <p>The schema might be nested.
     *
     * @param mySchema JSON schema
     */
    public MyFormatDescriptor mySchema(String mySchema) {
        Preconditions.checkNotNull(mySchema);
        this.mySchema = mySchema;
        this.schema = null;
        this.deriveSchema = null;
        return this;
    }

    /**
     * Sets the schema using type information.
     *
     * <p>JSON objects are represented as ROW types.
     *
     * <p>The schema might be nested.
     *
     * @param schemaType type information that describes the schema
     */
    public MyFormatDescriptor schema(TypeInformation<Row> schemaType) {
        Preconditions.checkNotNull(schemaType);
        this.schema = TypeStringUtils.writeTypeInfo(schemaType);
        this.mySchema = null;
        this.deriveSchema = null;
        return this;
    }

    /**
     * Derives the format schema from the table's schema described.
     *
     * <p>This allows for defining schema information only once.
     *
     * <p>The names, types, and fields' order of the format are determined by the table's
     * schema. Time attributes are ignored if their origin is not a field. A "from" definition
     * is interpreted as a field renaming in the format.
     */
    public MyFormatDescriptor deriveSchema() {
        this.deriveSchema = true;
        this.schema = null;
        this.mySchema = null;
        return this;
    }

    @Override
    protected Map<String, String> toFormatProperties() {
        final DescriptorProperties properties = new DescriptorProperties();

        if (deriveSchema != null) {
            properties.putBoolean(FORMAT_DERIVE_SCHEMA, deriveSchema);
        }

        if (mySchema != null) {
            properties.putString(FORMAT_MY_SCHEMA, mySchema);
        }

        if (schema != null) {
            properties.putString(FORMAT_SCHEMA, schema);
        }

        if (failOnMissingField != null) {
            properties.putBoolean(MyValidator.FORMAT_FAIL_ON_MISSING_FIELD, failOnMissingField);
        }

        return properties.asMap();
    }
}
