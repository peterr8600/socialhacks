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
	private List<JCheckBox> searchVariableCheckBox = new ArrayList<JCheckBox>();
	private static JFrame frame;

    //Specify the look and feel to use.  Valid values:
    //null (use the default), "Metal", "System", "Motif", "GTK+"
    final static String LOOKANDFEEL = "GTK+";

    public Component createComponents() {
        JButton button = new JButton("Confirm");
        button.setMnemonic(KeyEvent.VK_I);
        button.addActionListener(this);
        //label.setLabelFor(button);

		String[] raceEx = {"American Indian/Alaskan Native", "Asian/Pacific Islander", "Black", "Black-Hispanic", "White", "White-Hispanic", "Unknown", "Other"};
		JComboBox raceDropdown = new JComboBox(raceEx);
		for(int i = 0; i < raceEx.length; i++){
			raceCheckBox.add(new JCheckBox(raceEx[i]));
			raceCheckBox.get(i).setSelected(false);
		}
		
		String[] boroughEx = {"Bronx", "Brooklyn", "Manhattan", "Queens", "Staten Island"};
		JComboBox boroughDropdown = new JComboBox(boroughEx);
		for(int i = 0; i < boroughEx.length; i++){
			boroughCheckBox.add(new JCheckBox(boroughEx[i]));
			boroughCheckBox.get(i).setSelected(false);
		}
		
		String[] searchVariableEx = {"conFound", "conPistol", "conRifle", "conAssaultWeap", "conKnife", "conMachineGun", "conOther", "pfUsed", "pfHands", "pfWall", "pfGround", "pfWeapDrawn", "pfWeapPoint", "pfBaton", "pfHandcuff", "pfPepperSpray", "pfOther", "wasArrested", "wasSummoned", "wasFrisked", "wasSearched"};
		JComboBox searchVariableDropdown = new JComboBox(searchVariableEx);
		for(int i = 0; i < searchVariableEx.length; i++){
			searchVariableCheckBox.add(new JCheckBox(searchVariableEx[i]));
			searchVariableCheckBox.get(i).setSelected(false);
		}
		
        /*
         * An easy way to put space between a top-level container
         * and its contents is to put the contents in a JPanel
         * that has an "empty" border.
         */
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
		String[] boroughArray = new String[raceInfo.size()];
		boroughArray = raceInfo.toArray(boroughArray);
		
		List<String> searchVariableInfo = new ArrayList<String>();
		for (JCheckBox checkBox : searchVariableCheckBox) {
			if (checkBox.isSelected()) {
				searchVariableInfo.add(checkBox.getText());
			}
		}
		String[] searchVariableArray = new String[searchVariableInfo.size()];
		searchVariableArray = searchVariableInfo.toArray(searchVariableArray);
		
		try{
			//SFVisualizerTwo SFVTwo = new SFVisualizerTwo();
			//SFVTwo.drawData(StdDraw.BLACK, 10);
			SFVisualizer.visualizeData(raceArray, boroughArray, searchVariableArray);
		} catch(IOException ioe){
			System.out.println("something");
		}
		
		/*ImageIcon image = new ImageIcon("image/image0.png");
		ImageIcon image1 = new ImageIcon("image/image1.png");
		JLabel label = new JLabel("", image, JLabel.CENTER);
		JLabel label1 = new JLabel("", image1, JLabel.CENTER);
		JPanel panel = new JPanel(new BorderLayout());
		panel.add( label, BorderLayout.CENTER );*/
		//SFV.main(new String[0]);
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
        //Set the look and feel.
        initLookAndFeel();

        //Make sure we have nice window decorations.
        JFrame.setDefaultLookAndFeelDecorated(true);

        //Create and set up the window.
        frame = new JFrame("GUI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GUI app = new GUI();
        Component contents = app.createComponents();
        frame.getContentPane().add(contents, BorderLayout.CENTER);

        //Display the window.
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        //Schedule a job for the event-dispatching thread:
        //creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
}
