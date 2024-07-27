package Server;

import java.io.PrintWriter;

public class RequestHandler {

    public void handel(String response , PrintWriter out){
        if(response.startsWith("UpdBoard")){
            String[] parts = response.split(" ");
            LeaderBoard leaderBoard = new LeaderBoard();
            leaderBoard = leaderBoard.load("allResult.json");
            boolean c = false;
            for(History history : leaderBoard.getHistories()){
                if(history.getName().equals(parts[1])){
                    long aliveTime = Integer.parseInt(parts[2]);
                    int xp = Integer.parseInt(parts[3]);
                    if(history.getMostAliveTime() < aliveTime)
                        history.setMostAliveTime(aliveTime);
                    if(history.getMostAliveTime() < xp)
                        history.setMostGainedXp(xp);
                    c = true;
                }
            }
            if(c == false){
                History history = new History();
                long aliveTime = Integer.parseInt(parts[2]);
                int xp = Integer.parseInt(parts[3]);
                history.setName(parts[1]);
                history.setMostGainedXp(xp);
                history.setMostAliveTime(aliveTime);
                leaderBoard.getHistories().add(history);
            }
            leaderBoard.save("allResult.json");
        }
        else if (response.startsWith("NeedGameHistory")) {
            sendGameHistory(out);
        }
    }
    private void sendGameHistory(PrintWriter out) {
        LeaderBoard leaderBoard = new LeaderBoard();
        leaderBoard = leaderBoard.load("allResult.json"); // بارگذاری داده‌ها

        for (History history : leaderBoard.getHistories()) {
            String data = history.getName() + " - Alive Time: " + history.getMostAliveTime() +
                    ", XP: " + history.getMostGainedXp();
            out.println(data); // ارسال داده‌ها به کلاینت
        }
        out.println("END"); // علامت پایان داده‌ها
    }
}
