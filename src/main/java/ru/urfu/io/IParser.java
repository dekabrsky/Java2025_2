package ru.urfu.io;

import java.io.IOException;
import java.util.List;

public interface IParser<T> {

    List<String> readFile(String pathString) throws IOException;

    List<T> parse(String pathString) throws IOException;
}
