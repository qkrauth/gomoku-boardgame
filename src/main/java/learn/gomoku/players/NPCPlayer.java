package learn.gomoku.players;

import learn.gomoku.game.Gomoku;
import learn.gomoku.game.Stone;

import java.util.List;
import java.util.Random;

public class NPCPlayer implements Player {

    private static String[] names = {
            "Andrew", "Benson", "Carl", "Dennis", "Emily", "Fred",
            "Gina", "Holly", "Ines", "Jay", "Kingsley", "Loma", "Magnus"
    };

    private final Random random = new Random();
    private final String name;

    public NPCPlayer() {
        name = String.format("%s",
                names[random.nextInt(names.length)]
        );
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public Stone generateMove(List<Stone> previousMoves) {

        boolean isBlack = true;
        if (previousMoves != null && !previousMoves.isEmpty()) {
            Stone lastMove = previousMoves.get(previousMoves.size() - 1);
            isBlack = !lastMove.isBlack();
        }
        return new Stone(
                random.nextInt(Gomoku.WIDTH),
                random.nextInt(Gomoku.WIDTH),
                isBlack);
    }
}
