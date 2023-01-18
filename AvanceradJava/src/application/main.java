package application;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;
import java.awt.BorderLayout;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

public class main extends JFrame{
	
	public main() throws Exception {
		ArrayList columnNames = new ArrayList();
		ArrayList data = new ArrayList();
		String url = "jdbc:mysql://localhost:3306/movies";
		String userID = "root";
		String password = "admin123";
		String sql = "SELECT * FROM director, films";
		
		Connection connection = DriverManager.getConnection(url, userID, password);
		Statement statement = connection.createStatement();
		ResultSet resultSet = statement.executeQuery(sql);
		java.sql.ResultSetMetaData metaData = resultSet.getMetaData();
		int columns = metaData.getColumnCount();
		for (int i = 1; i <= columns; i++) {
			columnNames.add(metaData.getColumnName(i));
		}
		while (resultSet.next()) {
			ArrayList row = new ArrayList(columns);
			for(int i = 1; i <= columns; i++) {
				row.add(resultSet.getObject(i));
			}
			data.add(row);
		}
		Vector columnNamesVector = new Vector();
		Vector dataVector = new Vector();
		for (int i = 0; i < data.size(); i++) {
			ArrayList subArray = (ArrayList) data.get(i);
			Vector subVector = new Vector();
			for (int j = 0; j < subArray.size(); j++) {
				subVector.add(subArray.get(j));
			}
			dataVector.add(subVector);
		}
		for (int i = 0; i < columnNames.size(); i++)
		      columnNamesVector.add(columnNames.get(i));
		    JTable table = new JTable(dataVector, columnNamesVector) {
		      public Class getColumnClass(int column) {
		        for (int row = 0; row < getRowCount(); row++) {
		          Object o = getValueAt(row, column);
		          if (o != null) {
		            return o.getClass();
		          }
		        }
		        return Object.class;
		      }
		    };
		    JScrollPane scrollPane = new JScrollPane(table);
		    getContentPane().add(scrollPane);
		    JPanel buttonPanel = new JPanel();
		    getContentPane().add(buttonPanel, BorderLayout.SOUTH);
		  }
		
		public static void main(String[] args) throws Exception {
			main frame = new main();
			frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
			frame.pack();
			frame.setVisible(true);
		
	}
}
