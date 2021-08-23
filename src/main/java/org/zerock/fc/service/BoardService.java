package org.zerock.fc.service;

import lombok.extern.log4j.Log4j2;
import org.zerock.fc.dao.BoardDAO;
import org.zerock.fc.dto.BoardDTO;
import org.zerock.fc.dto.PageDTO;

import java.util.List;

@Log4j2
public enum BoardService {

    INSTANCE;

    public Integer register(BoardDTO boardDTO) throws RuntimeException{

        return BoardDAO.INSTANCE.insert(boardDTO);

    }

    public List<BoardDTO> getList(PageDTO pageDTO)throws RuntimeException{ //PageDTO 가 파라미터가 되는 이유가 페이지번호와 사이즈를 같이 가지고 있기 때문에.

        log.info("BoardService getList....................");
        log.info(pageDTO);

        return BoardDAO.INSTANCE.list(pageDTO);
    }

    public BoardDTO read(Integer bno) throws RuntimeException {

        log.info("BoardService read.................." + bno);

        return BoardDAO.INSTANCE.select(bno);

    }

    public void remove(Integer bno) throws RuntimeException {

        log.info("BoardService remove................" + bno);

        BoardDAO.INSTANCE.delete(bno);

    }

    public void modify(BoardDTO boardDTO) throws RuntimeException {

        log.info("BoardService modify.................." );
//
//        BoardDAO.INSTANCE.update(bno);

    }


}
