package newworldorder.game.command;

import java.io.Serializable;

import newworldorder.common.network.command.Command;
import newworldorder.game.model.GameEngine;

public interface IGameCommand extends Command, Serializable {
	public void setGameEngine(GameEngine engine);
}
