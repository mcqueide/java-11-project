module br.com.arqdev.util {
    requires spring.beans;
    requires spring.context;
    requires dozer;
	requires spring.boot.autoconfigure;
    requires springfox.core;
    requires springfox.spring.web;
    requires springfox.swagger2;
    requires spring.web;
    requires spring.plugin.metadata;
    requires com.fasterxml.classmate;
    requires spring.plugin.core;
    requires spring.webmvc;
    requires spring.core;
    requires com.google.common;

    exports br.com.arqdev.util;
    exports br.com.arqdev.util.swagger.model;
    exports br.com.arqdev.util.swagger.api;
    exports br.com.arqdev.util.swagger.documentation;
    exports br.com.arqdev.util.swagger.operation;
    exports br.com.arqdev.util.swagger.parameter;
    exports br.com.arqdev.util.swagger.request;
    exports br.com.arqdev.util.swagger.resource;
    exports br.com.arqdev.util.swagger.response;
    exports br.com.arqdev.util.swagger.security;
    exports br.com.arqdev.util.swagger.tag;
    exports br.com.arqdev.util.swagger.type;
}