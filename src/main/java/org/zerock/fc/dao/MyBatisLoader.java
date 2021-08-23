package org.zerock.fc.dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;

public enum MyBatisLoader { //한번만 로딩을 해두고 쓴다.그래서 enum.
    INSTANCE;

    private SqlSessionFactory sqlSessionFactory;

    MyBatisLoader() {
        try {
            String resource = "mybatis-config.xml";
            InputStream inputStream = Resources.getResourceAsStream(resource);
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    public SqlSessionFactory getFactory() {
        return this.sqlSessionFactory;
    }

}