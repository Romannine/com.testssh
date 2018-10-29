/*import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCountCallbackHandler;

import java.sql.Driver;
import java.util.List;

public class JDBCdemo {
    public void select() {
        DruidDataSource dataSource = new DruidDataSource();
        Driver driver = new HiveDriver();
        dataSource.setUrl("jdbc:hive2://101.200.47.72:10000/default");
        dataSource.setDriver(driver);
        // dataSource.setDefaultAutoCommit(true);
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);


        String sql = "select * from demo001";
*//*        if (sql.indexOf("create")>=1 || sql.indexOf("insert")>=1 || sql.indexOf("update")>=1) {
            jdbcTemplate.setMaxRows(500);
            jdbcTemplate.setFetchSize();
            List<Map<String, Object>> rows = jdbcTemplate.queryForList(*//**//*"set hive.cli.print.header=true;"+*//**//*sql);
            List<List<String>> listList = new ArrayList<>();
            List<String> listtest = Arrays.asList(rows.get(0).keySet().toString().split(","));
            System.out.println(listtest.toString());
            for (Map map : rows) {
                List<String> list = new ArrayList<>();
                for (Object o : map.keySet()) {
                    list.add(map.get(o).toString());
                }
                listList.add(list);
            }
            Iterator<Map<String, Object>> it = rows.iterator();
            System.out.println("1." + it.next().keySet());

            System.out.println(it.hasNext());
            while (it.hasNext()) {
                Map row = it.next();
                System.out.println("2." + row.toString());
                for (Object s : row.keySet()) {
                    System.out.print(row.get(s) + "\t");
                }
                System.out.println("");
            }
        }else {*//*
*//*
        Map<String, Object> map = jdbcTemplate.queryForMap(sql);
        System.out.println(map.toString());
*//*

        sql = "select * from demo001";
        List list = null;
//        String sql = "select objid ,objname from t_stockobj";
        RowCountCallbackHandler rcch = new RowCountCallbackHandler();
        try {
            jdbcTemplate.query(sql, rcch);
            String coloumn[] = rcch.getColumnNames();
            System.out.println(coloumn.toString());
            for (int i = 0; i < coloumn.length; i++) {
                list.add(coloumn[i]);
            }
        } catch (Exception e) {
// TODO: handle exception
            e.printStackTrace();
        }
    }
    }*/
