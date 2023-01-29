/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

import java.sql.*;
import java.util.ArrayList;

/**
 *
 * @author lucas
 */
public class IrisDAO {

    private String url = "jdbc:sqlite:iris-data.db";
    private static IrisDAO instance;
    protected final Connection conn = connect();
    private final FlorHelper florHelper = FlorHelper.getInstance();
    
    public IrisDAO() {
        
    }

    public IrisDAO(String url) {
        this.url = url;

    }

    /**
     * @return
     * @throws SQLException
     */
    public IrisDAO getInstance() throws SQLException {
        if (instance == null) {
            instance = new IrisDAO(this.url);
            if (instance.connect() == null) {
                throw new SQLException("Falha ao conectar ao banco de dados");
            } else {
                instance.createTable();
                instance.populate();
            }
        }
        return instance;
    }

    private Connection connect() {
        try {
            Connection connection = DriverManager.getConnection(this.url);
            return connection;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public final void createTable() {

        try {
            Statement stmt = this.conn.createStatement();

            stmt.execute("CREATE TABLE IF NOT EXISTS FLOWERS("
                    + "ID INTEGER NOT NULL,"
                    + "sepal_length DOUBLE NOT NULL,"
                    + "sepal_width DOUBLE NOT NULL,"
                    + "petal_length DOUBLE NOT NULL,"
                    + "petal_width DOUBLE NOT NULL,"
                    + "class text NOT NULL)");

            System.out.println("Table created!");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public final void populate() {
        Flor flower1 = new Flor(1, 5.3, 1.2, 3.2, 0.2, "Setosa");
        Flor flower2 = new Flor(2, 2.3, 3.2, 1.2, 0.4, "Versicolor");
        Flor flower3 = new Flor(3, 1.3, 2.2, 4.2, 0.7, "Virginica");
        
        florHelper.addFlor(flower1);
        florHelper.addFlor(flower2);
        florHelper.addFlor(flower3);
        
        
        
        this.insert(flower1);
        this.insert(flower2);
        this.insert(flower3);
    }
    
    public void setPstmt(PreparedStatement pstmt, Flor flor){
        try{
            pstmt.setDouble(1, flor.sepal_length);
            pstmt.setDouble(2, flor.sepal_width);
            pstmt.setDouble(3, flor.petal_length);
            pstmt.setDouble(4, flor.petal_width);
            pstmt.setString(5, flor.class_);
            pstmt.setInt(6, flor.id);
            pstmt.executeUpdate();
        }catch(SQLException e){
             System.out.println(e.getMessage());
        }
    }
    
    public boolean isFlower(Flor flower){
        return search(flower.id) != null;
    }
    
    public boolean insert(Flor flor) {
        try {
            if (isFlower(flor) == false){
                PreparedStatement pstmt = this.conn.prepareStatement("INSERT INTO FLOWERS("
                    + "sepal_length,"
                    + "sepal_width,"
                    + "petal_length,"
                    + "petal_width,"
                    + "class,"
                    + "ID) VALUES(?,?,?,?,?,?)");

                setPstmt(pstmt, flor);

                System.out.println("Successfull inserted.");

                return true;
            } else{
                return false;
            }
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public void update (Flor flor) {
        try {
            if (!isFlower(flor)) {
                throw new SQLException("Action canceled");
            }

            PreparedStatement pstmt = this.conn.prepareStatement("UPDATE FLOWERS SET "
                    + "sepal_length = ?,"
                    + "sepal_width = ?,"
                    + "petal_length = ?,"
                    + "petal_width = ?,"
                    + "class = ? WHERE id = ?");

            setPstmt(pstmt, flor);

            System.out.println("Successfull Updated.");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public void delete(int id) {
        try {
            PreparedStatement pstmt = this.conn.prepareStatement("DELETE FROM FLOWERS "
                    + "WHERE id = ?");

            pstmt.setInt(1, id);
            pstmt.executeUpdate();

            System.out.println("Successfull Deleted.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public Flor search(int id) {
        try {
            Statement statement = this.conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM FLOWERS");
            
            while (rs.next()) {
                
                if (id == Integer.parseInt(rs.getString("ID"))) {
                    
                    Flor flor = new Flor(
                            rs.getInt("ID"),
                            rs.getDouble("sepal_length"),
                            rs.getDouble("sepal_width"),
                            rs.getDouble("petal_length"),
                            rs.getDouble("petal_width"),
                            rs.getString("class")
                    );

                    return flor;
                }
            }
            throw new SQLException("There's no Flower with this ID");
        } catch (SQLException e) {
            System.out.println(e.getMessage());

        }
        return null;
    }
    
    public ArrayList allFlowers(){
        ArrayList<Flor> flowerList = new ArrayList();
        try {
            Statement statement = this.conn.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM FLOWERS");
            
            while (rs.next()) {
                    Flor flower = new Flor(rs.getInt("ID"),
                            rs.getDouble("sepal_length"),
                            rs.getDouble("sepal_width"),
                            rs.getDouble("petal_length"),
                            rs.getDouble("petal_width"),
                            rs.getString("class")
                    );
                    
                    flowerList.add(flower);
                }
           }
        catch (SQLException | ArrayIndexOutOfBoundsException | ArrayStoreException e) {
            System.out.println(e.getMessage());

        }
        return flowerList;
    }
    

}
