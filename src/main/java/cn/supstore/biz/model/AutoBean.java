package cn.supstore.biz.model;


import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoBean {
    class Table {
        String name;
        List<String> fieldList = new ArrayList<String>();
        List<String> typeList = new ArrayList<String>();
    }

    //final static String database = "valley";
    final static String url = "jdbc:mysql://127.0.0.1:3306/sale_db?characterEncoding=UTF-8&amp;useUnicode=true&amp;autoReconnect=true&amp;zeroDateTimeBehavior=round&amp;useOldAliasMetadataBehavior=true&amp;allowMultiQueries=true";
    final static String user = "user1", password = "123456";
    final static String targetFolder = "src/main/java/cn/supstore/biz/model";
    final static String packageName="cn.supstore.biz.model";
    final static String tab = "  ";

    final static List<Table> tables = new ArrayList<Table>();
    String tablename ="tt_config_attr";
    
    String convertType(String sqlType) {
        if (sqlType.startsWith("varchar")||sqlType.startsWith("text")) return "String";
        if (sqlType.startsWith("bigint")) return "Long";
        if (sqlType.startsWith("int")) return "Integer";
        if (sqlType.startsWith("smallint")) return "Short";
        if (sqlType.startsWith("tinyint")) return "Integer";
        if(sqlType.startsWith("double"))return "Double";
        if(sqlType.startsWith("date"))return "Date";
        if(sqlType.startsWith("time"))return "Date";
        if(sqlType.startsWith("char"))return "String";
        return null;
    }

    String convertField(String field) {
        return field;
    }
    
    String convertFieldCamel(String field){
    	
    	StringBuilder builder = new StringBuilder();
        
        for (int i = 0; i < field.length(); i++) {
            if (field.charAt(i) == '_') continue;
            if (i>0&&field.charAt(i - 1) == '_') {
                builder.append(Character.toUpperCase(field.charAt(i)));
            } else {
                builder.append(field.charAt(i));
            }
        }
//        builder.append("Po");
        return builder.toString();
    	
    }
    
    //表名中只允许字母和下划线,并且下划线不能连续两道
    String convertTableName(String name) {
        StringBuilder builder = new StringBuilder();
        if (name.charAt(0) != '_') builder.append(Character.toUpperCase(name.charAt(0)));
        for (int i = 1; i < name.length(); i++) {
            if (name.charAt(i) == '_') continue;
            if (name.charAt(i - 1) == '_') {
                builder.append(Character.toUpperCase(name.charAt(i)));
            } else {
                builder.append(name.charAt(i));
            }
        }
//        builder.append("Po");
        return builder.toString();
    }

    String uppercaseFirstLetter(String s) {
        return Character.toUpperCase(s.charAt(0)) + s.substring(1);
    }

    void writeTableToFile(Table table, PrintWriter writer) {
        StringBuilder fields = new StringBuilder();
        StringBuilder gettersAndSetters = new StringBuilder();
        for (int i = 0; i < table.fieldList.size(); i++) {
            String field = table.fieldList.get(i), type = table.typeList.get(i);
            fields.append(String.format("\tpublic \t%s %s;\n", type, field));
            String uppercase = uppercaseFirstLetter(field);
            gettersAndSetters.append(String.format("\tpublic \t%s get%s(){\n\t\treturn %s;\n\t}\n", type, uppercase, field));
            gettersAndSetters.append(String.format("\tpublic \tvoid set%s(%s %s){\n\t\t%s=%s;\n\t}\n", uppercase, type, uppercase, field, uppercase));
        }
        String ans = String.format("package %s;\n\n public class %s{\n%s\n%s\n}",packageName, table.name, fields, gettersAndSetters);
        ans = ans.replace("\t", tab);
        writer.printf(ans);
        writer.close();
    }

    void output() throws IOException {
        Path folder = Paths.get(targetFolder);
        if (Files.exists(folder) == false) {
            Files.createDirectory(folder);
        }
        for (Table i : tables) {
            Path file = folder.resolve(i.name + ".java");
            writeTableToFile(i, new PrintWriter(Files.newBufferedWriter(file)));
        }
    }

    void init() throws Exception {
        Class.forName("com.mysql.jdbc.Driver");
        Connection connection = DriverManager.getConnection(url, user, password);
        Statement showTablesStatement = connection.createStatement();
//        ResultSet showTablesResultSet = showTablesStatement.executeQuery("show tables");
//        while (showTablesResultSet.next()) {
//            Table t = new Table();
//            t.name = convertTableName(showTablesResultSet.getString(1));
//            Statement descTableStatement = connection.createStatement();
//            ResultSet descTableResult = descTableStatement.executeQuery("desc " + showTablesResultSet.getString(1));
//            while (descTableResult.next()) {
//                t.fieldList.add(convertField(descTableResult.getString(1)));
//                t.typeList.add(convertType(descTableResult.getString(2)));
//            }
//            tables.add(t);
//            descTableResult.close();
//            descTableStatement.close();
//        }	
       
		Table t = new Table();
		t.name = convertTableName(tablename);
		Statement descTableStatement = connection.createStatement();
		ResultSet descTableResult = descTableStatement.executeQuery("desc " + tablename);
		while (descTableResult.next()) {
		    t.fieldList.add(convertFieldCamel(descTableResult.getString(1)));
		    t.typeList.add(convertType(descTableResult.getString(2)));
		}
		tables.add(t);
		descTableResult.close();
		descTableStatement.close();
        showTablesStatement.close();
        connection.close();
    }

    AutoBean() throws Exception {
        init();
        output();
    }

    public static void main(String[] args) throws Exception {
        new AutoBean();
    }
}