package com.github.henrikac.logparser.cli;

public class CommandLineOption {
    private String arg = "";

    private String value = "";

    public CommandLineOption(String arg, String value) {
        this.arg = arg;
        this.value = value;
    }

    public String getArg() {
        return this.arg;
    }

    public String getValue() {
        return this.value;
    }

    @Override
    public String toString() {
        return String.format("arg: %s - value: %s", this.arg, this.value);
    }
}
