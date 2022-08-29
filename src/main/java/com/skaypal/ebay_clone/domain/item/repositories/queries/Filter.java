package com.skaypal.ebay_clone.domain.item.repositories.queries;

import java.util.List;

public class Filter {

    public Filter(){}

    private String field;
    private QueryOperator operator;
    private String value;
    private List<String> values;

    public String getField(){return this.field;}
    public QueryOperator getQueryOperator(){return this.operator;}
    public String getValue(){return this.value;}
    public List<String> getValues(){return this.values;}

    public void setField(String field){this.field = field;}
    public void setOperator(QueryOperator operator){this.operator = operator;}
    public void setValue(String value){this.value = value;}
    public void setValues(List<String> values){this.values = values;}

}
