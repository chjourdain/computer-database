package com.excilys.computerdatabase.cli;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Launcher {

    public static void main(String[] args) {
        CmdLineInterface cli = null;
        try (ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(
                "classpath:consoleApplicationContext.xml")) {
            cli = new CmdLineInterface();
        }
        cli.startCmdLineInterface();
    }
}