import java.sql.*;

public class Main {

    private static String connectionString = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;databaseName=meteostation;SendStringParametersAsUnicode=true;encrypt=true;trustServerCertificate=true";

    private static String user = "sa";

    private static String password = "0000";


    public static void main(String[] args) throws Exception{

        execSql();

    }

    private static void execSql() throws Exception
    {

        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver").newInstance();

        //ResultSet result;



        //Statement statement

        try (Connection connection = DriverManager.getConnection(connectionString, user, password))
        {
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select id, provider, averageTemperature, averagePressure, averageHumidity, averagePrecipitationLevel from regions");

            while (result.next())
            {
                Integer id = result.getInt("id");
                String name = result.getString("provider");
                Double avgTemp = result.getDouble("averageTemperature");
                Double avgPress = result.getDouble("averagePressure");
                Double avgHum = result.getDouble("averageHumidity");
                Double avgPL = result.getDouble("averagePrecipitationLevel");

                System.out.println("id "+id+" name "+name+" avg temp "+avgTemp+" avg press "+avgPress+" avg hum "+avgHum+" avg p l "+avgPL);
            }
            result.close();

        }
        catch (SQLException ex)
        {
            ex.printStackTrace();
        }


    }


}