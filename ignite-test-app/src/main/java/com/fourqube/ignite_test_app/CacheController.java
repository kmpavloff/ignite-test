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

    @PostMapping("/api/cache/{key}")
    public String putInCache(@PathVariable String key, @RequestBody String body) {
        try (IgniteClient client = Ignition.startClient(cfg)) {
            ClientCache<String, String> cache = client.cache("someCache");
            cache.put(key, body);
            return "OK";
        }
    }

    @GetMapping("/api/cache/{key}")
    public String getCache(@PathVariable String key) {
        try (IgniteClient client = Ignition.startClient(cfg)) {
            ClientCache<String, String> cache = client.cache("someCache");
            return cache.get(key);
        }
    }

}
