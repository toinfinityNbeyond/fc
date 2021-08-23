package org.zerock.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AttachDTO {

    private Integer bno;
    private  String fname, savename;
    private  boolean imgyn;  // 기본으로 false

}
