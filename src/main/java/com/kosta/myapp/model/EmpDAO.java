package com.kosta.myapp.model;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;

@Repository
public class EmpDAO {

	Logger logger = LoggerFactory.getLogger(EmpDAO.class);
	
	@Autowired
	SqlSession session; 
	
	static final String NAMESPACE = "net.gasan.emp.";
	
	//1.모든직원조회
	public List<EmpVO> selectAll() {
		return session.selectList(NAMESPACE+"selectAll");
	}

	//2.조건조회(특정부서)
	public List<EmpVO> selectByDept(int deptid) {
		return session.selectList(NAMESPACE+"selectByDept", deptid);
	}
	
	//3.조건조회(특정매니저)
	public List<EmpVO> selectByManager(int mid) {
		return session.selectList(NAMESPACE+"selectByMgr", mid);
	}
	
	//4.조건조회(특정job)
	public List<EmpVO> selectByJob(String job_id) {
		return session.selectList(NAMESPACE+"selectByJob", job_id);
	}

	//6.특정직원1건조회
	public EmpVO selectById(int employee_id) {
		return session.selectOne(NAMESPACE+"selectById", employee_id);
	}
	
	//7.insert
	public int empInsert(EmpVO emp) {
		logger.info(emp+"");
		return session.insert(NAMESPACE+"empInsert", emp);
	}
	
	//8.update(employee_id=?)
	public int empUpdate(EmpVO emp) {
		return session.update(NAMESPACE+"empUpdate", emp);
	}
	
	//9.update(department_id=?)
	public int empUpdateByDept(EmpVO emp) {
		return session.update(NAMESPACE+"empUpdateByDept", emp);
	}
	
	//10.delete(employee_id=?)
	public int empDelete(int employee_id) {
		return session.delete(NAMESPACE+"empDelete", employee_id);
	}
	
	//11.delete(department_id=?)
	public int empDeleteByDept(int deptid) {
		return session.delete(NAMESPACE+"empDeleteByDept", deptid);
	}
	
	//12.모든직책조회
	public List<JobVO> selectAllJob() {
		return session.selectList(NAMESPACE+"selectAllJob");
	}
	
	//13.모든 메니저 조회
	public List<EmpVO> selectAllMgr() {
		return session.selectList(NAMESPACE+"selectAllMgr");
	}

}
