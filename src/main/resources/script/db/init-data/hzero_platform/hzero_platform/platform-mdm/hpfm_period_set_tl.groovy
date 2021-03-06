package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_period_set_tl.groovy') {
    changeSet(author: "hzero@hand-china.com", id: "2019-03-01-hpfm_period_set_tl") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        createTable(tableName: "hpfm_period_set_tl", remarks: "会计期定义多语言") {
            column(name: "period_set_id", type: "bigint",  remarks: "会计期ID")  {constraints(nullable:"false")}  
            column(name: "lang", type: "varchar(" + 30 * weight + ")",  remarks: "语言")  {constraints(nullable:"false")}  
            column(name: "period_set_name", type: "varchar(" + 240 * weight + ")",  remarks: "会计期名称")   

        }

        addUniqueConstraint(columnNames:"period_set_id,lang",tableName:"hpfm_period_set_tl",constraintName: "hpfm_period_set_tl_u1")
    }
    changeSet(author: "hzero@hand-china.com", id: "2020-06-05-hpfm_period_set_tl") {
        addColumn(tableName: 'hpfm_period_set_tl') {
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID,hpfm_tenant.tenant_id") {
                constraints(nullable: "false")
            }
        }
    }
}