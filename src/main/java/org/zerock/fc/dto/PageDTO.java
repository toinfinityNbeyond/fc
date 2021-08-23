package org.zerock.fc.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data    // getter , setter 가 만들어짐
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageDTO {

    @Builder.Default
    private int page = 1;
    @Builder.Default
    private int size = 10;
    
    //객체를 만들자마자 1,10 의 값을 설정

    public int getSkip() {   //skip 이라는 애가 없어서 만들어 준 것
        //1 - 0   1페이지 -> 처음에 스킵할게 없다  (0,10) 10개의 게시글을 출력 (0*10,10) ,((2-1)*10,10),((3-1)*10,10)
        //2 - 10   2페이지  -> (10,10) 11부터 10개를 출력.
        //3 - 20  3페이지 ->  (20,10) 20부터 10개를 출력
        return (this.page-1) * size;


    }

}
