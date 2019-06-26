module br.com.arqdev.principal {
    requires spring.boot;
    requires spring.boot.autoconfigure;
    requires spring.context;
    requires dozer;

    requires br.com.arqdev.pessoa;
    requires br.com.arqdev.util;

    exports br.com.arqdev.principal;
}