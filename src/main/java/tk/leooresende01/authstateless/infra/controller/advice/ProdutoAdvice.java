package tk.leooresende01.authstateless.infra.controller.advice;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProdutoAdvice {
	
	@ExceptionHandler(value = { NoSuchElementException.class, 
			EmptyResultDataAccessException.class } )
	public ResponseEntity<Void> tratandoErroDeIdNaoEncontrado() {
		return ResponseEntity.notFound().build();
	}
	
	@ExceptionHandler(value = { HttpMessageNotReadableException.class} )
	public ResponseEntity<ErroDto> tratandoErroDeFormularioInvalido() {
		return ResponseEntity.badRequest().body(new ErroDto("Formulario de produtos invalido!", HttpStatus.BAD_REQUEST.value()));
	}
}
