package com.app.ola.core.api.general_es;

import com.orm.SugarRecord;

public class MuniPersistedModel extends SugarRecord {
    Integer db_id;
    String name;
    String code;
    Integer depto_id;

    public MuniPersistedModel(){
    }

    public MuniPersistedModel(Integer dbid, String name, String code, Integer deptoid){
        this.db_id = dbid;
        this.name = name;
        this.code = code;
        this.depto_id = deptoid;
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
    public Integer getDeptoID() {
        return this.depto_id;
    }
}