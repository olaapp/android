package com.app.ola.core.api.general_es;

import com.orm.SugarRecord;

public class DeptosPersistesModel extends SugarRecord {
    Integer db_id;
    String name;
    String code;

    public DeptosPersistesModel(){
    }

    public DeptosPersistesModel(Integer dbid, String name, String code){
        this.db_id = dbid;
        this.name = name;
        this.code = code;
    }


    public Integer getIDDB() {
        return this.db_id;
    }
    public String getName() {
        return this.name;
    }
    public String getCode() {
        return this.code;
    }

}