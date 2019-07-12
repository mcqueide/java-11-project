package br.com.arqdev.swagger.api;

public interface VendorExtension<T> {
    String getName();

    T getValue();
}