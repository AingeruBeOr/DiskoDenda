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
            System.out.println("1.- Talde berriak sartu.");
            System.out.println("2.- Disko berria sartu.");
            System.out.println("3.- Gira berriak sartu.");
            System.out.println("4.- Gira baten barruan hiri berriak sartu.");
            System.out.println("5.- Saldutako sarreraren zenbakia eguneratu.");
            System.out.println("6.- Giraren bukera eta hasiera datak aldatu.");
            System.out.println("7.- Talde bat kaleratu.");
            System.out.println("8.- Girako hiriak kendu.");
            System.out.println("9.- Talde baten gira guztiak lortu.");
            System.out.println("10.- Talde baten gira bateko hiri guztiak sarreraren prezioarekin.");
            System.out.println("11.- Talde baten disko baten abesti guztiak.");
            System.out.println("12.- Talde baten disko guztien prezioak.");
            System.out.println("13.- Talde bat adierazi, produktore bera erabiltzen duten taldek lortu.");
            System.out.println("14.- Kalkulatu gira baten irabaziak.");
            System.out.println("15.- Kalkulatu diskoaren irabaziak.");
            System.out.println("16.- Abestiak zenbakiaren arabera ordenatu.");
            System.out.println("17.- Hiriak izenaren arabera ordenatu.");
            System.out.println("18.- Talde batek hiri batean lortutako irabaziak.");
            System.out.println("19.- 1000€ baino gehiago irabazi duten taldeak erakutsi.");
            System.out.println("0.- Irten");
            aukera = Integer.parseInt(br.readLine());
            aukeraIrakurri(aukera);
        }
        konexioa.close();
    }

    private void aukeraIrakurri(int aukera){
        switch (aukera){
            case 1:
                taldeakSartu();
                break;
            case 2:
                diskoBerriaSartu();
                break;
            case 3:
                giraBerriakSartu();
                break;
            case 4:
                girarenHiriakSartu();
                break;
            case 5:
                sarrerakEguneratu();
                break;
            case 6:
                girarenBukHasDatakAldatu();
                break;
            case 7:
                taldeKaleratu();
                break;
            case 8:
                hiriakKendu();
                break;
            case 9:
                taldearenGirak();
                break;
            case 10:
                girarenHiriak();
                break;
            case 11:
                diskoarenAbestiak();
                break;
            case 12:
                diskoenPrezioa();
                break;
            case 13:
                taldeProduktoreBera();
                break;
            case 14:
                giraIrabaziak();
                break;
            case 15:
                diskoIrabaziak();
                break;
            case 16:
                diskoAbestiakOrdenatu();
                break;
            case 17:
                giraHiriakOrdenatuta();
                break;
            case 18:
                hiriBatekoIrabaziak();
                break;
            case 19:
                milaBainoGehiago();
                break;
            case 0:
                System.exit(0);
                break;
            default:
                System.out.println("Sartu aukera egoki bat");
                break;
        }
    }

    private void taldeakSartu(){

    }

    private void diskoBerriaSartu(){

    }

    private void giraBerriakSartu(){

    }

    private void girarenHiriakSartu(){

    }

    private void sarrerakEguneratu(){

    }

    private void girarenBukHasDatakAldatu(){

    }

    private void taldeKaleratu(){

    }

    private void hiriakKendu(){

    }

    private void taldearenGirak(){

    }

    private void girarenHiriak(){

    }

    private void diskoarenAbestiak(){

    }

    private void diskoenPrezioa(){

    }

    private void taldeProduktoreBera(){

    }

    private void giraIrabaziak(){

    }

    private void diskoIrabaziak(){

    }

    private void diskoAbestiakOrdenatu(){

    }

    private void giraHiriakOrdenatuta(){

    }

    private void hiriBatekoIrabaziak(){

    }

    private void milaBainoGehiago(){

    }
}

