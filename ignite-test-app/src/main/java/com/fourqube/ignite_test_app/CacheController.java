package com.fourqube.ignite_test_app;

import org.apache.ignite.Ignition;
import org.apache.ignite.client.ClientCache;
import org.apache.ignite.client.IgniteClient;
import org.apache.ignite.configuration.ClientConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class CacheController {

    @Autowired
    private ClientConfiguration cfg;

    @GetMapping("/api/hello")
    public String hello() {
        return "Hello, World from REST Controller!";
    }

    @PostMapping("/api/cache/{name}")
    public String putInCache(@PathVariable String name, @RequestBody String body) {
        try (IgniteClient client = Ignition.startClient(cfg)) {
            ClientCache<String, String> cache = client.cache("someCache");
            cache.put(name, body);
            return cache.get(name);
        }
    }

    @GetMapping("/api/cache/{name}")
    public String getCache(@PathVariable String name) {
        try (IgniteClient client = Ignition.startClient(cfg)) {
            ClientCache<String, String> cache = client.cache("someCache");
            return cache.get(name);
        }
    }

}
