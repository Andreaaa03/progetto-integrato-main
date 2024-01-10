package com.slamDunkers.SlamStats;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

@SpringBootApplication
public class SlamStatsApplication {

	public static void main(String[] args) throws IOException {
		SpringApplication.run(SlamStatsApplication.class, args);
	}

}
