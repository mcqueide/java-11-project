package br.com.arqdev.util.swagger.type;

public interface GenericTypeNamingStrategy {
    String getOpenGeneric();

    String getCloseGeneric();

    String getTypeListDelimiter();
}
