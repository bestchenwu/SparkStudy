package com.flink.scala2.table.tableStreaming.userDefined;

import com.spark.constants.SymbolConstants;
import org.apache.commons.lang.StringUtils;
import org.apache.flink.api.common.typeinfo.TypeInformation;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.types.Row;

import java.util.ArrayList;
import java.util.List;

/**
 * 自定义的schema是name:type,name:type的形式
 *
 * @author chenwu on 2020.5.21
 */
public class MyRowSchemaConvertor {

    /**
     * 根据schema获取{@link TypeInformation}
     *
     * @param schema
     * @return {@link TypeInformation}
     * @author chenwu on 2020.5.21
     */
    public static TypeInformation<Row> decode(String schema){
        if(StringUtils.isBlank(schema)){
            throw new IllegalStateException("MySchema schema can't be null");
        }
        String[] splitArray = schema.split(SymbolConstants.SYMBOL_DH);
        String[] typeNames = new String[splitArray.length];
        TypeInformation[] typeInformations = new TypeInformation[splitArray.length];
        int index = 0;
        for(String splitItem:splitArray){
            String[] types = splitItem.split(SymbolConstants.SYMBOL_MH);
            if(types==null || splitItem.length()<2){
                throw new IllegalArgumentException("splitItem:"+splitItem+" is not valid");
            }
            MySchemaEnum mySchema = MySchemaEnum.parseTypeString(types[1]);
            if(mySchema==null){
                throw new IllegalArgumentException("can't support:"+types[1]);
            }
            typeNames[index] = types[0];
            typeInformations[index] = mySchema.getTypeInformation();
            index++;
        }
        TypeInformation<Row> rowTypeInformation = Types.ROW_NAMED(typeNames, typeInformations);
        return rowTypeInformation;
    }
}
