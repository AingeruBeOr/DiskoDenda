package disko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DB {
    private BufferedReader br;
    private Connection konexioa;

    public static void main(String[] args) throws NumberFormatException, IOException, SQLException {
        DB db = new DB();
        db.menuErakutsi();
    }

    private DB(){
        br = new BufferedReader(new InputStreamReader(System.in));
        konektatu();
    }

    private void konektatu(){
        try{
            Class.forName("com.mysql.cj.jbdc.Driver");
            String zerbitzaria = "jbdc:mysql://localhost:3306/XXX";
            String erabiltzailea = "root";
            String pasahitza = "";
            konexioa = DriverManager.getConnection(zerbitzaria,erabiltzailea,pasahitza);
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }

    private void menuErakutsi() throws NumberFormatException, IOException, SQLException{
        int aukera = -1;
        while(aukera!=0){
            System.out.println("\n*********** Menua ********");
            System.out.println("1.- Aukera");
            System.out.println("0.- Irten");
            aukera = Integer.parseInt(br.readLine());
            switch (aukera){
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                default:
                    System.out.println("Sartu aukera egoki bat");
                    break;
            }
        }
        konexioa.close();
    }


}

