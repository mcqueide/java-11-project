package br.com.arqdev.util.swagger.type;


public class CodeGenGenericTypeNamingStrategy implements GenericTypeNamingStrategy {
    private static final String OPEN = "Of";
    private static final String CLOSE = "";
    private static final String DELIM = "And";

    public CodeGenGenericTypeNamingStrategy() {
    }

    public String getOpenGeneric() {
        return "Of";
    }

    public String getCloseGeneric() {
        return "";
    }

    public String getTypeListDelimiter() {
        return "And";
    }
}
