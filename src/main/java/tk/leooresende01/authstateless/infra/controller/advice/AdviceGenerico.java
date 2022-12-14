package tk.leooresende01.authstateless.infra.controller.advice;

import java.util.NoSuchElementException;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.InternalAuthenticationServiceException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import tk.leooresende01.authstateless.infra.controller.advice.exception.ForbiddenException;
import tk.leooresende01.authstateless.infra.controller.advice.exception.OfertaException;
import tk.leooresende01.authstateless.infra.controller.advice.exception.ProdutoException;
import tk.leooresende01.authstateless.infra.controller.advice.exception.UsernameOrPasswordInvalidException;
import tk.leooresende01.authstateless.infra.controller.advice.exception.UsuarioJaExisteException;

@RestControllerAdvice
public class AdviceGenerico {

	@ExceptionHandler(value = { NoSuchElementException.class, EmptyResultDataAccessException.class,
			BadCredentialsException.class })
	public ResponseEntity<ErroDto> tratandoErroDeIdNaoEncontrado(Exception ex) {
		if (ex instanceof NoSuchElementException || ex instanceof EmptyResultDataAccessException)
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body(new ErroDto("Recurso não encontrado", HttpStatus.NOT_FOUND.value()));
		else if (ex instanceof InternalAuthenticationServiceException || ex instanceof BadCredentialsException)
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
					.body(new ErroDto("Usuario e/ou senha invalidos", HttpStatus.UNAUTHORIZED.value()));
		else
			return ResponseEntity.internalServerError().build();
	}

	@ExceptionHandler(value = ForbiddenException.class)
	public ResponseEntity<ErroDto> tratandoErroDePermissaoDenied() {
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(new ErroDto(
				"Você não tem permissão de acessar ou modificar esse recurso", HttpStatus.FORBIDDEN.value()));
	}

	@ExceptionHandler(value = { UsuarioJaExisteException.class, UsernameOrPasswordInvalidException.class,
			HttpMessageNotReadableException.class, OfertaException.class, ProdutoException.class })
	public ResponseEntity<ErroDto> tratandoErroDeUsuarioJaExisteException(Exception ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST)
				.body(new ErroDto(ex.getMessage(), HttpStatus.BAD_REQUEST.value()));
	}

	@ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
	public ResponseEntity<ErroDto> tratandoErroDeMetodoHTTPNaoSuportado() {
		return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED).body(
				new ErroDto("Esse metodo HTTP não é suportado por essa rota", HttpStatus.METHOD_NOT_ALLOWED.value()));
	}
}
