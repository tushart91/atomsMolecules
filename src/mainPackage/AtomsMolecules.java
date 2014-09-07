package mainPackage;

import javax.swing.JApplet;

/**
 *
 * @author tushart12    
 */
public class AtomsMolecules extends JApplet{
    
    Teacher panel;
    
    @Override
    public void init()
    {
        String inputFromPage = this.getParameter("stage");
        
        if (inputFromPage == null) 
            inputFromPage = "0";
        
        panel = new Teacher(inputFromPage);
        add(panel);
        setSize(800,600);
        setVisible(true);
    }
}
