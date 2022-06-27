package com.kosta.myapp.model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.BoardEmpVO;
import com.kosta.dto.BoardVO;
import com.kosta.util.DBUtil;

@Repository
public class BoardDAO {
	static final String SQL_SELECT = "select * from tbl_board order by 1 desc";
	static final String SQL_SELECT_BY_WRITER = "select * from tbl_board where writer = ? order by 1 desc";
	static final String SQL_SELECT_BY_BNO = "select * from tbl_board where bno = ?";
	static final String SQL_SELECT_BY_TITLE = "select * from tbl_board where title like ? order by 1 desc";
	static final String SQL_SELECT_BY_REGDATE = "select * from tbl_board where regdate between ? and ? order by 1 desc";
	static final String SQL_INSERT = "insert into tbl_board values(seq_bno.nextval, ?, ?, ?, sysdate, sysdate, ?)";
	static final String SQL_UPDATE = "update tbl_board set title = ?, content = ?, updatedate = sysdate where bno = ?";
	static final String SQL_DELETE = "delete from tbl_board where bno = ?";
	static final String SQL_BOARD_EMP = "SELECT b.bno, b.title, b.CONTENT, e.FIRST_NAME||' '|| e.LAST_NAME name"
										+ " FROM tbl_board b JOIN employees e ON (b.WRITER = e.EMPLOYEE_ID) order by 1 desc";
	
	@Autowired
	DataSource ds;
	
	Connection conn;
	PreparedStatement pst; 
	ResultSet rs;
	int result;
	
	//join
	public List<BoardEmpVO> selectAllJoin() {
		List<BoardEmpVO> boardlist = new ArrayList<>();
		BoardEmpVO board = null;

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_BOARD_EMP);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				board = new BoardEmpVO(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				boardlist.add(board);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn); 
		}
	
		return boardlist;
	}
	
	//1.���Խñ���ȸ
	public List<BoardVO> selectAll() {
		List<BoardVO> boardlist = new ArrayList<>();

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
	
		return boardlist;
	}

	//2.�ۼ��ڷ� ��ȸ
	public List<BoardVO> selectByWriter(int writer) {
		List<BoardVO> boardlist = new ArrayList<>();

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BY_WRITER);
			pst.setInt(1, writer);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
	
		return boardlist;
	}

	//3.�Խù���ȣ����ȸ
	public BoardVO selectByBno(int bno) {
		BoardVO board = null;

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BY_BNO);
			pst.setInt(1, bno);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				board = makeBoard(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
	
		return board;
	}
	
	//4.�������� ��ȸ
	public List<BoardVO> selectByTitle(String title) {
		List<BoardVO> boardlist = new ArrayList<>();

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BY_TITLE);
			pst.setString(1, "%"+title+"%");
			rs = pst.executeQuery();
			
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return boardlist;
	}
	
	//5.��¥�� ��ȸ
	public List<BoardVO> selectByRegdate(Date sdate, Date edate) {
		List<BoardVO> boardlist = new ArrayList<>();

		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_SELECT_BY_REGDATE);
			pst.setDate(1, sdate);
			pst.setDate(2, edate);
			rs = pst.executeQuery();
			
			while(rs.next()) {
				boardlist.add(makeBoard(rs));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return boardlist;
	}

	//6.���ο�Խñ�
	public int newBoard(BoardVO board) {
		
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_INSERT);
			pst.setString(1, board.getTitle());
			pst.setString(2, board.getContent());
			pst.setInt(3, board.getWriter());
			pst.setString(4, board.getPic());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return result;
	}

	//7.�Խñۼ���
	public int updateBoard(BoardVO board) {
		
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_UPDATE);
			pst.setString(1, board.getTitle());
			pst.setString(2, board.getContent());
			pst.setInt(3, board.getBno());
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return result;
	}

	//8.�Խñۻ���
	public int deleteBoard(int bno) {
		
		try {
			conn = ds.getConnection();
			pst = conn.prepareStatement(SQL_DELETE);
			pst.setInt(1, bno);
			result = pst.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			DBUtil.dbClose(rs, pst, conn);
		}
		
		return result;
	}

	private BoardVO makeBoard(ResultSet rs) throws SQLException {
		BoardVO board = new BoardVO();
		board.setBno(rs.getInt(1));
		board.setTitle(rs.getString(2));
		board.setContent(rs.getString(3));
		board.setWriter(rs.getInt(4));
		board.setRegdate(rs.getDate(5));
		board.setUpdatedate(rs.getDate(6));
		board.setPic(rs.getString(7));
		
		return board;
	}
}
