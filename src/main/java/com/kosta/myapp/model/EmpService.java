package com.kosta.myapp.model;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kosta.dto.EmpVO;
import com.kosta.dto.JobVO;

@Service
public class EmpService {
	
	@Autowired
	EmpDAO empDAO;
	
	//1.���������ȸ
	public List<EmpVO> selectAll() {
		return empDAO.selectAll();
	}
	
	//2.������ȸ(Ư���μ�)
	public List<EmpVO> selectByDept(int deptid) {
		return empDAO.selectByDept(deptid);
	}
	
	//3.������ȸ(Ư���Ŵ���)
	public List<EmpVO> selectByManager(int mid) {
		return empDAO.selectByManager(mid);
	}
	
	//4.������ȸ(Ư��job)
	public List<EmpVO> selectByJob(String job_id) {
		return empDAO.selectByJob(job_id);
	}

	//5.조건조회(특정department_id, job_id, salary>=, hire_date>=)
	public List<EmpVO> selectByCondition(int deptid, String job_id, double sal, String hire_hate) {
		return empDAO.selectByCondition(deptid, job_id, sal, hire_hate);
	}
	//6.Ư������1����ȸ
	public EmpVO selectById(int employee_id) {
		return empDAO.selectById(employee_id);
	}
	
	//7.insert
	public int empInsert(EmpVO emp) {
		return empDAO.empInsert(emp);
	}
	
	//8.update(Ư������1�� employee_id=?)
	public int empUpdate(EmpVO emp) {
		return empDAO.empUpdate(emp);
	}
	
	//10.delete(Ư������1�� employee_id=?)
	public int empDelete(int employee_id) {
		return empDAO.empDelete(employee_id);
	}
	
	//11.delete(���� department_id=?)
	public int empDeleteByDept(int deptid) {
		return empDAO.empDeleteByDept(deptid);
	}
	
	//12.�����å��ȸ
	public List<JobVO> selectAllJob() {
		return empDAO.selectAllJob();
	}

	//13.모든 메니저 조회
	public List<EmpVO> selectAllMgr() {
		return empDAO.selectAllMgr();
	}
	
	//14. 이메일 중복체크
	public int emailDup(String email) {
		return empDAO.emailDup(email);
	}
}
