package org.zerock.stockspring.board.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.zerock.stockspring.board.dto.BoardDTO;
import org.zerock.stockspring.board.dto.PageRequestDTO;
import org.zerock.stockspring.board.dto.PageResponseDTO;
import org.zerock.stockspring.board.service.BoardService;

@Controller
@RequestMapping("/board")
@Log4j2
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    //게시물 리스트
    @GetMapping("/list")
    public void listGet(PageRequestDTO pageRequestDTO, Model model){
        log.info("=========Board GET list========");
        PageResponseDTO<BoardDTO> responseDTO = boardService.list(pageRequestDTO);
        model.addAttribute("responseDTO",responseDTO);
    }


    //게시물 등록 GET
    @GetMapping("/register")
    public void registerGet(){
        log.info("=========Board GET Register========");
    }
    //게시물 등록 POST
    @PostMapping("/register")
    public String registerPost(BoardDTO boardDTO, RedirectAttributes redirectAttributes){
        log.info("=========Board Post Register========");
        log.info("포스팅할 게시물"+ boardDTO);
        Long bno = boardService.register(boardDTO);
        log.info("=========게시물 등록 성공========");
        redirectAttributes.addFlashAttribute("result",bno);
        return "redirect:/board/list";
    }
    //게시물 조회&수정
    @GetMapping({"/read","modify"})
    public void read(Long bno,PageRequestDTO pageRequestDTO, Model model){
        log.info("=========Board Get Read&Modify=========");
        BoardDTO boardDTO = boardService.readOne(bno);
        model.addAttribute("dto",boardDTO);
        model.addAttribute("pageRequestDTO",pageRequestDTO);
    }
    @PostMapping("/modify")
    public String modify(PageRequestDTO pageRequestDTO, @Valid BoardDTO boardDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes){
        log.info("==========Board Post Modify============");
        if(bindingResult.hasErrors()){
            log.info("Errors");
            String link = pageRequestDTO.getLink();
            redirectAttributes.addFlashAttribute("errors",bindingResult.getAllErrors());
            return "redirect:/board/modify"+link;
        }
        boardService.modify(boardDTO);

        redirectAttributes.addFlashAttribute("result","modified");
        redirectAttributes.addAttribute("bno",boardDTO.getBno());
        return "redirect:/board/read";

    }

    //게시물 삭제
    @PostMapping("/remove")
    public String remove(Long bno, RedirectAttributes redirectAttributes){
        log.info("=======Board Post Remove======");
        boardService.remove(bno);
        redirectAttributes.addFlashAttribute("result","removed");
        return "redirect:/board/list";
    }


}
