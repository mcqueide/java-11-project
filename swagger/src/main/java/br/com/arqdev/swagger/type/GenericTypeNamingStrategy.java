package br.com.arqdev.swagger.type;

public interface GenericTypeNamingStrategy {
    String getOpenGeneric();

    String getCloseGeneric();

    String getTypeListDelimiter();
}
