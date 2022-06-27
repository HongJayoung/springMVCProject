package org.kosta.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.kosta.dto.BoardVO;
import com.kosta.myapp.model.BoardService;

@Controller
@RequestMapping("/board")
public class BoardController {
	
	@Autowired
	BoardService bservice;
	
	Logger logger = LoggerFactory.getLogger(BoardController.class);
	
	//목록보기
	@RequestMapping(value = "/boardList.do")
	public void boardList(Model model, HttpServletRequest request) {
		Map<String, ?> flashMap = RequestContextUtils.getInputFlashMap(request);
		
		if(flashMap !=null) {
			String resultMsg = (String)flashMap.get("resultMsg");
			model.addAttribute("resultMsg", resultMsg);
		}
		
		model.addAttribute("boardDatas", bservice.selectAll());
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
	public String boardInsert(BoardVO board, RedirectAttributes redirectAttr, HttpServletRequest request) {
		MultipartFile uploadfile = board.getPhotos(); 
		
		if (uploadfile == null) return "redirect:/board/boardList.do"; 
		String path = request.getSession().getServletContext().getRealPath("/resources/uploads"); 
		String fileName = uploadfile.getOriginalFilename(); 
		String fpath = path +"\\" + fileName ;
		board.setPic(fileName); 
		
		try {
			File file = new File(fpath); 
			uploadfile.transferTo(file); 
		} catch (IOException e) {
			e.printStackTrace(); 
		} 

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
	
	//title로 조회
	@RequestMapping("/titleSearch.do")
	public String titleSearch(String title, Model model) {
		List<BoardVO> blist = bservice.selectByTitle("%"+title+"%");
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}
	
	//writer로 조회
	@RequestMapping("/writerSearch.do")
	public String writerSearch(Integer writer, Model model) {
		List<BoardVO> blist = bservice.selectByWriter(writer);
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}
	
	//date로 조회
	@RequestMapping("/dateSearch.do")
	public String dateSearch(Date sdate, Date edate , Model model) {
		List<BoardVO> blist = bservice.selectByRegdate(sdate, edate);
		model.addAttribute("boardDatas", blist);
		return "board/boardListFrame";
	}
	
}
