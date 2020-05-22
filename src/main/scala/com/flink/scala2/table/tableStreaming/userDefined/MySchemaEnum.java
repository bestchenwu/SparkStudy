package com.flink.scala2.table.tableStreaming.userDefined;

import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;

/**
 * 自定义的Table schema支持的类型
 *
 * @author chenwu on 2020.5.21
 */
public enum MySchemaEnum {

    STRING(Types.STRING),
    INT(Types.INT),
    LONG(Types.LONG),
    FLOAT(Types.FLOAT),
    DOUBLE(Types.DOUBLE);

    private TypeInformation typeInformation;

    private MySchemaEnum(TypeInformation typeInformation) {
        this.typeInformation = typeInformation;
    }

    public TypeInformation getTypeInformation() {
        return typeInformation;
    }

    public static MySchemaEnum parseTypeString(String type) {
        if (StringUtils.isBlank(type)) {
            return null;
        }
        for (MySchemaEnum mySchemaEnum : MySchemaEnum.values()) {
            if (mySchemaEnum.name().equalsIgnoreCase(type)) {
                return mySchemaEnum;
            }
        }
        return null;
    }

}
