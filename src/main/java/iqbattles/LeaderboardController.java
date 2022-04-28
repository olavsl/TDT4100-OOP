package iqbattles;

import java.io.IOException;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class LeaderboardController extends SceneController {
    
    private Player player;
    private ArrayList<Player> players = new ArrayList<>();
    private LeaderBoardUpdater leaderBoardUpdater = new LeaderBoardUpdater();
    private Filehandler filehandler = new Filehandler();

    // @FXML private ListView<String> leaderboard;
    @FXML private TableView<Player> leaderboard;
    @FXML private TableColumn<Player, String> namesColumn;
    @FXML private TableColumn<Player, Integer> rankColumn;
    @FXML private TableColumn<Player, Integer> ratingColumn;
    @FXML private TableColumn<Player, Integer> gamesColumn;

    ObservableList<Player> playerList = FXCollections.observableArrayList();

    // Sort players array
    public void displayLeaderboard() throws IOException {
        // Using LeaderBoardUpdater to sort players after rating, and then updating the file
        this.players = filehandler.getPlayersFromFile("players.txt");
        this.playerList.addAll(this.players);
        leaderBoardUpdater.rankSort(this.playerList);
        filehandler.writePlayersToFile("players.txt", this.players);

        // Filling table columns with player info
        namesColumn.setCellValueFactory(new PropertyValueFactory<Player, String>("username"));
        rankColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("ranking"));
        ratingColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("rating"));
        gamesColumn.setCellValueFactory(new PropertyValueFactory<Player, Integer>("gamesPlayed"));

        leaderboard.setItems(playerList);
    }

    @FXML
    private void switchToPlayerProfile(ActionEvent event) throws IOException {
        switchToPlayerProfile(event, this.player);
    }
    
    // Setter
    public void setPlayer(Player player) {
        this.player = player;
    }
    
}
