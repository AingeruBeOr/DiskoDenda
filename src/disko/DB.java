package disko;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.*;

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

    private void aukeraIrakurri(int aukera) throws IOException,SQLException{
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

    private void taldeakSartu() throws SQLException, IOException, NumberFormatException{
    	//TODO NONDIK ATERA PRODUKTOREA?
    	Statement st =konexioa.createStatement();
    	//Taldea sartzeko:
    	System.out.println("Sartu taldearen izena: ");
    	String izena= br.readLine();
		System.out.println("Sartu taldearen kodea: ");
		int kode=Integer.parseInt(br.readLine());
		System.out.println("Sartu produktorearen kodea:");
		int pKode=Integer.parseInt(br.readLine());
		String query="insert into talde values('"+izena+"',"+kode+","+pKode+")";
		System.out.println(query);
		st.executeUpdate(query);
		//Partaideak sartzeko:
		boolean jarraitu=true;
		String pIzen;
		int partKode;
		String hizki;
		do {
			System.out.println("Sartu partaidearen izena: ");
	    	pIzen= br.readLine();
			System.out.println("Sartu partaidearen kodea: ");
			partKode=Integer.parseInt(br.readLine());
			query="insert into partaidea values('"+pIzen+"',"+partKode+","+kode+")";
			System.out.println(query);
			st.executeUpdate(query);
			System.out.println("Gelditzeko sakatu 'G', bestela beste tekla sakatu.");
			hizki=br.readLine();
			if(hizki.equalsIgnoreCase("G")) {jarraitu=false;}
		}while(jarraitu);
    }

    private void diskoBerriaSartu(){

    }

    private void giraBerriakSartu(){

    }

    private void girarenHiriakSartu() throws SQLException, IOException, NumberFormatException{
    	//TODO igual habria q poner a que gira pertenece cada ciudad como si fuese identitate ahula?
    	Statement st =konexioa.createStatement();
    	boolean jarraitu=true;
		String herrialde;
		String hiriIzen;
		String hizki;
		String query;
		
		do {
			System.out.println("Sartu herrialdearen izena: ");
	    	herrialde= br.readLine();
	    	System.out.println("Sartu hiriaren izena: ");
	    	hiriIzen= br.readLine();
	    	query="insert into hiria values('"+herrialde+"','"+hiriIzen+"')";
			System.out.println(query);
			st.executeUpdate(query);
			System.out.println("Gelditzeko sakatu 'G', bestela beste tekla sakatu.");
			hizki=br.readLine();
			if(hizki.equalsIgnoreCase("G")) {jarraitu=false;}
		}while(jarraitu);
    }

    private void sarrerakEguneratu(){

    }
    
    /**
     * Gira bateko datetan arazoak egonda hauek aldatuko dira:
     * @throws IOException
     * @throws SQLException
     * @throws NumberFormatException
     */
    private void girarenBukHasDatakAldatu()throws SQLException, NumberFormatException, IOException{
    	Statement st =konexioa.createStatement();
    	System.out.println("Aldatu nahi duzun girak egiten duen taldearen KODEA adierazi: ");
    	int kodea= Integer.parseInt(br.readLine());
    	System.out.println("Sartu zein den aldatu nahi duzun giraren hasiera data (XXXX-XX-XX formatuan): ");
		String data=br.readLine();
    	String dataB;
    	String query;
    	System.out.println("Hasiera data aldatu nahi duzu? Hala bada sakatu 'B' hizkia, bestela sakatu beste bat.");
    	if(br.readLine().equalsIgnoreCase("B")) {
    		System.out.println("Sartu hasiera data berria (XXXX-XX-XX formatuan): ");
    		dataB=br.readLine();
    		query="update gira set HasData='"+dataB+"' where TaldeK="+kodea+" and HasData='"+data+"'";
    		System.out.println(query);
			st.executeUpdate(query);
    	}
    	System.out.println("Bukaera data aldatu nahi duzu? Hala bada sakatu 'B' hizkia, bestela sakatu beste bat.");
    	if(br.readLine().equalsIgnoreCase("B")) {
    		System.out.println("Sartu bukaera data berria (XXXX-XX-XX formatuan): ");
    		dataB=br.readLine();
    		query="update gira set BukData='"+dataB+"' where TaldeK="+kodea+" and HasData='"+data+"'";
    		System.out.println(query);
			st.executeUpdate(query);
    	}
    }

    private void taldeKaleratu(){

    }

    private void hiriakKendu(){

    }

    private void taldearenGirak(){

    }

    private void girarenHiriak(){

    }

    /**
     * Disko baten abesti guztiak erakutsiko dira.
     * Horretatrako,
     * @throws IOException
     * @throws SQLException
     */
    private void diskoarenAbestiak() throws IOException,SQLException{
        System.out.println("Sartu taldearen izena:");
        String taldeIzen = br.readLine();
        System.out.println("Sartu " + taldeIzen + " taldearen diskoaren izena:");
        String diskoIzen = br.readLine();
        PreparedStatement ps = konexioa.prepareStatement(
                "SELECT DISKO.Kodea, DISKO.Izena, ABESTIA.* " +
                "FROM TALDE, DISKO, ABESTIA " +
                "WHERE TALDE.kodea = DISKO.TaldeK AND " +
                        "DISKO.Kodea = ABESTIA.DiskoK AND " +
                        "DISKO.izena = ? AND " +
                        "TALDE.Izena = ?");
        ps.setString(1,diskoIzen);
        ps.setString(2,taldeIzen);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
    }

    private void diskoenPrezioa(){

    }

    private void taldeProduktoreBera(){

    }

    /**
     * Talde batek gira batean lortuako irabaziak adieraziko dira.
     * Horretarako, taldearen izena eta gira adierziko dira.
     * @throws IOException
     * @throws SQLException
     */
    private void giraIrabaziak() throws IOException,SQLException{
        System.out.println("Sartu talde baten izena: ");
        String taldeIzen = br.readLine();
        System.out.println("Sartu girak identifikatzen duen hasiera data: ");
        String giraData = br.readLine();
        PreparedStatement ps = konexioa.prepareStatement(
                "SELECT SUM(LEKUA.SaldutakoSarrerak * LEKUA.Prezioa) " +
                "FROM TALDE, LEKUA, HIRIAN_JO " +
                "WHERE TALDE.izena = ? and " +
                    "TALDE.TaldeK = HIRIAN_JO.TaldeK and " +
                    "HIRIAN_JO.HasData = ? and " +
                    "LEKUA.Herrialdea = HIRIAN_JO.herrialdea and " +
                    "LEKUA.HiriIzen = HIRIAN_JO.HiriIzen");
        ps.setString(1,taldeIzen);
        ps.setString(2,giraData);
        ResultSet rs = ps.executeQuery();
        rs.next();
        System.out.println(rs.getString(1));
    }

    private void diskoIrabaziak(){

    }

    private void diskoAbestiakOrdenatu(){

    }

    private void giraHiriakOrdenatuta(){

    }

    /**
     * Talde batek hiri baten iriabazitako dirua adierziko da.
     * Horretarako, taldearen izena eta hiriaren izena eskatuko dira.
     * @throws IOException
     * @throws SQLException
     */
    private void hiriBatekoIrabaziak() throws IOException,SQLException{
        System.out.println("Sartu talde baten izena: ");
        String taldeIzen = br.readLine();
        System.out.println("Sartu hiri baten izena: ");
        String hiriIzen = br.readLine();
        PreparedStatement ps = konexioa.prepareStatement(
                "SELECT HIRIA.Izena, COUNT(*), SUM(Prezioa*SaldutakoSarrerak) " +
                "FROM HIRIA, LEKUA, TALDE, GIRA " +
                "WHERE TALDE.Izena=? AND " +
                    "TALDE.Kodea=GIRA.TaldeK AND " +
                    "HIRIA.Izena=? AND " +
                    "HIRIA.Izena=LEKUA.HiriIzena AND " +
                    "HIRIA.Herrialdea=LEKUA.Herrialdea");
        ps.setString(1,taldeIzen);
        ps.setString(2,hiriIzen);
        ResultSet rs = ps.executeQuery();
        while(rs.next()){
            System.out.println(rs.getString(1) + rs.getString(2) + rs.getString(3));
        }
    }

    private void milaBainoGehiago(){

    }
}

