package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.BoardEmpVO;
import com.kosta.dto.BoardVO;
import com.kosta.util.DBUtil;

@Repository
public class BoardDAOMybatis {

	Logger logger = LoggerFactory.getLogger(BoardDAOMybatis.class);
	
	@Autowired
	SqlSession session; 
	
	static final String NAMESPACE = "net.gasan.board.";
	
	//join
	public List<BoardEmpVO> selectAllJoin() {
		logger.info("[mybatis] selectEmp");
		return session.selectList(NAMESPACE + "selectEmp");
	}
	
	//1.���Խñ���ȸ
	public List<BoardVO> selectAll() {
		logger.info("[mybatis] selectAll");
		return session.selectList(NAMESPACE + "selectAll");
	}

	//2.�ۼ��ڷ� ��ȸ
	public List<BoardVO> selectByWriter(Integer writer) {
		logger.info("[mybatis] selectByWriter");
		return session.selectList(NAMESPACE + "selectByWriter", writer);
	}

	//3.�Խù���ȣ����ȸ
	public BoardVO selectByBno(int bno) {
		logger.info("[mybatis] selectByBno");
		return session.selectOne(NAMESPACE + "selectByBno", bno);
	}
	
	//4.�������� ��ȸ
	public List<BoardVO> selectByTitle(String title) {
		logger.info("[mybatis] selectByTitle");
		return session.selectList(NAMESPACE + "selectByTitle", title);
	}
	
	//5.��¥�� ��ȸ
	public List<BoardVO> selectByRegdate(Date sdate, Date edate) {
		Map<String, Date> dateMap = new HashMap<>();
		dateMap.put("sdate", sdate);
		dateMap.put("edate", edate);
		
		logger.info("[mybatis] selectByRegdate " + dateMap);
		return session.selectList(NAMESPACE + "selectByRegdate", dateMap);
	}

	//6.���ο�Խñ�
	public int newBoard(BoardVO board) {
		logger.info("[mybatis] boardInsert");
		return session.insert(NAMESPACE + "boardInsert", board);
	}

	//7.�Խñۼ���
	public int updateBoard(BoardVO board) {
		logger.info("[mybatis] boardUpdate");
		return session.update(NAMESPACE + "boardUpdate", board);
	}

	//8.�Խñۻ���
	public int deleteBoard(int bno) {
		logger.info("[mybatis] boardDelete");
		return session.delete(NAMESPACE + "boardDelete", bno);
	}

}
