package iqbattles;

public class Player{
    
    private String username;
    private String password;
    private int rating;
    private int ranking;
    private int gamesPlayed;

    public Player(String username, String password, int rating, int ranking, int gamesPlayed) {
        setUsername(username);
        setPassword(password);
        this.rating = rating;
        this.ranking = ranking;
        this.gamesPlayed = gamesPlayed;
    }

    @Override
    public String toString() {
        return this.username + " " + this.password + " " + this.rating + " " + this.ranking + " " + this.gamesPlayed;
    }

    // Setters
    public void setUsername(String username) {
        //Innkapsling av tilstand
        if (username.length() < 4 || username.length() > 16) {
            throw new IllegalArgumentException("Username does not match allowed length!");
        }

        if (!username.matches("[a-zA-Z0-9]+")) {
            throw new IllegalArgumentException("Username can only contain letters and numbers!");
        }
        
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public void setRanking(int ranking) {
        this.ranking = ranking;
    }
    
    public void setGamesPlayed(int gamesPlayed) {
        this.gamesPlayed = gamesPlayed;
    }

    public void addToGamesPlayed() {
        this.gamesPlayed++;
    }

    // Getters
    public String getUsername() {
        return this.username;
    }

    public String getHashedPassword() {
        return this.password;
    }

    public int getRating() {
        return this.rating;
    }

    public int getRanking() {
        return this.ranking;
    }

    public int getGamesPlayed() {
        return this.gamesPlayed;
    }
}