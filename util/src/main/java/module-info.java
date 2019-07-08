module br.com.arqdev.util {
    requires spring.beans;
    requires spring.context;
    requires dozer;
    requires springfox.core;
    //requires springfox.spi;
    requires springfox.spring.web;
    requires springfox.swagger2;
    //requires google.collections;
    requires spring.web;
    requires spring.plugin.metadata;
    requires com.fasterxml.classmate;
    //requires springfox.schema;
    requires spring.plugin.core;
    //requires springfox.spi;
    requires spring.webmvc;
    requires spring.core;
    requires com.google.common;
    //requires springfox.spi;

    exports br.com.arqdev.util;
}