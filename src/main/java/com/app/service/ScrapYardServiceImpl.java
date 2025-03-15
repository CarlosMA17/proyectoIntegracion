package com.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.app.dtos.bookdto.ScrapYardPartsResponseDto;
import com.app.dtos.bookdto.ScrapYardRequestDto;
import com.app.entity.ScrapYard;
import com.app.entity.ScrapYardParts;
import com.app.exception.ResourceNotFoundException;
import com.app.mappers.scrapyard.ScrapYardMapper;
import com.app.repository.ScrapYardRepository;
import com.app.repository.WriterRepository;

@Service
public class ScrapYardServiceImpl implements ScrapYardService {
	
	private final static String BOOK_NOT_FOUND = "Book with id %d not found";
	private final static String WRITER_NOT_FOUND = "Writer with id %d not found";
	
	@Autowired ScrapYardRepository scrapYardRepository;
	@Autowired WriterRepository writerRepository;
	@Autowired ScrapYardMapper scrapYardMapper;

	/*@Override
	public ScrapYardResponseDto createBook(scrapYardRequestDto bookRequestDto) {
		Book book = bookMapper.toBook(bookRequestDto);
		Book savedBook = bookRepository.save(book);
		
		return bookMapper.toBookResponse(savedBook);
	}*/

	@Override
	public List<ScrapYardPartsResponseDto> getAllBooks() {
		
		/*
		 * Book book = bookRepository.findById(bookId) .orElseThrow(() -> new
		 * ResourceNotFoundException( String.format(BOOK_NOT_FOUND, bookId)));
		 */
		
		List<ScrapYardParts> scrapYardParts =  scrapYardRepository.findAll();
		return scrapYardParts.stream()
								.map(scrapYardMapper::toResponse)
								.collect(Collectors.toList());
		
	}

	/*@Override
	public ScrapYardResponseDto getBookById(Long bookId) {
		/*
		 * Book book = bookRepository.findById(bookId) .orElseThrow(() -> new
		 * ResourceNotFoundException( String.format(BOOK_NOT_FOUND, bookId)));
		 */
		
		/*Book book = validateAndGetBook(bookId);
		
		return bookMapper.toBookResponse(book);
	}

	@Override
	public ScrapYardResponseDto updateBook(Long bookId, scrapYardRequestDto bookRequestDto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteBook(Long bookId) {
		// TODO Auto-generated method stub
		
	}
	
	private ScrapYard validateAndGetBook(Long id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException(
						String.format(BOOK_NOT_FOUND, id)));
	}

	@Override
	public ScrapYardResponseDto addWriterToBook(Long bookId, Long writerId) {
		Book book = validateAndGetBook(bookId);
		Writer writer = writerRepository.findById(writerId)
										.orElseThrow(() -> new ResourceNotFoundException(String.format(WRITER_NOT_FOUND, writerId)));
		
		book.addWriter(writer);
		Book updatedBook = bookRepository.save(book);
		
		return bookMapper.toBookResponse(updatedBook);
	}*/

}
