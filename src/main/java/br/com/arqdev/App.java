package br.com.arqdev;


import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {
        List<String> lista = List.of("a", "b");
        lista.forEach(System.out::println);
    }
}
