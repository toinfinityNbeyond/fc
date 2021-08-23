package org.zerock.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {

    private Integer bno;
    private String title,content,writer;
    private int viewcount;
    private Timestamp regdate,updatedate;

    private List<AttachDTO> attachDTOList;  //null . 게시글 한개에 첨부파일을 여러개 넣을 수 있게 선언.

    public void addAttach(AttachDTO attachDTO) {
        if (attachDTOList == null) {
            attachDTOList = new ArrayList<>();
        }
        attachDTOList.add(attachDTO);
    }    //
}
