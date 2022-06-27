package com.kosta.myapp.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.DeptDTO;

@Service
public class DeptService {
	
	@Autowired
	DeptDAOMybatis deptDAO;
	//private DeptDAO deptDAO;
	
	//1.모두조회
	public List<DeptDTO> selectAll() {
		return deptDAO.selectAll();
	}
	
	//2.부서코드로 조회
	public DeptDTO selectById(int deptid) {
		return deptDAO.selectById(deptid);
	}
	
	//3.지역코드로 조회
	public List<DeptDTO> selectByLocation(int locid) {
		return deptDAO.selectByLocation(locid);
	}
	
	//4.신규부서입력
	public int deptInsert(DeptDTO dept) {
		return deptDAO.deptInsert(dept);
	}
	
	//5.특정부서수정
	public int deptUpdate(DeptDTO dept) {
		return deptDAO.deptUpdate(dept);
	}
	
	//6.특정부서삭제
	public int deptDelete(int deptid) {
		return deptDAO.deptDelete(deptid);
	}
}
