module br.com.arqdev.pessoa {

    requires spring.web;
	requires spring.beans;
	requires spring.context;
	requires spring.data.jpa;
	requires spring.data.commons;
    requires spring.boot.autoconfigure;

    requires java.annotation;
    requires java.persistence;
	requires jackson.annotations;
	requires lombok;

	requires br.com.arqdev.autenticacao;
	requires br.com.arqdev.util;

	exports br.com.arqdev.pessoa;
}