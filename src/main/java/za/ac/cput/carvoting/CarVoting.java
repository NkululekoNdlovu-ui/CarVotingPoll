/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package za.ac.cput.carvoting;

import java.sql.SQLException;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author DELL
 */
public class CarVoting {

    public static void main(String[] args) throws SQLException {
       ClientAppGUI runClient = new ClientAppGUI();
       
       runClient.pack();
       runClient.setSize(500,500);
       runClient.setGui();
       runClient.setVisible(true);
       runClient.setDefaultCloseOperation(EXIT_ON_CLOSE);
       
       Client clientCon = new Client();
       clientCon.communicateWihServer();
       
    }
}
