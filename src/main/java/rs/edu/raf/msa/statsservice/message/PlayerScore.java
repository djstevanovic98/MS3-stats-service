package rs.edu.raf.msa.statsservice.message;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
public class PlayerScore {
	// treba mi lista plejera

	Long gameId;
	
	String player;

	int points;

}
