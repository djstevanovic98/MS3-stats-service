package rs.edu.raf.msa.statsservice.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;
import rs.edu.raf.msa.game.entity.PlayPlayer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class StatsService {

	@Value("${rabbitmq.queue}")
	private String queueName;

	@Autowired
	RabbitTemplate template;

	Map<Long, List<PlayPlayer>> utakmice = new HashMap<Long, List<PlayPlayer>>(){
		{
			put((long)1, new ArrayList<>());
			put((long)2, new ArrayList<>());
			put((long)3, new ArrayList<>());
		}
	};
	List<PlayPlayer> playPlayers = new ArrayList<>();

	// consumer
	@RabbitListener(queues = "${rabbitmq.queue}")
	public void listen(Message message) throws JsonProcessingException {

		log.info("Received message - {}", message.toString());

		ObjectMapper objectMapper = new ObjectMapper();

		String stringMessage = new String(message.getBody());
		System.out.println("Telo: "+ stringMessage);

		if (stringMessage.contains("gameNumber")) {
			log.info("PlayerScore Message read from {} : {}", queueName, objectMapper.readValue(stringMessage, PlayPlayer.class).toString());
			PlayPlayer playPlayer = objectMapper.readValue(stringMessage, PlayPlayer.class);
			playPlayers.add(playPlayer);
			utakmice.get(playPlayer.getGameNumber()).add(playPlayer);

		} else {
			log.info("Hello World Message read from {} : {}", queueName, stringMessage);
		}
	}

	public Map<Long, List<PlayPlayer>> games(){
		return this.utakmice;
	}


}
