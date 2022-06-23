package org.kosta.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.dto.BoardVO;
import com.kosta.myapp.model.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService bservice;
	
	//목록보기
	@RequestMapping(value = "/boardList.do")
	public void boardList(Model model, HttpServletRequest request) {
		List<BoardVO> blist = bservice.selectAll();
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		String resultMsg = null;
		if(flashMap !=null) {
			resultMsg = (String)flashMap.get("resultMsg");
			model.addAttribute("resultMsg", resultMsg);
		}
		
		model.addAttribute("boardDatas", blist);
	}
	
	//상세보기
	@RequestMapping(value = "/boardDetail.do", method = RequestMethod.GET)
	public void boardDetail(int bno, Model model) {
		model.addAttribute("board", bservice.selectByBno(bno));
	}
	
	//수정
	@RequestMapping(value = "/boardUpdate.do", method = RequestMethod.POST)
	public String boardUpdate(BoardVO board, RedirectAttributes redirectAttr) {
		int result = bservice.updateBoard(board);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 수정");
		
		return "redirect:/board/boardList.do";
	}
	
	//등록화면
	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.GET)
	public void boardInsertPage() {

	}
	
	//등록하기
	@RequestMapping(value = "/boardInsert.do", method = RequestMethod.POST)
	public String boardInsert(BoardVO board, RedirectAttributes redirectAttr) {
		int result = bservice.newBoard(board);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 입력");
		
		return "redirect:/board/boardList.do";
	}
	
	//삭제
	@RequestMapping(value = "/boardDelete.do")
	public String boardDelete(int bno, RedirectAttributes redirectAttr) {
		int result = bservice.deleteBoard(bno);
		redirectAttr.addFlashAttribute("resultMsg", result+"건 삭제");
		
		return "redirect:/board/boardList.do";
	}
}
