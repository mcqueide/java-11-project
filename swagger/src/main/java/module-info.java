module br.com.arqdev.swagger {
	requires spring.boot.autoconfigure;
	requires spring.context;
    requires spring.beans;
    requires spring.web;
    requires spring.core;
    requires spring.plugin.core;
    requires spring.plugin.metadata;
    requires spring.webmvc;
    requires com.google.common;
    requires springfox.core;
    requires springfox.swagger2;
    requires com.fasterxml.classmate;
    requires tomcat.embed.core;

    exports br.com.arqdev.swagger;
}