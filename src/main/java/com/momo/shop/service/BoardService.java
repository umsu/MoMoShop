package com.momo.shop.service;


import com.momo.shop.model.BoardPaging;
import com.momo.shop.model.BoardVO;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

public interface BoardService {

    /**
     * 게시물 등록
     * @param boardVO
     */
    void insertBoard(BoardVO boardVO);

    /**
     * 답변 등록
     * @param boardVO
     */
    void replyBoard(BoardVO boardVO);

    /**
     * 게시물 수정
     * @param boardVO
     */
    void updateBoard(BoardVO boardVO);

    /**
     * 게시물 삭제
     * @param qna_no
     */
    void deleteBoard(HttpServletRequest request, int qna_no);

    /**
     * 게시물 조회
     * @param qna_no
     * @return
     */
    BoardVO selectBoard(int qna_no);

    /**
     * 게시물 목록 조회
     * @return
     */
    Map<String,Object> selectBoardList(BoardPaging boardPaging);

    int passck(BoardVO boardVO);

    int idchk(BoardVO boardVO);

}
