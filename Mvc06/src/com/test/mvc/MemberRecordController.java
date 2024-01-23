/*=====================================================================
 *   MemberRecordController.java
 *   - 사용자 정의 컨트롤러 클래스
 *   memberlist.action -> 요청 -> memberList() 처리 
 *      memberController 통해서 memberList.jsp -> memberinsertform.action
 *      memberRecordController -> MemberInsertForm.jsp 
 *   - 데이터 입력(회원 등록)폼 요청 관련 액션 처리
 *   - memberinsertform.action -> 요청 -> memberInsertForm() 처리
 *   - 데이터 입력(회원 등록) 액션 처리
 *   - memberinsert.action -> 요청 -> memberInsert() 처리
 *                           MemberRecordController -> redirect:memberlist.action
 =====================================================================*/

package com.test.mvc;

import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MemberRecordController
{
	@RequestMapping("/memberlist.action")
	public String MemberList(Model model) 
	{
		MemberDAO dao = new MemberDAO();
		ArrayList<MemberDTO> memberList = null;
		
		try
		{
			dao.Connection();
			memberList = dao.lists();
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally {
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		
		model.addAttribute("memberList", memberList);
		
		return "/WEB-INF/view/MemberList.jsp";
	}
	
	@RequestMapping("/memberinsertform.action")
	public String MemberInsertFrom()
	{
		return "/WEB-INF/view/MemberInsertForm.jsp";
	}
	
//	@RequestMapping("/memberinsert.action")
//	public String MemberInsert(@RequestParam String id, @RequestParam String pw
//							,  @RequestParam String name, @RequestParam String tel
//							,  @RequestParam String email) throws SQLException, ClassNotFoundException
	@RequestMapping("/memberinsert.action")
	public String MemberInsert(MemberDTO dto) throws SQLException, ClassNotFoundException
	{	// 매개변수에 request 넣어줘도 됨
		/*
		 * String id = request.getParameter("id"); String pw =
		 * request.getParameter("pw"); String name = request.getParameter("name");
		 * String tel = request.getParameter("tel"); String email =
		 * request.getParameter("email");
		 */
		MemberDAO dao = new MemberDAO();
		
		try
		{
			dao.Connection();
			dao.insertQuery(dto);
			
		} catch (Exception e)
		{
			System.out.println(e.toString());
		}
		finally {
			try
			{
				dao.close();
			} catch (Exception e)
			{
				System.out.println(e.toString());
			}
		}
		
		
		
		
		return "redirect:memberlist.action";
	}
	
}

