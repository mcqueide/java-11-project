module br.com.arqdev.principal {
	requires spring.boot;
	requires spring.boot.autoconfigure;
	requires spring.context;
	requires dozer;
	
	exports br.com.arqdev.principal;
	
	opens br.com.arqdev.principal;
}