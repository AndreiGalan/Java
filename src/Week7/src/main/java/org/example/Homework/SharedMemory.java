package org.example.Homework;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SharedMemory {
    private final List<Token> tokens;

    public SharedMemory(int n) {
        tokens = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            tokens.add(new Token(i));
        }
        Collections.shuffle(tokens);
    }

    public synchronized List<Token> extractTokens(int howMany) {
        List<Token> extracted = new ArrayList<>();
        for (int i = 0; i < howMany; i++) {
            if (tokens.isEmpty()) {
                break;
            }
            extracted.add(tokens.remove(0));
        }
        return extracted;
    }
    public List<Token> getTokens() {
        return tokens;
    }

    public void addTokens(List<Token> tokens) {
        this.tokens.addAll(tokens);
    }
}
