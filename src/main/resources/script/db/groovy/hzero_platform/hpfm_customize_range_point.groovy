package script.db

databaseChangeLog(logicalFilePath: 'script/db/hpfm_customize_range_point.groovy') {
    changeSet(author: "jiangzhou.bo@hand-china.com", id: "2019-07-25-hpfm_customize_range_point") {
        def weight = 1
        if(helper.isSqlServer()){
            weight = 2
        } else if(helper.isOracle()){
            weight = 3
        }
        if(helper.dbType().isSupportSequence()){
            createSequence(sequenceName: 'hpfm_customize_range_point_s', startValue:"1")
        }
        createTable(tableName: "hpfm_customize_range_point", remarks: "API个性化规则切入点关系") {
            column(name: "range_point_id", type: "bigint", autoIncrement: true ,   remarks: "表ID，主键，供其他表做外键")  {constraints(primaryKey: true)} 
            column(name: "range_id", type: "bigint",  remarks: "范围ID")  {constraints(nullable:"false")}  
            column(name: "service_name", type: "varchar(" + 60 * weight + ")",  remarks: "服务名")  {constraints(nullable:"false")}  
            column(name: "package_name", type: "varchar(" + 240 * weight + ")",  remarks: "包名")  {constraints(nullable:"false")}  
            column(name: "class_name", type: "varchar(" + 180 * weight + ")",  remarks: "类名")   
            column(name: "method_name", type: "varchar(" + 180 * weight + ")",  remarks: "方法名")   
            column(name: "method_args", type: "longtext",  remarks: "方法参数列表")   
            column(name: "object_version_number", type: "bigint",   defaultValue:"1",   remarks: "行版本号，用来处理锁")  {constraints(nullable:"false")}  
            column(name: "creation_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "created_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_updated_by", type: "bigint",   defaultValue:"-1",   remarks: "")  {constraints(nullable:"false")}  
            column(name: "last_update_date", type: "datetime",   defaultValueComputed:"CURRENT_TIMESTAMP",   remarks: "")  {constraints(nullable:"false")}  

        }
   createIndex(tableName: "hpfm_customize_range_point", indexName: "hpfm_customize_range_point_n1") {
            column(name: "range_id")
        }

    }
    changeSet(author: "hzero@hand-china.com", id: "2020-06-11-hpfm_customize_range_point") {
        addColumn(tableName: 'hpfm_customize_range_point') {
            column(name: "tenant_id", type: "bigint", defaultValue: "0", remarks: "租户ID,hpfm_tenant.tenant_id") {
                constraints(nullable: "false")
            }
        }
    }
}