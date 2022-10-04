package rs.edu.raf.msa.statsservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import rs.edu.raf.msa.game.entity.PlayPlayer;
import rs.edu.raf.msa.statsservice.service.StatsService;

import java.util.List;
import java.util.Map;

@RestController
public class StatsController {
    @Autowired
    StatsService statsService;

    @GetMapping("/games")
    public Map<Long, List<PlayPlayer>> games() {
        return statsService.games();
    }

}
