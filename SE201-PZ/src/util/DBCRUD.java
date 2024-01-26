package util;

import java.sql.*;
import klase.Klijent;
import klase.Lokacija;
import klase.VlasnikNekretnine;

import static util.DatabaseConnector.con;

/**
 *
 *
 * @author Admin
 */
public class DBCRUD {

    public static void dodajKlijenta(Klijent klijent) {
        try {
            DatabaseConnector.getConnection();
            PreparedStatement st = DatabaseConnector.con.prepareStatement("INSERT INTO klijent(KlijentID, Adresa, Ime, Telefon)" + " VALUES (?,?,?,?)");
            st.setInt(1, klijent.getKlijentId());
            st.setString(2, klijent.getAdresa());
            st.setString(3, klijent.getIme());
            st.setString(4, klijent.getTelefon());

            st.execute();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void dodajVlasnika(VlasnikNekretnine vlasnik) {
        try {
            DatabaseConnector.getConnection();
            PreparedStatement st = DatabaseConnector.con.prepareStatement("INSERT INTO vlasniknekretnine(VlasnikID, Adresa, Ime, Telefon, Godine)" + " VALUES (?,?,?,?,?)");
            st.setInt(1, vlasnik.getVlasnikId());
            st.setString(2, vlasnik.getAdresa());
            st.setString(3, vlasnik.getIme());
            st.setString(4, vlasnik.getTelefon());
            st.setInt(5, vlasnik.getGodine());

            st.execute();
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void ispisLokacije() throws SQLException {
        ResultSet resultSet = null;
        try {
            DatabaseConnector.getConnection();
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT * FROM lokacija");

            resultSet = st.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("lokacijaid");
                String drzava = resultSet.getString("drzava");
                String grad = resultSet.getString("grad");
                String ulica = resultSet.getString("ulica");

                System.out.println("ID: " + id + ", Drzava: " + drzava + ", Grad: " + grad + ", Ulica: " + ulica);

            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void ispisDodatno() throws SQLException {
        ResultSet resultSet = null;
        try {
            DatabaseConnector.getConnection();
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT * FROM dodatno");

            resultSet = st.executeQuery();
            while (resultSet.next()) {
                int id = resultSet.getInt("dodatnoId");
                Boolean grejanje = resultSet.getBoolean("grejanje");
                Boolean kablovska = resultSet.getBoolean("kablovska");
                Boolean klima = resultSet.getBoolean("klima");
                Boolean parkingMesto = resultSet.getBoolean("parkingMesto");
                Boolean terasa = resultSet.getBoolean("terasa");

                System.out.println("Id: " + id + " ,Grejanje: " + grejanje + " ,Kablovska: " + kablovska + " ,Klima: " + klima + " ,Parking mesto: " + parkingMesto + " ,Terasa: " + terasa);

            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateUgovor(double naknadaAgenciji, int idUgovora) {
        try {
            DatabaseConnector.getConnection();
            PreparedStatement st = DatabaseConnector.con.prepareStatement("UPDATE Ugovor SET naknadaAgenciji = ? WHERE ugovorid = ?");
            st.setDouble(1, naknadaAgenciji);
            st.setInt(2, idUgovora);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Ažuriranje uspešno!");
            } else {
                System.out.println("Nema podataka za ažuriranje.");
            }
            con.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void updateVlasnik(String Ime, int idVlasnika) {
        try {
            DatabaseConnector.getConnection();
            PreparedStatement st = DatabaseConnector.con.prepareStatement("UPDATE VlasnikNekretnine SET Ime = ? WHERE vlasnikid = ?");
            st.setString(1, Ime);
            st.setInt(2, idVlasnika);

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                System.out.println("Ažuriranje uspešno!");
            } else {
                System.out.println("Nema podataka za ažuriranje.");
            }
            con.close();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }

    public static void sortiranjeBaze(String tabela, String kolona, String nacin) throws SQLException{
        try {
            DatabaseConnector.getConnection();
            PreparedStatement st = DatabaseConnector.con.prepareStatement("SELECT * FROM " + tabela + " ORDER BY " + kolona + " " + nacin);
            ResultSet resultSet = st.executeQuery();
            
            while (resultSet.next()) {
                int id = resultSet.getInt(tabela+"Id");
                String sortiranaKolona = resultSet.getString(kolona);
                

                System.out.println("Id: " + id +" " + kolona+":" + sortiranaKolona);
                
                
            }
             con.close();
            }catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }}
    