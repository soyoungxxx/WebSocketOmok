package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;


public class CustomGameDAO {
    private Connection connection = null;

    // MySQL 연결 정보
    private final String url = "jdbc:mysql://localhost:3306/omok";
    private final String username = "root";
    private final String password = "root";


    public CustomGameDAO() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("연결 성공");
        } catch (Exception e) {
            System.out.println("연결 실패");
            e.printStackTrace();
        }
    }

    public void createGame(String roomCode) { //게임 방 생성
        try {
            String query = "insert into GameList (is_custom, game_code) values(?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1,true);
            preparedStatement.setString(2,roomCode);
            preparedStatement.executeUpdate();
            System.out.println("방 생성 성공");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("방 생성 실패");
        }
    }


    public void changeIsCustom(String roomCode){ //현재 방의 roomCode를 기반으로 그 방의 is_custom 값 변경
        try{
            String query = "update gamelist set is_custom=? where game_code=? and is_custom=true";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setBoolean(1,false);
            preparedStatement.setString(2,roomCode);
            preparedStatement.executeUpdate();
            System.out.println("방 전환 성공");
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("방 전환 실패");
        }
    }
}
