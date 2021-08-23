package org.zerock.fc.dao;

import lombok.extern.log4j.Log4j2;
import org.apache.ibatis.session.SqlSession;

@Log4j2
public enum TimeDAO {
    INSTANCE;

    public String getTime() throws  RuntimeException {  //익명 클래스는 게 아니라 stringbuffer 필요 없다.
        String result = null;

        try (SqlSession session = MyBatisLoader.INSTANCE.getFactory().openSession()) {
            String timeStr = session.selectOne("org.zerock.bitboard.dao.TimeMapper.getTime");
            log.info("========================");
            log.info(result);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return result;
    }

}
