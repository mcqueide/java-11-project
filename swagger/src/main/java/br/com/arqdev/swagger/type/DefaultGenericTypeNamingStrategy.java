package br.com.arqdev.swagger.type;

public class DefaultGenericTypeNamingStrategy implements GenericTypeNamingStrategy {
    private static final String OPEN = "«";
    private static final String CLOSE = "»";
    private static final String DELIM = ",";

    public DefaultGenericTypeNamingStrategy() {
    }

    public String getOpenGeneric() {
        return "«";
    }

    public String getCloseGeneric() {
        return "»";
    }

    public String getTypeListDelimiter() {
        return ",";
    }
}
