package com.company.service;

import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.company.domain.BoardVO;

import lombok.extern.log4j.Log4j;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")
@Log4j
public class BoardServiceTests {
	
	@Autowired
	private BoardService service;
	
	@Test
	public void testExist() {
		log.info(service);
		assertNotNull(service); 
	}
	@Test
	public void testRegister() {
		
		BoardVO board = new BoardVO();
		board.setTitle("새로 작성하는 글");
		board.setContent("새로 작성하는 내용");
		board.setWriter("newbie");
		
		service.register(board);
		
		log.info("생성된 게시물의 번호: "+ board.getBno());
		/* DB로 입력 확인해 보기 */
	}
	
	@Test
	public void testGetList() {
		
		service.getList().forEach(board -> log.info(board));		
	}
	
	@Test
	public void testGet() {
		
		log.info(service.get(1L));		
	}
	
	@Test
	public void testUpdate() {
		
		BoardVO board = service.get(2L);
		if(board == null) {
			return;
		}
		
		board.setTitle("제목 수정합니다~" );
		log.info("MODIFY RESULT: " + service.modify(board));
	}
	
	@Test
	public void testDelete() {
		//게시물 존재여부 확인하기 
		log.info("REMOVE RESULT: " + service.remove(5L));		
	}	
}
