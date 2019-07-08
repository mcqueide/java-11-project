module br.com.arqdev.principal {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires dozer;

    requires br.com.arqdev.pessoa;
    requires br.com.arqdev.util;
    requires springfox.core;
    //requires springfox.spi;
    requires springfox.spring.web;
    requires springfox.swagger2;
    requires maven.model;

    exports br.com.arqdev.principal;
}