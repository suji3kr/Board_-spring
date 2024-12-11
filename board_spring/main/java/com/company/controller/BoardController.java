package com.company.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.company.domain.BoardVO;
import com.company.service.BoardService;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;

@Controller
@Log4j
@RequestMapping("/board/*") /* 보드에 해당하는 애들만 매핑하겠다 */
@AllArgsConstructor
public class BoardController {

	private BoardService service;

	@GetMapping("/list")
	public void list(Model model) {

		log.info("list");
		model.addAttribute("list", service.getList());
	}

	@PostMapping("/register")
	public String register(BoardVO board, RedirectAttributes rttr) {

		log.info("register: " + board);

		service.register(board);

		// result 라는 키로 board.getBno() 값을 Flash 속성에 추가
		rttr.addFlashAttribute("result", board.getBno());
		// addFlashAttribute 이 데이터는 브라우저로 리다이렉트되는 동안에만 유효하며, 그 이후에는 소멸한다.

		// "/board/list"로 리다이렉트
		return "redirect:/board/list";
	}

	/*
	 * @GetMapping("/get") public void get(@RequestParam("bno")Long bno, Model
	 * model) {
	 * 
	 * log.info("/get"); model.addAttribute("board", service.get(bno)); }
	 */
	
	@GetMapping({"/get", "/modify"})
	
	public void get(@RequestParam("bno") Long bno, Model model) {
		model.addAttribute("board", service.get(bno));
	}

	@PostMapping("/modify")
	public String modify(BoardVO board, RedirectAttributes rttr) {

		log.info("modify: " + board);

		if (service.modify(board)) {
			rttr.addFlashAttribute("result", "success");
		}
		return "redirect:/board/list";
	}

	@GetMapping("/register")
	public void resister() {

	}

}
