package mainPackage;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.DecimalFormat;
import javax.swing.JButton;
import javax.swing.JPanel;

/*
 *
 * @author tushart12
 */
public class Teacher extends JPanel implements Runnable, MouseListener{
    
    int width, height;
    Thread t = null;
    boolean threadSuspended;
    int electronClicked;
    int nucleusClicked;
    int fontSize;
    int leftTitle;
    boolean pause;
    int mX;
    int useless;
    int mY;
    Font font;
    Translate trs;
    JButton nextButton;
    JButton prevButton;
    JButton pButton;
    int stage;
    Integer time;
    int pStage;
    int pTime;
    int count;
    int eX;
    int eY;
    int selectedElement;
    int selectedRow;
    int selectedColoumn;
    int currentElement;
    int angle;
    Integer diaX;
    Integer diaY;
    Integer temp,rem;
    Integer noofshells;
    int noofphase[] = {0,1,2,2,5,5,5,5,4,4};
    int startElement[][] = {{},{1},{3,7},{11,15},{19,23,27,31,35},
                            {37,41,45,49,53},{55,74,78,82,86},{87,106,110,114,118},{57,61,65,69},{89,93,97,101}};
    int phaseElements[][]= {{},{2},{4,4},{4,4},{4,4,4,4,2},{4,4,4,4,2},
    {4,4,4,4,1},{4,4,4,4,1},{4,4,4,3},{4,4,4,3}};
    int phase;
    int electronPoint[][];
    //int shells[] = {2, 8, 18, 32};
    int ovalPoint[][] = {{340,240},{310,210},{280,180},{250,150},{220,120},{190,90},{160,60},{130,90}};
    int ovalLength[] = {120,180,240,300,360,420,480,540};
    int subShellIndex[]  = {0,0,1,0,1,0, 2,1,0, 2,1,0, 3, 2,1,0, 3, 2,1};
    int electronShells[] = {2,2,6,2,6,2,10,6,2,10,6,2,14,10,6,2,14,10,6};
    int newShells[]      = {1,2,2,3,3,4, 3,4,5, 4,5,6, 4, 5,6,7, 5, 6,7};
    String nameSubShells[][]  = {{"1s2"},{"2s2"},{"2p6","3s2"},{"3p6","4s2"},{"3d10","4p6","5s2"}}; 
    int currentShells[]  = {0,0,0,0,0,0,0};
    int currentSubShells[][] = {{0},{0,0},{0,0,0},{0,0,0,0},{0,0,0,0},{0,0,0},{0,0}};
    double angleSpeed[]     = {7,(double)7/2,(double)7/3,(double)7/4,(double)7/5,(double)7/6,1};
    
    String shellNames[]  = {"K", "L", "M", "N", "O", "P", "Q", "R"};
    String subShellNames[][] = 
    {{"1s2"},{"2s2","2p6"},{"3s2","3p6","3d10"},{"4s2","4p6","4d10","4f14"},
        {"5s2","5p6","5d10","5f14"},{"6s2","6p6","6d10"},{"7s2","7p6"}};
    String coloumnName[] = {"","IA","IIA","IIIB","IVB","VB","VIB","VIIB",
    "VIIIB","VIIIB","VIIIB","IB","IIB","IIIA","IVA","VA","VIA","VIIA","VIIIA"};
    int initialAngle;
    int completed = 0;
    int valenceShell[][] = {{0},{1},{2},{9,2},{10,2},{11,2},{12,2},{13,2},{14,2},
    {15,2},{16,2},{18,1},{2},{3},{4},{5},{6},{7},{8}};
    Integer electronShell[];
    int arrow[][] = {{80, 160, 150, 120}, {80, 190, 150, 150},
                {80, 220, 210, 150},{80, 250, 210, 180},{80, 280, 270, 180},
                {80, 310, 270, 210},{80, 340, 330, 210},{140, 340, 330, 240}};
    int shelled[][] = {{2},{2},{6,2},{6,2},{10,6,2}};
    int pY[] = {110,150,190,230,270,310,350,390,430,470,510,550};
    int pX[] = { 20, 60,100,140,180,220,260,300,340,380,
                    420,460,500,540,580,620,660,700,740,780};
    String elementSymbols[] = {"","H","He","Li","Be","B","C","N","O","F","Ne","Na",
        "Mg","Al","Si","P","S","Cl","Ar","K","Ca","Sc","Ti","V","Cr","Mn","Fe",
        "Co","Ni","Cu","Zn","Ga","Ge","As","Se","Br","Kr","Rb","Sr","Y","Zr","Nb",
        "Mo","Tc","Ru","Rh","Pd","Ag","Cd","In","Sn","Sb","Te","I","Xe","Cs",
        "Ba","La","Ce","Pr","Nd","Pm","Sm","Eu","Gd","Tb","Dy","Ho","Er","Tm",
        "Yb","Lu","Hf","Ta","W","Re","Os","Ir","Pt","Au","Hg","Tl","Pb","Bi","Po",
        "At","Rn","Fr","Ra","Ac","Th","Pa","U","Np","Pu","Am","Cm","Bk","Cf",
        "Es","Fm","Md","No","Lr","Rf","Db","Sg","Bh","Hs","Mt","Ds","Rg","Uub",
        "Uut","Uuq","Uup","Uuh","Uus","Uuo"};
    
    Integer elementPoint[][] = {{},{60,100,150,190},{740,780,150,190},{60,100,190,230},
    {100,140,190,230},{540,580,190,230},{580,620,190,230},{620,660,190,230},
    {660,700,190,230},{700,740,190,230},{740,780,190,230},{60,100,230,270},
    {100,140,230,270},{540,580,230,270},{580,620,230,270},{620,660,230,270},
    {660,700,230,270},{700,740,230,270},{740,780,230,270},{60,100,270,310},
    {100,140,270,310},{140,180,270,310},{180,220,270,310},{220,260,270,310},
    {260,300,270,310},{300,340,270,310},{340,380,270,310},{380,420,270,310},
    {420,460,270,310},{460,500,270,310},{500,540,270,310},{540,580,270,310},
    {580,620,270,310},{620,660,270,310},{660,700,270,310},{700,740,270,310},
    {740,780,270,310},{60,100,310,350},{100,140,310,350},{140,180,310,350},
    {180,220,310,350},{220,260,310,350},{260,300,310,350},{300,340,310,350},
    {340,380,310,350},{380,420,310,350},{420,460,310,350},{460,500,310,350},
    {500,540,310,350},{540,580,310,350},{580,620,310,350},{620,660,310,350},
    {660,700,310,350},{700,740,310,350},{740,780,310,350},{60,100,350,390},
    {100,140,350,390},{100,140,470,510,},{140,180,470,510},{180,220,470,510},
    {220,260,470,510},{260,300,470,510},{300,340,470,510},{340,380,470,510},
    {380,420,470,510},{420,460,470,510},{460,500,470,510},{500,540,470,510},
    {540,580,470,510},{580,620,470,510},{620,660,470,510},{660,700,470,510},
    {180,220,350,390},{220,260,350,390},{260,300,350,390},{300,340,350,390},
    {340,380,350,390},{380,420,350,390},{420,460,350,390},{460,500,350,390},
    {500,540,350,390},{540,580,350,390},{580,620,350,390},{620,660,350,390},
    {660,700,350,390},{700,740,350,390},{740,780,350,390},{60,100,390,430},
    {100,140,390,430},{100,140,510,550},{140,180,510,550},{180,220,510,550},
    {220,260,510,550},{260,300,510,550},{300,340,510,550},{340,380,510,550},
    {380,420,510,550},{420,460,510,550},{460,500,510,550},{500,540,510,550},
    {540,580,510,550},{580,620,510,550},{620,660,510,550},{660,700,510,550},
    {180,220,390,430},{220,260,390,430},{260,300,390,430},{300,340,390,430},
    {340,380,390,430},{380,420,390,430},{420,460,390,430},{460,500,390,430},
    {500,540,390,430},{540,580,390,430},{580,620,390,430},{620,660,390,430},
    {660,700,390,430},{700,740,390,430},{740,780,390,430}};
    
    String elementNames[] = {"","Hydrogen","Helium","Lithium","Berylium","Boron",
        "Carbon","Nitrogen","Oxygen","Fluorine","Neon","Sodium","Magnesium",
        "Aluminium","Silicon","Phosphorus","Sulphur","Chlorine","Argon","Pottasium",
        "Calcium","Scanadium","Titanium","Vanadium","Chromium","Manganese",
        "Iron","Cobalt","Nickel","Copper","Zinc","Galium","Germanium","Arsenic",
        "Selenium","Bromine","Krypton","Rubidium","Strontium","Yttrium","Zirconium",
        "Niobium","Molybdenum","Technetium","Ruthenium","Rhodium","Palladium",
        "Silver","Cadmium","Indium","Tin","Antimony","Tellerium","Iodine","Xenon",
        "Caesium","Barium","Lanthanum","Cerium","Praseodymium","Neodymium",
        "Promethium","Samarium","Europium","Gadolinium","Terbium","Dysprosium",
        "Holmium","Erbium","Thulium","Ytterbium","Lutetium","Hafnium","Tantalum",
        "Tungsten","Rhenium","Osmium","Iridium","Platinum","Gold","Mercury","Thalium",
        "Lead","Bismuth","Polonium","Astatine","Radon","Francium","Radium",
        "Actinium","Thorium","Protactinium","Uranium","Neptunium","Plutonium",
        "Americium","Curium","Berkelium","Californium","Einsteinium","Fermium",
        "Mendelevium","Nobelium","Lawrencium","Rutherfordium","Dubnium","Seaborgium",
        "Bohrium","Hassium","Meitnerium","Darmstadtium","Roentgenium","Ununbium",
        "Ununtrium","Ununquadium","Ununpentium","Ununhexium","Ununseptium","Ununoctium"};
    
    Float massNumber[] = {0.0f,1.008f,4.003f,6.941f,9.012f,10.81f,12.01f,14.01f,16.0f,
    19.0f,20.18f,22.99f,24.31f,26.98f,28.09f,30.97f,32.07f,35.45f,39.95f,39.10f,40.08f,
    44.96f,47.88f,50.94f,52.0f,54.94f,55.85f,58.93f,58.69f,63.55f,65.39f,69.72f,72.61f,
    74.92f,98.96f,79.90f,83.80f,85.47f,87.62f,88.91f,91.22f,92.91f,95.94f,98.91f,101.1f,
    102.9f,106.4f,107.9f,112.4f,114.8f,118.7f,121.8f,127.6f,126.9f,131.3f,132.9f,137.3f,
    138.9f,140.1f,140.9f,144.2f,146.9f,150.4f,152.0f,157.3f,158.9f,162.5f,164.9f,167.3f,
    168.9f,173.0f,175.0f,178.5f,180.9f,183.8f,186.2f,190.2f,192.2f,195.1f,197.0f,200.6f,
    204.4f,207.2f,209.0f,209.0f,210.0f,222.0f,223.0f,226.0f,227.0f,232.0f,231.0f,238.0f,237.0f,
    244.1f,243.1f,247.1f,247.1f,251.1f,252.0f,257.1f,258.1f,259.1f,262.1f,261.1f,262.1f,
    263.1f,264.1f,265.1f,268.0f,269.0f,272.0f,285.0f,284.0f,289.0f,288.0f,292.0f,0.0f,294.0f};
    
    int coloumnNumber[][] = {{},{60,100,110,150},{100,140,110,150},{140,180,110,150},
    {180,220,110,150},{220,260,110,150},{260,300,110,150},{300,340,110,150},
    {340,380,110,150},{380,420,110,150},{420,460,110,150},{460,500,110,150},
    {500,540,110,150},{540,580,110,150},{580,620,110,150},{620,660,110,150},
    {660,700,110,150},{700,740,110,150},{740,780,110,150}}; 
    
    int rowNumber[][] = {{},{20,60,150,190},{20,60,190,230},{20,60,230,270},
    {20,60,270,310},{20,60,310,350},{20,60,350,390},{20,60,390,430},
    {20,60,470,510},{20,60,510,550},};
    /*
     * @param args the command line arguments
     */
    
    public Teacher(String param)
    {
        setSize(800,600);
        setLayout(null); 
        System.out.println("init(): begin");
        setBackground( Color.black );
        setPreferredSize(new Dimension(800, 600));
        setMaximumSize(this.getPreferredSize());
        setMinimumSize(this.getPreferredSize());        
        trs = new Translate();
        
        nextButton = new JButton(trs.TranslateString("NEXT"));
        prevButton = new JButton(trs.TranslateString("PREVIOUS"));
        pButton    = new JButton(trs.TranslateString("PAUSE"));
        
        prevButton.setBackground(Color.LIGHT_GRAY);
        pButton.setBackground(Color.LIGHT_GRAY);
        nextButton.setBackground(Color.LIGHT_GRAY);
        prevButton.setBounds(220, 2, 120, 30);
        pButton.setBounds(340, 2, 120, 30);
        nextButton.setBounds(460, 2, 120, 30);
        add(prevButton);
        add(pButton);
        add(nextButton);
        

        nextButton.addActionListener(new ActionListener() {

                @Override
                public void actionPerformed(ActionEvent ae) {
                    if(stage==1)
                    {
                        stage = 2;
                        time = 37;
                        diaX=diaY=0;
                        fontSize = 36;
                    }
                    else if(stage==2)
                    {
                        stage = 3;
                    }
                    else if(stage==3)
                    {
                        stage = 4;
                        time = 161;
                        rem = 38;
                        count = 0;
                    }
                    else if(stage==4||stage==6)
                        stage = 5;
                    else if(stage==5)
                    {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        time = 701;
                        stage = 8;
                    }
                    else if(stage==7)
                    {
                        if(phase<noofphase[selectedRow])
                            phase++;
                        else
                            stage = 5;
                        
                    }
                    else if(stage == 8)
                    {
                        stage = 9;
                        repaint();
                    }
                    if(stage!=9)
                    repaint();
                           
                }
            });
        prevButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                    if(stage==2)
                    {
                        stage = 1;
                        time = 1; 
                        fontSize = 1;
                        leftTitle = 370;
                        setBackground(Color.BLACK);
                    }
                    else if(stage==3)
                    {
                        stage = 2;
                        time = 37;
                        diaX=diaY=0;
                    }
                    else if(stage==4)
                    {
                        stage = 3;
                    }
                    else if(stage==5)
                    {
                        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                        stage = 4;
                        time = 161;
                        rem = 38;
                        count = 0;
                    }
                    else if(stage==6||stage==8)
                        stage = 5;
                    else if(stage==7)
                    {
                        if(phase>1)
                            phase--;
                        else
                            stage = 5;
                    }
                    else if(stage==9)
                    {
                        time = 701;
                        stage = 8;
                    }
                    repaint();
            }
        });
        
        pButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!pause)
                {
                    pButton.setText(trs.TranslateString("PLAY"));
                    pause = true;
                }
                else
                {
                    pButton.setText(trs.TranslateString("PAUSE"));
                    pause = false;
                }
            }
        });
        
        this.addMouseListener(this);
        
        System.out.println("start(): begin");

        initialAngle = 0;
        noofshells = 7;
        temp = 0;
        useless = 0;
        count = 0;
        rem = 38;
        stage = 1;
        time = 1;
        fontSize = 1;
        leftTitle = 370;
        selectedElement = 0;
        electronShell = null;
        electronPoint = null;
        electronClicked = -1;
        nucleusClicked = -1;
        pause = false;
        diaX=0;
        diaY=0;
        mX = 370;
        mY = 240;
        
        int i = Integer.parseInt(param);
        if(i>1){
            stage=i-1;
            nextButton.doClick();
        }
        
        width = getSize().width;
        height = getSize().height;
        System.out.println("init(): end");
        
        System.out.println("start(): begin");
        if ( t == null ) {
            System.out.println("start(): creating thread");
            t = new Thread( this );
            System.out.println("start(): starting thread");
            threadSuspended = false;
            t.start();
        }
        else {
            if ( threadSuspended ) {
                threadSuspended = false;
                System.out.println("start(): notifying thread");
                synchronized( this ) {
                notify();
                }
            }
        }
        System.out.println("start(): end");
    }
    
    @Override
    public void mouseClicked(MouseEvent event){
        Integer x = event.getX();
        Integer y = event.getY();
        Integer left,top,right,down;
        Integer temp1,temp2,temp3,temp4;
        if(stage==5)
        {
            left=top=right=down = 0;
            for(int i=0;i<pX.length;i++)
            {
                if(((x-pX[i])>0)&&((x-pX[i])<40))
                {
                    left = pX[i];
                    right = pX[i+1];
                }
            }
            for(int i=0;i<pY.length;i++)
            {
                if(((y-pY[i])>0)&&((y-pY[i])<40))
                {
                    top = pY[i];
                    down = pY[i+1];
                }
            }
            
            selectedElement = 0;
            for(int i=1;i<elementPoint.length;i++)
            {
                temp1 = elementPoint[i][0];
                temp2 = elementPoint[i][1];
                temp3 = elementPoint[i][2];
                temp4 = elementPoint[i][3];                
                if(left.equals(temp1)&&right.equals(temp2)&&top.equals(temp3)&&down.equals(temp4))
                {
                    selectedElement = i;
                    stage++;
                    repaint();
                }
            }
            
            if(selectedElement == 0)
            {
                selectedRow = 0;
                selectedColoumn = 0;
                for(int i=1;i<coloumnNumber.length;i++)
                {
                    temp1 = coloumnNumber[i][0];
                    temp2 = coloumnNumber[i][1];
                    temp3 = coloumnNumber[i][2];
                    temp4 = coloumnNumber[i][3];
                    if(left.equals(temp1)&&right.equals(temp2)&&top.equals(temp3)&&down.equals(temp4))
                    {
                        selectedColoumn = i;
                        selectedRow = 0;
                        stage = 7;
                        repaint();
                    }
                }
                if(selectedColoumn==0)
                {
                    for(int i=1;i<rowNumber.length;i++)
                    {
                        temp1 = rowNumber[i][0];
                        temp2 = rowNumber[i][1];
                        temp3 = rowNumber[i][2];
                        temp4 = rowNumber[i][3];
                        if(left.equals(temp1)&&right.equals(temp2)&&top.equals(temp3)&&down.equals(temp4))
                        {
                            selectedRow = i;
                            selectedColoumn = 0;
                            stage = 7;
                            repaint();
                        }
                    }
                }
            }
        }
        
        if(stage==6&&electronPoint!=null)
        {
            for(int i=0;i<electronPoint.length;i++)
            {
                if(x>electronPoint[i][0]&&x<electronPoint[i][1]&&
                        y>electronPoint[i][2]&&y<electronPoint[i][3])
                {
                    electronClicked = i;
                    repaint();
                }
            }
            if(((x-380)<40)&&((y-280)<40)&&((x-420)>-40)&&((y-320)>-40))
            {
                nucleusClicked = 1;
                repaint();
            }
        }
    }
    
    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
    
    
    // Executed within the thread that this applet created.
    @Override
    public void run() {
        System.out.println("run(): begin");
        try {
            while (true) {
                System.out.println("run(): awake");
                // Here's where the thread does some work
                if(!pause)
                {
                    if(stage==1 && fontSize<=36)
                    {
                        pButton.setEnabled(true);
                        prevButton.setEnabled(false);
                        fontSize++;
                        time++;
                        leftTitle-=7;
                        repaint();
                    }

                    else if(stage==2 && time<161)
                    {
                        pButton.setEnabled(true);
                        prevButton.setEnabled(true);
                        if(time==50 || time==100 )
                        {
                            repaint();
                            time++;
                        }
                        else if(time>=140)
                        {
                            diaX += 10;
                            diaY += 10;
                            repaint();
                            time++;
                        }
                        else
                            time++;

                    }

                    else if(stage==3)
                    {
                        pButton.setEnabled(true);
                        initialAngle = (initialAngle+1)%360;
                        repaint();
                    }
                    else if(stage==4&&time<701)
                    {
                        pButton.setEnabled(true);
                        if(time==300)
                        {
                            repaint();
                        }
                        if(time==400||time==500||time==600)
                        {
                            count++;
                            repaint();
                        }
                        if(time==700)
                        {
                            count++;
                            repaint();
                        }
                        time++;
                    }

                    else if(stage==8&&time<951)
                    {
                        pButton.setEnabled(true);
                        if(time==750||time==800||time==850||time==900||time==950)
                            repaint();
                        time++;
                    }
                    else 
                        pButton.setEnabled(false);
                }
                // Now the thread checks to see if it should suspend itself
                if ( threadSuspended ) {
                synchronized( this ) {
                    while ( threadSuspended ) {
                        System.out.println("run(): waiting");
                        wait();
                    }
                }
                }
                System.out.println(time);
                System.out.println("run(): requesting repaint");
                System.out.println("run(): sleeping");
                Thread.sleep( 41 );  // interval given in milliseconds
            }
        }
        catch (InterruptedException e) { }
        System.out.println("run(): end");
    }
    
    @Override
    public void paint( Graphics g ) {
        System.out.println("paint()");
        g.clearRect(0, 0, 800, 600);
        switch(stage)
        {
            case 1:
                stageOne(g);
                break;
            case 2:
                stageTwo(g);
                break;
            case 3:
                stageThree(g);
                break;
            case 4:
                stageFour(g);
                break;
            case 5:
                stageFive(g);
                break;
            case 6:
                stageSix(g);
                break;
            case 7:
                stageSeven(g);
                break;
            case 8:
                stageEight(g);
                break;
            case 9:
                stageNine(g);
                break;

        }    
    }
    
    public void drawTranslatableString( Graphics g, String str , int x, int y)
    {
        String write = trs.TranslateString(str);
        g.drawString(write,x,y);
    }
    
    public void drawTranslatableDigit( Graphics g, Integer str, int x, int y){
        
        String write = trs.TranslateDigit(str);
        g.drawString(write,x,y);
        
    }
    
    public void drawTranslatableDouble( Graphics g, Double str , int x, int y){
        
        String write = trs.TranslateDouble(str);
        g.drawString(write,x,y);
        
    }
    
    public void stageOne ( Graphics g ){
        
        super.paint(g);
        g.setColor( Color.green );
        setBackground(Color.BLACK);
        g.setFont(new Font("Verdana", Font.PLAIN, fontSize));
        drawTranslatableString(g,"Atoms and Molecules Tutorial", leftTitle, 300);
    }

    public void stageTwo ( Graphics g ){
        
        setBackground(new Color(238,238,238));
        super.paint(g);
        g.setColor( Color.BLACK );
        g.setFont(new Font("Verdana", Font.PLAIN, 36));
        drawTranslatableString(g,"The Introduction", 240, 80);
        
	if(time>49&&time<140)
        {
            mX = 300;
            mY = 200;
            g.setColor( Color.BLUE);
            for(int i=0;i<10;i++)
            {
                mX = 300;
                for(int j=0;j<10;j++)
                {
                    g.fillOval(mX, mY, 10, 10);
                    mX += 10;
                }
                mY += 10;
            }
            g.setColor(Color.BLACK);
            g.drawLine(400, 300, 450, 350);
            g.drawLine(400, 300, 400, 320);
            g.drawLine(400, 300, 420, 300);
            g.drawRect(299, 199, 102, 102);
            g.setFont(new Font("Verdana", Font.BOLD, 16));
            drawTranslatableString(g,"A substance is a", 455, 355);
            drawTranslatableString(g,"collection of MOLECULES", 445, 375);
            if(time>99)
            {
                g.setFont(new Font("Verdana", Font.PLAIN, 26));
                drawTranslatableString(g,"Let's take a closer look", 100, 400);
                drawTranslatableString(g,"at a single molecule", 100, 430);
                g.drawOval(300, 200, 10, 10);
                g.drawOval(299, 199, 12, 12);
                g.drawOval(298, 198, 14, 14);
            }  
            mX = 300;
            mY = 200;
        }

        if(time>=140&&time<=161)
        {
            g.setColor( Color.BLUE);
            g.fillOval(300, 200, diaX, diaY);
            
            if(time==161)
            {
                g.setColor(Color.RED);
                mY = 230;
                int h=405, k=305;
                for(mY = 200;mY<410; mY+=10)
                {
                    for(mX=300;mX<510; mX+=10)
                    {
                        temp = (int) Math.sqrt(Math.pow((mX-h+5),2)+Math.pow((mY-k+5),2));
                        if(temp < 100)
                            g.fillOval(mX, mY, 10, 10);
                    }
                }
                g.setColor(Color.BLACK);
                g.setFont(new Font("Verdana", Font.BOLD, 16));
                drawTranslatableString(g,"This is the molecule", 200, 500);
                g.drawLine(275, 480, 350, 400);
                g.drawLine(275, 481, 350, 401);
                drawTranslatableString(g,"These are the atoms", 500, 150);
                g.drawLine(590, 160, 456, 264);
                g.drawLine(590, 161, 456, 265);
                drawTranslatableString(g,"Atom", 100, 200);
                drawTranslatableString(g,"Molecule", 100, 242);
                g.setColor(Color.BLUE);
                g.fillOval(50, 220, 30, 30);
                g.setColor(Color.RED);
                g.fillOval(60, 190, 10, 10);
                g.fillOval(53, 223, 24, 24);
                g.setColor(Color.BLACK);
                
            }
        }        
    }

    public void stageThree( Graphics g )
    {
        setBackground(new Color(238,238,238));
        super.paint(g);
        g.setColor( Color.BLACK );
        g.setFont(new Font("Verdana", Font.PLAIN, 36));
        drawTranslatableString(g,"The Atom", 50, 80);
        g.setColor(Color.BLACK);
        g.setColor(Color.RED);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        drawTranslatableString(g,"NUCLEUS = ", 500, 530);
        g.setColor(Color.BLACK);
        drawTranslatableString(g,"PROTONS + NEUTRONS", 595, 530);
        drawTranslatableString(g,"ELECTRONS", 500, 550);
        g.drawLine(539, 515, 404, 310);
        g.drawLine(540, 515, 405, 310);
        g.drawLine(495, 540, 400, 390);
        g.drawLine(496, 540, 401, 390);
        animateAtom(g, 118 );
        
    }
    
    public void calculate( Integer atomicNumber )
    {
        temp = atomicNumber;
        int i;
        for(i=0;i<currentShells.length;i++)
            currentShells[i] = 0;
        i = 0;            
        while(temp>0)
        {
            if(temp>=electronShells[i])
            {
                currentShells[newShells[i]-1] += electronShells[i];
                currentSubShells[newShells[i]-1][subShellIndex[i]] = electronShells[i];
            }
            else
            {
                currentShells[newShells[i]-1] += temp;
                currentSubShells[newShells[i]-1][subShellIndex[i]] = temp;
            }

            temp -= electronShells[i];
            i++;
        }
        /*
         * Exceptions
         */
        if(atomicNumber==24||atomicNumber==29)
        {
            currentShells[2] = atomicNumber-11;
            currentShells[3] = 1;
        }
        else if(atomicNumber==41||atomicNumber==42||atomicNumber==44||atomicNumber==45)
        {
            currentShells[3] = atomicNumber-29;
            currentShells[4] = 1;
        }
        else if(atomicNumber==46||atomicNumber==47)
        {
            currentShells[3] = 18;
            currentShells[4] = atomicNumber-46;
        }
        else if(atomicNumber==57||atomicNumber==58)
        {
            currentShells[3] = atomicNumber-39;
            currentShells[4] = 9;
        }
        else if(atomicNumber==64)
        {
            currentShells[3] = 25;
            currentShells[4] = 9;
        }
        else if(atomicNumber==78||atomicNumber==79)
        {
            currentShells[4] = atomicNumber-61;
            currentShells[5] = 1;
        }
        else if(atomicNumber==89)
        {
            currentShells[4] = 18;
            currentShells[5] = 9;
        }
        else if(atomicNumber==90||atomicNumber==91||atomicNumber==92||atomicNumber==93)
        {
            currentShells[4] = atomicNumber-72;
            currentShells[5] = 10;
        }
        else if(atomicNumber==96)
        {
            currentShells[4] = 25;
            currentShells[5] = 9;
        }
        else if(atomicNumber==103)
        {
            currentShells[5] = 8;
            currentShells[6] = 3;
        }
    }
    
    public void drawAtom( Graphics g, Integer atomicNumber , int x, int y, int size, boolean showData)
    {
        g.setColor(Color.RED);
        g.fillOval(x-(2*size),y-(2*size),4*size,4*size);
        g.setColor(Color.BLUE);
        
        mX = x; mY = y; width = 12*size;
        for(int i=0;i<currentShells.length;i++)
        {
            if(currentShells[i]>0)
            {
                g.drawOval(mX-(width/2),mY-(width/2),width,width);
                g.drawOval(mX-(width/2)+1,mY-(width/2)+1,width-2,width-2);
                width += 6*size;
            }
        }
        
        int l=0;
        electronPoint = null;
        electronShell = null;
        electronPoint = new int[atomicNumber][4];
        electronShell = new Integer[atomicNumber];
        width = 12*size;
        g.setColor(Color.yellow);
        for(int k=0;k<currentShells.length;k++)
        {
            if(currentShells[k]>0)
            {
                    angle = (int) Math.floor((double)360/currentShells[k]);
                    for(int j=0,m=0;m<currentShells[k];j+=angle,m++)
                    {
                        mX = x + (int) ((width/2)*(Math.cos(Math.toRadians(j))));
                        mY = y + (int) ((width/2)*(Math.sin(Math.toRadians(j))));
                        g.fillOval(mX-(size/2), mY-(size/2), size, size);
                        if(showData)
                        {                           
                            electronShell[l] = k+1;
                            electronPoint[l][0] = mX-5;
                            electronPoint[l][1] = mX+5;
                            electronPoint[l][2] = mY-5;
                            electronPoint[l++][3] = mY+8;
                        }
                    }
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Verdana", Font.BOLD, 14));
                    if(showData)
                    drawTranslatableString(g,shellNames[k], x+(width/2)+2, y-3);
                    width += 6*size;
                    g.setColor(Color.YELLOW);
            }
        }
        if(showData)
        {
            g.setColor(Color.BLACK);
            g.setFont(new Font("Verdana", Font.BOLD, 14));
            g.drawString(elementNames[atomicNumber], 40, 450);
            drawTranslatableString(g,"Atomic Number(Z) : "+atomicNumber,10, 470);
            drawTranslatableString(g,"Protons(Z)             : "+atomicNumber,10, 510);
            drawTranslatableString(g,"Electrons(Z)          : "+atomicNumber,10, 550);
            int mass = Math.round(massNumber[atomicNumber]);
            if(mass!=0)
            {
                drawTranslatableString(g,"Mass Number  (A)  : "+massNumber[atomicNumber],10, 490);
                drawTranslatableString(g,"Neutrons(A-Z)       : "+(mass-atomicNumber),10, 530);
            }
            else
                drawTranslatableString(g,"Mass Number  (A)  : No Stable Isotope",10, 490);
        }
    }
    
    public void animateAtom( Graphics g, Integer atomicNumber )
    {
        temp = atomicNumber;
        g.setColor(Color.RED);
        g.fillOval(380,280,40,40);
        g.setColor(Color.BLUE);
        int i=0;
        while(temp>0)
        {
            g.drawOval(ovalPoint[newShells[i]-1][0],ovalPoint[newShells[i]-1][1],ovalLength[newShells[i]-1],ovalLength[newShells[i]-1]);
            g.drawOval(ovalPoint[newShells[i]-1][0]-1,ovalPoint[newShells[i]-1][1]-1,ovalLength[newShells[i]-1]+2,ovalLength[newShells[i]-1]+2);
            temp = temp - electronShells[i];
            i++;
        }
        calculate(atomicNumber);
        g.setColor(Color.yellow);
        for(int k=0;k<currentShells.length;k++)
        {
            if(currentShells[k]>0)
            {
                    angle = (int) Math.floor((double)360/currentShells[k]);
                    for(double j=initialAngle*angleSpeed[k],m=0;m<currentShells[k];j+=angle,m++)
                    {
                        mX = (ovalPoint[k][0]+(ovalLength[k]/2)) + (int) ((ovalLength[k]/2)*(Math.cos(Math.toRadians(j))));
                        mY = (ovalPoint[k][1]+(ovalLength[k]/2)) + (int) ((ovalLength[k]/2)*(Math.sin(Math.toRadians(j))));
                        g.fillOval(mX-5, mY-5, 10, 10);
                    }
                    g.setColor(Color.BLACK);
                    g.setFont(new Font("Verdana", Font.BOLD, 14));
                    g.drawString(shellNames[k], ovalPoint[k][0]+ovalLength[k]+2, 295);
                    g.setColor(Color.YELLOW);
            }
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        g.drawString(elementNames[atomicNumber], 40, 450);
        drawTranslatableString(g,"Atomic Number(Z) : "+atomicNumber,10, 470);
        drawTranslatableString(g,"Protons(Z)             : "+atomicNumber,10, 510);
        drawTranslatableString(g,"Electrons(Z)          : "+atomicNumber,10, 550);
        int mass = Math.round(massNumber[atomicNumber]);
        drawTranslatableString(g,"Mass Number  (A)  : "+massNumber[atomicNumber],10, 490);
        drawTranslatableString(g,"Neutrons(A-Z)       : "+(mass-atomicNumber),10, 530);
    }

    public void stageFour( Graphics g )
    {
        setBackground(new Color(238,238,238));
        super.paint(g);
        g.setFont(new Font("Verdana", Font.PLAIN, 36));
        drawTranslatableString(g,"Electronic Configuration", 200, 80);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.PLAIN, 18));
        mX = 50; mY = 150;
        for(int i=0;i<subShellNames.length;i++)
        {
            mX = 100;
            for(int j=0;j<subShellNames[i].length;j++)
            {
                g.drawString(subShellNames[i][j], mX, mY);
                if(j==2)
                    mX += 60;
                else
                    mX += 50;
            }
            mY += 30;
        }
        
        drawTranslatableString(g,"Let's Take an Example, Strontium Z:38", 370,150);
        drawTranslatableString(g,"To write down the electronic Configuration", 370, 180);
        drawTranslatableString(g,"Just follow the arrows on the left side", 370, 210);
        mX = 370;
        mY = 240;
        
        if(time>=200)
        {
            for(int i=0;i<=count;i++)
            {
                for(int j=0;j<shelled[i].length;j++)
                {
                    if(i==count)
                        rem -= shelled[i][j];
                    g.drawString(nameSubShells[i][j], mX, mY);
                    if(i==4 && j==0)
                        mX +=50;
                    else
                        mX+=40;
                }
                drawArrow(g, arrow[i][0], arrow[i][1], arrow[i][2], arrow[i][3]);
            }
        }
        if(rem==0)
            g.setColor(Color.RED);
        g.drawRect(90, 348, 235, 30);
        drawTranslatableString(g,"Remaining Electrons: "+rem.toString(), 100, 370);
        if(time==701)
        {
            g.setFont(new Font("Verdana", Font.BOLD, 14));
            g.setColor(Color.BLUE);
            g.drawLine(500, 270, 700, 270);
            g.drawString("4f", 710, 275);
            g.drawLine(500, 300, 700, 300);
            g.drawString("4d", 710, 305);
            g.drawLine(500, 330, 700, 330);
            g.drawString("4p", 710, 335);
            g.drawLine(500, 375, 700, 375);
            g.drawString("4s", 710, 380);
            
            g.drawLine(440, 325, 500, 270);
            g.drawLine(440, 325, 500, 300);
            g.drawLine(440, 325, 500, 330);
            g.drawLine(440, 325, 500, 375);
            
            g.drawLine(400, 325, 440, 325);
            
            g.drawString("4", 360, 330);
            
            g.setColor(Color.GREEN);
            g.drawLine(500, 360, 700, 360);
            g.drawString("3d", 710, 365);
            g.drawLine(500, 390, 700, 390);
            g.drawString("3p", 710, 395);
            g.drawLine(500, 420, 700, 420);
            g.drawString("3s", 710, 425);
            
            g.drawLine(440, 390, 500, 360);
            g.drawLine(440, 390, 500, 390);
            g.drawLine(440, 390, 500, 420);
            
            g.drawLine(400, 390, 440, 390);
            
            g.drawString("3", 360, 395);
            
            g.setColor(Color.ORANGE);
            g.drawLine(500, 450, 700, 450);
            g.drawString("2p", 710, 455);
            g.drawLine(500, 510, 700, 510);
            g.drawString("2s", 710, 515);
            
            g.drawLine(440, 480, 500, 450);
            g.drawLine(440, 480, 500, 510);
            
            g.drawLine(400, 480, 440, 480);
            
            g.drawString("2", 360, 485);
            
            g.setColor(Color.RED);
            g.drawLine(400, 540, 700, 540);
            g.drawString("1s", 710, 545);
            g.drawString("1", 360, 545);
            
            g.setFont(new Font("Verdana", Font.PLAIN, 20));
            drawTranslatableString(g,"The Energy Level Diagram", 30, 500);
            
        }
        
    }
    
    public void drawArrow(Graphics g, int x1, int y1, int x2, int y2)
    {
        g.drawLine(x1,y1,x2,y2);
        g.drawLine(x1,y1,x1+5,y1);
        g.drawLine(x1,y1,x1+3,y1-5);
    }
    
    public void stageFive( Graphics g )
    {
        setBackground(new Color(238,238,238));
        super.paint(g);
        g.setColor( Color.BLACK );
        g.setFont(new Font("Verdana", Font.PLAIN, 36));
        drawTranslatableString(g,"Try It Yourself", 260, 80);
        
        g.setFont(new Font("Verdana", Font.BOLD, 14)); 
        
        for(Integer i=1;i<=9;i++)
        {
            g.setColor(new Color(59,89,182));
            g.fillRect(rowNumber[i][0]+1, rowNumber[i][2]+1, 39, 39);
            g.setColor(Color.WHITE);
            drawTranslatableDigit(g, i,rowNumber[i][0]+15,rowNumber[i][3]-10);
        }
        
        for(Integer i=1;i<=18;i++)
        {
            g.setColor(new Color(59,89,182));
            g.fillRect(coloumnNumber[i][0]+1, coloumnNumber[i][2]+1, 39, 39);
            g.setColor(Color.WHITE);
            drawTranslatableDigit(g, i, coloumnNumber[i][0]+10,coloumnNumber[i][3]-15);
        }
        
        g.setColor(new Color(200,200,200));
        g.fillRect(elementPoint[1][0]+1, elementPoint[1][2]+1, 39, 39);
        g.setColor(Color.ORANGE);
        g.fillRect(elementPoint[3][0]+1, elementPoint[3][2]+1, 39, 39);
        g.fillRect(elementPoint[11][0]+1, elementPoint[11][2]+1, 39, 39);
        g.fillRect(elementPoint[19][0]+1, elementPoint[19][2]+1, 39, 39);
        g.fillRect(elementPoint[37][0]+1, elementPoint[37][2]+1, 39, 39);
        g.fillRect(elementPoint[55][0]+1, elementPoint[55][2]+1, 39, 39);
        g.fillRect(elementPoint[87][0]+1, elementPoint[87][2]+1, 39, 39);
        
        g.setColor(Color.YELLOW);
        g.fillRect(elementPoint[4][0]+1, elementPoint[4][2]+1, 39, 39);
        g.fillRect(elementPoint[12][0]+1, elementPoint[12][2]+1, 39, 39);
        g.fillRect(elementPoint[20][0]+1, elementPoint[20][2]+1, 39, 39);
        g.fillRect(elementPoint[38][0]+1, elementPoint[38][2]+1, 39, 39);
        g.fillRect(elementPoint[56][0]+1, elementPoint[56][2]+1, 39, 39);
        g.fillRect(elementPoint[88][0]+1, elementPoint[88][2]+1, 39, 39);
        
        g.setColor(Color.CYAN);
        g.fillRect(elementPoint[13][0]+1, elementPoint[13][2]+1, 39, 39);
        g.fillRect(elementPoint[30][0]+1, elementPoint[30][2]+1, 39, 39);
        g.fillRect(elementPoint[31][0]+1, elementPoint[31][2]+1, 39, 39);
        g.fillRect(elementPoint[32][0]+1, elementPoint[32][2]+1, 39, 39);
        g.fillRect(elementPoint[48][0]+1, elementPoint[48][2]+1, 39, 39);
        g.fillRect(elementPoint[49][0]+1, elementPoint[49][2]+1, 39, 39);
        g.fillRect(elementPoint[50][0]+1, elementPoint[50][2]+1, 39, 39);
        g.fillRect(elementPoint[51][0]+1, elementPoint[51][2]+1, 39, 39);
        g.fillRect(elementPoint[80][0]+1, elementPoint[80][2]+1, 39, 39);
        g.fillRect(elementPoint[81][0]+1, elementPoint[81][2]+1, 39, 39);
        g.fillRect(elementPoint[82][0]+1, elementPoint[82][2]+1, 39, 39);
        g.fillRect(elementPoint[83][0]+1, elementPoint[83][2]+1, 39, 39);
        g.fillRect(elementPoint[84][0]+1, elementPoint[84][2]+1, 39, 39);
        g.fillRect(elementPoint[112][0]+1, elementPoint[112][2]+1, 39, 39);
        g.fillRect(elementPoint[113][0]+1, elementPoint[113][2]+1, 39, 39);
        g.fillRect(elementPoint[114][0]+1, elementPoint[114][2]+1, 39, 39);
        g.fillRect(elementPoint[115][0]+1, elementPoint[115][2]+1, 39, 39);
        g.fillRect(elementPoint[116][0]+1, elementPoint[116][2]+1, 39, 39);
        g.setColor(Color.GREEN);
        g.fillRect(elementPoint[5][0]+1, elementPoint[5][2]+1, 39, 39);
        g.fillRect(elementPoint[6][0]+1, elementPoint[6][2]+1, 39, 39);
        g.fillRect(elementPoint[7][0]+1, elementPoint[7][2]+1, 39, 39);
        g.fillRect(elementPoint[8][0]+1, elementPoint[8][2]+1, 39, 39);
        g.fillRect(elementPoint[9][0]+1, elementPoint[9][2]+1, 39, 39);
        g.fillRect(elementPoint[14][0]+1, elementPoint[14][2]+1, 39, 39);
        g.fillRect(elementPoint[15][0]+1, elementPoint[15][2]+1, 39, 39);
        g.fillRect(elementPoint[16][0]+1, elementPoint[16][2]+1, 39, 39);
        g.fillRect(elementPoint[17][0]+1, elementPoint[17][2]+1, 39, 39);
        g.fillRect(elementPoint[33][0]+1, elementPoint[33][2]+1, 39, 39);
        g.fillRect(elementPoint[34][0]+1, elementPoint[34][2]+1, 39, 39);
        g.fillRect(elementPoint[35][0]+1, elementPoint[35][2]+1, 39, 39);
        g.fillRect(elementPoint[52][0]+1, elementPoint[52][2]+1, 39, 39);
        g.fillRect(elementPoint[53][0]+1, elementPoint[53][2]+1, 39, 39);
        g.fillRect(elementPoint[85][0]+1, elementPoint[85][2]+1, 39, 39);
        g.setColor(new Color(136,170,255));
        g.fillRect(elementPoint[2][0]+1, elementPoint[2][2]+1, 39, 39);
        g.fillRect(elementPoint[10][0]+1, elementPoint[10][2]+1, 39, 39);
        g.fillRect(elementPoint[18][0]+1, elementPoint[18][2]+1, 39, 39);
        g.fillRect(elementPoint[36][0]+1, elementPoint[36][2]+1, 39, 39);
        g.fillRect(elementPoint[54][0]+1, elementPoint[54][2]+1, 39, 39);
        g.fillRect(elementPoint[86][0]+1, elementPoint[86][2]+1, 39, 39);
        g.fillRect(elementPoint[118][0]+1, elementPoint[118][2]+1, 39, 39);
        g.setColor(new Color(221,153,153));
        for(int i=21;i<=29;i++)
        g.fillRect(elementPoint[i][0]+1, elementPoint[i][2]+1, 39, 39);
        for(int i=39;i<=47;i++)
        g.fillRect(elementPoint[i][0]+1, elementPoint[i][2]+1, 39, 39);
        for(int i=72;i<=79;i++)
        g.fillRect(elementPoint[i][0]+1, elementPoint[i][2]+1, 39, 39);
        for(int i=104;i<=111;i++)
        g.fillRect(elementPoint[i][0]+1, elementPoint[i][2]+1, 39, 39);
        
        g.setColor(new Color(255,170,136));
        for(int i=57;i<=71;i++)
        g.fillRect(elementPoint[i][0]+1, elementPoint[i][2]+1, 39, 39);
        
        g.setColor(new Color(221,170,204));
        for(int i=89;i<=103;i++)
        g.fillRect(elementPoint[i][0]+1, elementPoint[i][2]+1, 39, 39);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.PLAIN, 18));       
        g.drawString(elementSymbols[1], pX[1]+2, pY[2]-10);
        g.drawString(elementSymbols[2], pX[18]+2, pY[2]-10);
        g.drawString(elementSymbols[3], pX[1]+2, pY[3]-10);
        g.drawString(elementSymbols[4], pX[2]+2, pY[3]-10);
        g.drawString(elementSymbols[11], pX[1]+2, pY[4]-10);
        g.drawString(elementSymbols[12], pX[2]+2, pY[4]-10);
        
        for(int i=5;i<=10;i++)
            g.drawString(elementSymbols[i], pX[8+i]+2, pY[3]-10);
        for(int i=13;i<=18;i++)
            g.drawString(elementSymbols[i], pX[i]+2, pY[4]-10);
        for(int i=19;i<=36;i++)
            g.drawString(elementSymbols[i], pX[i-18]+2, pY[5]-10);
        for(int i=37;i<=54;i++)
            g.drawString(elementSymbols[i], pX[i-36]+2, pY[6]-10);
        g.drawString(elementSymbols[55], pX[1]+2, pY[7]-10);
        g.drawString(elementSymbols[56], pX[2]+2, pY[7]-10);
        for(int i=72;i<=86;i++)
            g.drawString(elementSymbols[i], pX[i-68]+2, pY[7]-10);
        g.drawString(elementSymbols[87], pX[1]+2, pY[8]-10);
        g.drawString(elementSymbols[88], pX[2]+2, pY[8]-10);
        for(int i=104;i<=118;i++)
            g.drawString(elementSymbols[i], pX[i-100]+2, pY[8]-10);
        for(int i=57;i<=71;i++)
            g.drawString(elementSymbols[i], pX[i-55]+2, pY[10]-10);
        for(int i=89;i<=103;i++)
            g.drawString(elementSymbols[i], pX[i-87]+2, pY[11]-10);
        
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        electronClicked = -1;
        g.setFont(new Font("Verdana", Font.BOLD, 12));
        drawTranslatableString(g,"Click on any element to review it",240,565);
        phase = 1;
    }
    
    public void stageSix( Graphics g )
    {
        setBackground(new Color(238,238,238));
        super.paint(g);
        calculate(selectedElement);
        drawAtom(g, selectedElement, 400, 300, 10, true );
        DecimalFormat form = new DecimalFormat("0.000");
        if(electronClicked!=-1)
        {
            g.setColor(new Color(255,0,0));
            g.setFont(new Font("Verdana", Font.BOLD, 14));
            g.drawString("n ="+electronShell[electronClicked].toString(),20,370);
            Double t1 = (Math.pow(electronShell[electronClicked],2)/(double)selectedElement)*52.9;
            g.drawString("r = "+ form.format(t1) + " pm",20,390);
            
            t1 = (220.0/electronShell[electronClicked]);
            g.drawString("v = " + form.format(t1)+" E+6 m/s",620,500);
            
            t1 = -2.18*(Math.pow(selectedElement,2)/Math.pow(electronShell[electronClicked],2));
            electronClicked = -1;
            g.drawString("E = "+form.format(t1)+" E-18 J",20,410);            
        }
        
        if(nucleusClicked!=-1)
        {
            g.setColor(new Color(255,0,0));
            g.setFont(new Font("Verdana", Font.BOLD, 14));
            float f = (float)(1.25*Math.pow(massNumber[selectedElement], 1.0/3));
            g.drawString("R = "+form.format(f)+" E-15 m",610,160);
            g.setFont(new Font("Verdana", Font.BOLD, 10));
            nucleusClicked = -1;
        }
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        drawTranslatableString(g, "Radius of Nucleus:", 610, 80);
        g.setColor(new Color(184,34,7));
        g.drawString("R = r A", 610, 110);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.drawString("o",646,114);
        g.drawString("1/3",667,105);
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        g.drawString("r = 1.25 x 10    m", 610, 130);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.drawString("o", 613, 135);
        g.drawString("-15", 713, 125);
                
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        drawTranslatableString(g,"Radius Of the Orbit is given by:", 20,80);
        g.drawString("r  =", 20, 120);
        g.drawString("n  h", 80, 110);
        g.drawString("_",98,98);
        g.drawString("___________", 55, 112);
        g.drawString("Zk e m", 70, 130);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.drawString("n",25, 123);
        g.drawString("e",90, 133);
        g.drawString("2",102, 125);
        g.drawString("e",122, 133);
        g.drawString("2",89, 105);
        g.drawString("2",109, 105);
        
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        drawTranslatableString(g, "(or)", 60, 150);
        g.setColor(new Color(184,34,7));
        g.drawString("r  = ",20,180);
        g.drawString("n", 60, 170);
        g.drawString("Z", 60, 190);
        g.drawString("___", 55, 172);
        g.drawString("x52.9 pm",80,180);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.drawString("2", 69, 165);
        g.drawString("n",25, 183);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        drawTranslatableString(g,"Energy of an electron", 20,230);
        drawTranslatableString(g,"is given by:", 20,250);
        g.drawString("E = ", 20, 280);
        g.drawString("Zk e", 75, 270);
        g.drawString("_ _________", 50, 272);
        g.drawString("2r", 85, 290);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.drawString("e", 94, 273);
        g.drawString("2", 108, 265);
        g.drawString("n", 99, 293);
        
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        drawTranslatableString(g, "(or)", 60, 315);
        g.setColor(new Color(184,34,7));
        g.drawString("E  = -2.18x10", 20,340);
        g.drawString("Z",160,325);
        g.drawString("n",160,350);
        g.drawString("J",195,340);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        g.drawString("n",28,343);
        g.drawString("-18", 125, 335);
        g.drawString("2",172,330);
        g.drawString("2",172,345);
        g.setFont(new Font("Verdana", Font.BOLD, 18));
        g.drawString("__",157,332);
        g.setFont(new Font("Verdana", Font.PLAIN, 32));
        g.drawString("(",145,345);
        g.drawString(")",180,345);
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        drawTranslatableString(g, "Velocity of electron", 620, 420);
        drawTranslatableString(g, "at distance r:", 620, 440);
        g.setColor(new Color(184,34,7));
        g.drawString("v = ",620,470);
        g.drawString(" c",685,460);
        g.drawString("n x 137",667,480);
        g.drawString("_________",665,462);
        g.setFont(new Font("Verdana", Font.BOLD, 10));
        
        g.setColor(Color.BLACK);
        g.setFont(new Font("Verdana", Font.BOLD, 12));
        drawTranslatableString(g,"Click on the electron or nucleus for more information",200,568);
    }
    
    public void stageSeven( Graphics g )
    {
        setBackground(new Color(238,238,238));
        super.paint(g);
        setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
        if(selectedColoumn!=0)
        {
            g.setFont(new Font("Verdana", Font.PLAIN, 36));
            drawTranslatableString(g, "Group ", 310, 80);
            g.drawString(coloumnName[selectedColoumn],430, 80);
            g.setFont(new Font("Verdana", Font.BOLD, 14));
            
            for(int i=0;i<currentShells.length;i++)
            {
                currentShells[i] = 0;
            }

            for(int i=0;i<valenceShell[selectedColoumn].length;i++)
            {
                currentShells[i] = valenceShell[selectedColoumn][i];
            }
            drawAtom(g, 0, 400, 300, 10, false);
            
            g.setColor(Color.BLACK);
            g.setFont(new Font("Verdana",Font.BOLD,14));
            drawTranslatableString(g,"The Valence Shell of "+coloumnName[selectedColoumn],300,500);
        }
        else
        {
            g.setFont(new Font("Verdana", Font.PLAIN, 36));
            drawTranslatableString(g,"Period ", 300, 80);
            drawTranslatableDigit(g, selectedRow, 425, 80);
            
            int tX = 150; int tY = 180; int size = 5;
            
            currentElement = startElement[selectedRow][phase-1];
            
            for(int i=0;i<phaseElements[selectedRow][phase-1];currentElement++,i++)
            {
                if(i==2)
                {                    
                    tX  = 150;
                    tY += 250;
                    if(selectedRow==6&&phase==1)
                    {
                        currentElement = 72;
                    }
                    else if(selectedRow==7&&phase==1)
                    {
                        currentElement = 104;
                    }
                }
                calculate(currentElement);
                drawAtom(g, currentElement, tX, tY, size, false);
                g.setColor(Color.BLACK);
                g.drawString(elementNames[currentElement],tX+140,tY);
                tX += 380;
            }
            
            g.setFont(new Font("Verdana", Font.BOLD, 14));
            
        }
    }
    
    public void stageEight( Graphics g )
    {
        setBackground(new Color(238,238,238));
        super.paint(g);
        g.setColor( Color.BLACK );
        g.setFont(new Font("Verdana", Font.PLAIN, 36));
        drawTranslatableString(g,"How Atoms Combine", 200, 80);
        
        g.setColor( Color.BLACK );
        g.setFont(new Font("Verdana", Font.PLAIN, 18));
        drawTranslatableString(g,"Lets take water for example. It is formed by the combination of", 70, 130);
        drawTranslatableString(g,"two H atoms and one O atom", 70, 160);
        g.setFont(new Font("Verdana", Font.BOLD, 14));
        if(time>=750)
        {
            g.setColor(Color.RED);
            g.fillOval(140,210,30,30);
            g.setColor(Color.BLACK);
            g.fillOval(175,210,10,10);
            g.drawOval(175,230,10,10);
            
            g.setColor(Color.BLUE);
            g.fillOval(220,200,50,50);
            g.setColor(Color.BLACK);
            g.fillOval(230,185,10,10);
            g.fillOval(250,185,10,10);
            g.fillOval(275,210,10,10);
            g.fillOval(275,230,10,10);
            g.fillOval(250,255,10,10);
            g.drawOval(230,255,10,10);
            g.fillOval(205,230,10,10);
            g.drawOval(205,210,10,10);
            
            g.setColor(Color.RED);
            g.fillOval(230,300,30,30);
            g.setColor(Color.BLACK);
            g.fillOval(230,285,10,10);
            g.drawOval(250,285,10,10);
            
            g.drawString("H", 150, 230);
            g.drawString("H", 240, 320);
            g.drawString("O", 240, 230);
        }
        if(time>=800)
        {
            g.drawLine(176, 240, 150, 275);
            drawTranslatableString(g,"Electron Hole",100,290);
        }
        if(time>=850)
        {
            g.fillPolygon(new int[]{320,420,410,450,410,420,320}, 
                    new int[]{220,220,200,225,250,230,230}, 7);
        }
        if(time>=900)
        {           
            g.setColor(Color.RED);
            g.fillOval(470,210,30,30);
            g.setColor(Color.BLACK);
            g.fillOval(505,210,10,10);            
            
            g.setColor(Color.BLUE);
            g.fillOval(520,200,50,50);
            g.setColor(Color.BLACK);
            g.fillOval(530,185,10,10);
            g.fillOval(550,185,10,10);
            g.fillOval(575,210,10,10);
            g.fillOval(575,230,10,10);
            g.fillOval(550,255,10,10);
            g.fillOval(505,230,10,10);
            
            g.setColor(Color.RED);
            g.fillOval(530,270,30,30);
            g.setColor(Color.BLACK);
            g.fillOval(530,255,10,10);
            
            g.drawString("H", 480, 230);
            g.drawString("H", 540, 290);
            g.drawString("O", 540, 230);
        }
        if(time>=950)
        {
            g.setColor(Color.RED);
            g.fillOval(325,380,30,30);
            
            g.setColor(Color.BLUE);
            g.fillOval(370,400,50,50);
            
            g.setColor(Color.RED);
            g.fillOval(435,380,30,30);
            
            g.setColor(Color.BLACK);
            g.fillPolygon(new int[]{352,372,373,353},new int[]{405,415,413,403},4);
            g.fillPolygon(new int[]{438,418,417,437},new int[]{405,415,413,403},4);
            
            g.drawString("H", 335, 400);
            g.drawString("H", 445, 400);
            g.drawString("O", 390, 430);
            
            g.drawArc(357,390,75,75,30,120);
            g.drawArc(357,389,75,77,30,120);
            g.drawLine(428, 409, 426, 400); 
            drawTranslatableDigit(g, 120, 380, 385);
            g.setFont(new Font("Verdana",Font.BOLD,10));
            g.drawString("O",410,379);
        }
    }

    public void stageNine( Graphics g )
    {
        setBackground(new Color(238,238,238));
        super.paint(g);
        g.setFont(new Font("Verdana", Font.BOLD, 36));
        drawTranslatableString(g,"The End", 325, 150);
    }
}
