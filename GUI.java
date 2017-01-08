/*
 * GUI.java is a 1.4 example that requires
 * no other files.
 */
import javax.swing.*;          
import java.awt.*;
import java.awt.event.*;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class GUI implements ActionListener {
    private static String labelPrefix = "Number of button clicks: ";
    private int numClicks = 0;
    final JLabel race = new JLabel("Race");
    final JLabel borough = new JLabel("Borough");
    final JLabel searchVariable = new JLabel("Search Variable");
    private List<JCheckBox> raceCheckBox = new ArrayList<JCheckBox>();
    private List<JCheckBox> boroughCheckBox = new ArrayList<JCheckBox>();
    private List<JCheckBox> searchVariableCheckBox =
	new ArrayList<JCheckBox>();
    private static JFrame frame;

    final static String LOOKANDFEEL = "System";

    public Component createComponents() {
        JButton button = new JButton("Confirm");
        button.setMnemonic(KeyEvent.VK_I);
        button.addActionListener(this);
	String[] raceEx = {"American Indian/Alaskan Native", "Asian/Pacific Islander", "Black", "Black-Hispanic", "White", "White-Hispanic", "Unknown", "Other"};
	for(int i = 0; i < raceEx.length; i++){
	    raceCheckBox.add(new JCheckBox(raceEx[i]));
	    raceCheckBox.get(i).setSelected(false);
	}
	
	String[] boroughEx = {"Bronx", "Brooklyn", "Manhattan", "Queens", "Staten Island"};
	for(int i = 0; i < boroughEx.length; i++){
	    boroughCheckBox.add(new JCheckBox(boroughEx[i]));
	    boroughCheckBox.get(i).setSelected(false);
	}
	
	String[] searchVariableEx = {"conFound", "conPistol", "conRifle", "conAssaultWeap", "conKnife", "conMachineGun", "conOther", "pfUsed", "pfHands", "pfWall", "pfGround", "pfWeapDrawn", "pfWeapPoint", "pfBaton", "pfHandcuff", "pfPepperSpray", "pfOther", "wasArrested", "wasSummoned", "wasFrisked", "wasSearched"};
	for(int i = 0; i < searchVariableEx.length; i++){
	    searchVariableCheckBox.add(new JCheckBox(searchVariableEx[i]));
	    searchVariableCheckBox.get(i).setSelected(false);
	}
	
        JPanel pane = new JPanel(new GridLayout(0, 1));
	
        pane.add(race);
	for(int i = 0; i < raceCheckBox.size(); i++){
	    pane.add(raceCheckBox.get(i));
	}
	
	pane.add(borough);
	for(int i = 0; i < boroughCheckBox.size(); i++){
	    pane.add(boroughCheckBox.get(i));
	}
	
	pane.add(searchVariable);
	for(int i = 0; i < searchVariableCheckBox.size(); i++){
	    pane.add(searchVariableCheckBox.get(i));
	}
	
        pane.add(button);
        pane.setBorder(BorderFactory.createEmptyBorder(
						       30, //top
						       30, //left
						       10, //bottom
						       30) //right
		       );
	
        return pane;
    }
    
    public void actionPerformed(ActionEvent e){
	
        List<String> raceInfo = new ArrayList<String>();
	for (JCheckBox checkBox : raceCheckBox) {
	    if (checkBox.isSelected()) {
		raceInfo.add(checkBox.getText());
	    }
	}
	String[] raceArray = new String[raceInfo.size()];
	raceArray = raceInfo.toArray(raceArray);
	
	List<String> boroughInfo = new ArrayList<String>();
	for (JCheckBox checkBox : boroughCheckBox) {
	    if (checkBox.isSelected()) {
		boroughInfo.add(checkBox.getText());
	    }
	}
	String[] boroughArray = new String[boroughInfo.size()];
	boroughArray = boroughInfo.toArray(boroughArray);
	
	List<String> searchVariableInfo = new ArrayList<String>();
	for (JCheckBox checkBox : searchVariableCheckBox) {
	    if (checkBox.isSelected()) {
		searchVariableInfo.add(checkBox.getText());
	    }
	}
	String[] searchVariableArray = new String[searchVariableInfo.size()];
	searchVariableArray = searchVariableInfo.toArray(searchVariableArray);
	
	try{
	    SFVisualizer.visualizeData(raceArray, boroughArray, 
				       searchVariableArray);
	} catch(IOException ioe){
	    System.out.println("something");
	}
    }
    
    private static void initLookAndFeel() {
        String lookAndFeel = null;
	
        if (LOOKANDFEEL != null) {
            if (LOOKANDFEEL.equals("Metal")) {
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("System")) {
                lookAndFeel = UIManager.getSystemLookAndFeelClassName();
            } else if (LOOKANDFEEL.equals("Motif")) {
                lookAndFeel = "com.sun.java.swing.plaf.motif.MotifLookAndFeel";
            } else if (LOOKANDFEEL.equals("Windows")) {
                lookAndFeel = "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
            } else if (LOOKANDFEEL.equals("GTK+")) { //new in 1.4.2
                lookAndFeel = "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
            } else {
                System.err.println("Unexpected value of LOOKANDFEEL specified: "
                                   + LOOKANDFEEL);
                lookAndFeel = UIManager.getCrossPlatformLookAndFeelClassName();
            }
	    
            try {
                UIManager.setLookAndFeel(lookAndFeel);
            } catch (ClassNotFoundException e) {
                System.err.println("Couldn't find class for specified look and feel:"
                                   + lookAndFeel);
                System.err.println("Did you include the L&F library in the class path?");
                System.err.println("Using the default look and feel.");
            } catch (UnsupportedLookAndFeelException e) {
                System.err.println("Can't use the specified look and feel ("
                                   + lookAndFeel
                                   + ") on this platform.");
                System.err.println("Using the default look and feel.");
            } catch (Exception e) {
                System.err.println("Couldn't get specified look and feel ("
                                   + lookAndFeel
                                   + "), for some reason.");
                System.err.println("Using the default look and feel.");
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Create the GUI and show it.  For thread safety,
     * this method should be invoked from the
     * event-dispatching thread.
     */
    private static void createAndShowGUI() {
        initLookAndFeel();
	
        JFrame.setDefaultLookAndFeelDecorated(true);
	
        frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
        GUI app = new GUI();
        Component contents = app.createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);
	
        frame.pack();
        frame.setVisible(true);
    }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
	    });
    }
}
