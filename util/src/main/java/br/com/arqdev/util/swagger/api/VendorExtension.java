package br.com.arqdev.util.swagger.api;

public interface VendorExtension<T> {
    String getName();

    T getValue();
}