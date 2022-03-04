package com.example.segproject.components;

import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

/**
 * Text input field that only accepts integers
 */
public class IntegerField extends TextField {
    public IntegerField() {
        super();
        setTextFormatter(new TextFormatter<Integer>(new IntegerStringConverter()));
    }
}
