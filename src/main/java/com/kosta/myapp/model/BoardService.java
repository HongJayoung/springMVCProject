package com.kosta.myapp.model;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.BoardEmpVO;
import com.kosta.dto.BoardVO;

@Service
public class BoardService {
	
	@Autowired
	BoardDAOMybatis boardDAO;
	
	public List<BoardEmpVO> selectAllJoin() {
		return boardDAO.selectAllJoin();
	}
	
	//1.���Խñ���ȸ
	public List<BoardVO> selectAll() {
		return boardDAO.selectAll();
	}
	
	//2.�ۼ��ڷ� ��ȸ
	public List<BoardVO> selectByWriter(Integer writer) {
		return boardDAO.selectByWriter(writer);
	}
	
	//3.�Խù���ȣ����ȸ
	public BoardVO selectByBno(int bno) {
		return boardDAO.selectByBno(bno);
	}
	
	//4.�������� ��ȸ
	public List<BoardVO> selectByTitle(String title) {
		return boardDAO.selectByTitle(title);
	}
	
	//5.��¥�� ��ȸ
	public List<BoardVO> selectByRegdate(Date sdate, Date edate) {
		return boardDAO.selectByRegdate(sdate, edate);
	}
	
	//6.���ο�Խñ�
	public int newBoard(BoardVO board) {
		return boardDAO.newBoard(board);
	}
	
	//7.�Խñۼ���
	public int updateBoard(BoardVO board) {
		return boardDAO.updateBoard(board);
	}
	
	//8.�Խñۻ���
	public int deleteBoard(int bno) {
		return boardDAO.deleteBoard(bno);
	}

}
