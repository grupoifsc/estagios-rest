package com.github.projetoifsc.estagios.app.utils;

public class HttpErrorMessages {

	private static final String BAD_REQUEST = "Dados inválidos foram enviados na requisição.";
	private static final String UNAUTHORIZED = "Autenticação falhou. Você deve estar corretamente autenticado para acessar este recurso.";
	private static final String TOO_MANY_REQUESTS = "O limite de requisições foi excedido. Aguarde alguns minutos e tente novamente.";
	private static final String FORBIDDEN = "Autorização falhou. Você não tem acesso a este recurso.";
	private static final String NOT_FOUND = "Recurso não encontrado ou não disponível para você.";
	
	public static final String BAD_REQUEST_MSG = "{\"code\":400,\"message\": \"" + BAD_REQUEST + "\"}";
	public static final String UNAUTHORIZED_MSG = "{\"code\":401,\"message\": \"" + UNAUTHORIZED + "\"}";
	public static final String TOO_MANY_REQUESTS_MSG = "{\"code\":429,\"message\": \"" + TOO_MANY_REQUESTS + "\"}";
	public static final String FORBIDDEN_MSG = "{\"code\":403,\"message\": \"" + FORBIDDEN + "\"}";
	public static final String NOT_FOUND_MSG = "{\"code\":404,\"message\": \"" + NOT_FOUND + "\"}";
	
}
