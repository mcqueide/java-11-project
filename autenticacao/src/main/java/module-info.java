module br.com.arqdev.autenticacao {
    requires spring.data.jpa;
    requires spring.context;
	requires spring.beans;
	requires spring.security.core;
	requires spring.security.web;
	requires spring.security.config;
	requires spring.web;
	requires spring.boot.autoconfigure;

	requires java.validation;
    requires java.persistence;
    requires lombok;
	requires jjwt;
	requires tomcat.embed.core;
	
	requires br.com.arqdev.util;
	
	exports br.com.arqdev.autenticacao;
	exports br.com.arqdev.autenticacao.modelo;
	exports br.com.arqdev.autenticacao.dto;
}