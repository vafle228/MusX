package org.musxteam.telegram;

import java.io.IOException;
import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.musxteam.core.RequestHandler;
import org.musxteam.core.command.EchoCommand;
import org.musxteam.core.command.HelpCommand;
import org.musxteam.core.command.TestCommand;
import org.musxteam.core.requests.ConcreteRequest;

public class Main {
    private static final RequestHandler requestHandler = new RequestHandler();
    private static final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {
        String input = br.readLine();

        ConcreteRequest request = new ConcreteRequest(input);
        requestHandler.startNewCommand(request, new TestCommand());
        System.out.println(requestHandler.handleRequest(request));

        requestHandler.startNewCommand(request, new EchoCommand());
        System.out.println(requestHandler.handleRequest(request));
    }
}