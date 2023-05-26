package com.example.ssia.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.concurrent.DelegatingSecurityContextCallable;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RestController
@Slf4j
public class HelloController {

    @GetMapping("/hello")
    public String hello(Authentication auth) {
        return "Hello, " + auth.getName() + "!\n";
    }

    @GetMapping("/bye")
    @Async
    public void goodbye() {
        SecurityContext context = SecurityContextHolder.getContext();
        Authentication auth = context.getAuthentication();
        String username = auth.getName();
        log.info("username = {}", username);
    }

    @GetMapping("/ciao")
    public String ciao() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };

        ExecutorService e = Executors.newCachedThreadPool();
        try {
            // Not necessary when SecurityContextHolder's strategy is MODE_INHERITABLETHREADLOCAL.
            var contextTask = new DelegatingSecurityContextCallable<>(task);
            return "Ciao, " + e.submit(contextTask).get() + "!";
        } finally {
            e.shutdown();
        }
    }

    @GetMapping("/hola")
    public String hola() throws Exception {
        Callable<String> task = () -> {
            SecurityContext context = SecurityContextHolder.getContext();
            return context.getAuthentication().getName();
        };
        ExecutorService e = Executors.newCachedThreadPool();
        // Not necessary when SecurityContextHolder's strategy is MODE_INHERITABLETHREADLOCAL.
        e = new DelegatingSecurityContextExecutorService(e);
        try {
            return "Hola, " + e.submit(task).get() + "!";
        } finally {
            e.shutdown();
        }
    }
}
