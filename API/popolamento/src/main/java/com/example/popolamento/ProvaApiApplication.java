package com.example.popolamento;

import com.example.popolamento.Service.PopolamentoAll;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;


@SpringBootApplication
public class ProvaApiApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication app = new SpringApplication(ProvaApiApplication.class);
		ConfigurableApplicationContext context = app.run(args);


		PopolamentoAll popolamentoAll = context.getBean(PopolamentoAll.class);
//		popolamentoAll.team(); //team
		popolamentoAll.games(); //games
		popolamentoAll.scores(); //scores
		Thread.sleep(600000);
//		popolamentoAll.player(); //player
		popolamentoAll.playerStat(); //playerStat
		Thread.sleep(600000);
		popolamentoAll.teamStat(); //teamStat
		Thread.sleep(600000);
		popolamentoAll.classifica(); //classifica

		context.close();
	}
}
