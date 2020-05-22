package com.flink.scala2.table.tableStreaming.userDefined;

import org.apache.flink.table.api.ValidationException;
import org.apache.flink.table.descriptors.DescriptorProperties;
import org.apache.flink.table.descriptors.FormatDescriptorValidator;

public class MyValidator extends FormatDescriptorValidator {

    public static final String FORMAT_TYPE_VALUE = "my";
    public static final String FORMAT_SCHEMA = "format.schema";
    public static final String FORMAT_MY_SCHEMA = "format.my-schema";
    public static final String FORMAT_FAIL_ON_MISSING_FIELD = "format.fail-on-missing-field";

    @Override
    public void validate(DescriptorProperties properties) {
        super.validate(properties);
        properties.validateBoolean(FORMAT_DERIVE_SCHEMA, true);
        final boolean deriveSchema = properties.getOptionalBoolean(FORMAT_DERIVE_SCHEMA).orElse(false);
        final boolean hasSchema = properties.containsKey(FORMAT_SCHEMA);
        final boolean hasSchemaString = properties.containsKey(FORMAT_MY_SCHEMA);
        if (deriveSchema && (hasSchema || hasSchemaString)) {
            throw new ValidationException(
                    "Format cannot define a schema and derive from the table's schema at the same time.");
        } else if (!deriveSchema && hasSchema && hasSchemaString) {
            throw new ValidationException("A definition of both a schema and my schema is not allowed.");
        } else if (!deriveSchema && !hasSchema && !hasSchemaString) {
            throw new ValidationException("A definition of a schema or my schema is required.");
        } else if (hasSchema) {
            properties.validateType(FORMAT_SCHEMA, false, true);
        } else if (hasSchemaString) {
            properties.validateString(FORMAT_MY_SCHEMA, false, 1);
        }

        properties.validateBoolean(FORMAT_FAIL_ON_MISSING_FIELD, true);
    }
}
