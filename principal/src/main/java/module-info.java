module br.com.arqdev.principal {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires spring.beans;
	requires spring.webmvc;
	
    requires br.com.arqdev.autenticacao;
    requires br.com.arqdev.pessoa;
    requires br.com.arqdev.util;
    requires br.com.arqdev.swagger;

    exports br.com.arqdev.principal;
}