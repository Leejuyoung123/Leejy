package org.edu.service;

import java.util.List;

import javax.inject.Inject;
import org.edu.dao.IF_MemberDAO;
import org.edu.vo.MemberVO;
import org.springframework.stereotype.Service;

@Service
public class MemberServiceImpl implements IF_MemberService{
	@Inject
	private IF_MemberDAO sampleDAO;

	@Override
	public void insertMember(MemberVO memberVO) throws Exception {
		sampleDAO.insertMember(memberVO);
	}

	@Override
	public List<MemberVO> selectMember() throws Exception {

		return sampleDAO.selectMember(); 
	}

	@Override
	public void updateMember(MemberVO memberVO) throws Exception {
		sampleDAO.updateMember(memberVO);
	}

	@Override
	public void deleteMember(String user_id) throws Exception {
		sampleDAO.deleteMember(user_id);
	}	
}