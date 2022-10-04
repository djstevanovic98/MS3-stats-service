package rs.edu.raf.msa.game.entity;

import lombok.Data;

@Data
public class PlayPlayer {

    Long gameNumber;
    String description;
    int homeScore;
    int visitorScore;
    String quarterTime;
    int quarter;
}
