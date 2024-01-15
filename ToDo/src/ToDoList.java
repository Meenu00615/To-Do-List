import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*; //importing abstracting window toolkit
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

class Task extends JPanel{
    //Create a frame(GUI base Window)
    JLabel index;  //instance variable
    JTextField taskName;
    JButton done; //Marking as task done

    //Setting the background color of various component
    Color pink = new Color(255, 161, 161);
    Color green = new Color(188, 226, 158);
    Color doneColor = new Color(233, 119, 119);

     private boolean checked;
     //Task class
     Task(){
         //Constructor for set up the panel size, color and layout manager
        this.setPreferredSize(new Dimension(400, 20));
        this.setBackground(pink);
        this.setLayout(new BorderLayout());
        checked = false;
        index = new JLabel("");
        index.setPreferredSize(new Dimension(20, 20));
        index.setHorizontalAlignment(JLabel.CENTER);
        this.add(index, BorderLayout.WEST);

        taskName = new JTextField("Do ToDo");
        taskName.setBorder(BorderFactory.createEmptyBorder());
        taskName.setBackground(pink);

        this.add(taskName, BorderLayout.CENTER);

        done= new JButton("Done");
        done.setPreferredSize(new Dimension(80, 20));
        done.setBorder(BorderFactory.createEmptyBorder());
        done.setBackground(doneColor);
        done.setFocusPainted(false);

        this.add(done, BorderLayout.EAST);
    }

    public void changeIndex(int num) {
        this.index.setText(num+"");
        this.revalidate();
    }

    public JButton getDone() {
        return done;
    }
    public boolean getState() {
        return checked;
    }



    public void changeState() {
        this.setBackground(green);
        taskName.setBackground(green);
        checked = true;
        revalidate();
    }
}

//List Class
class List extends JPanel {
    Color lightColor = new Color(252, 221, 176);

    List() {
        //vertical spacing
        GridLayout layout = new GridLayout(10, 1);
        layout.setVgap(5);

        this.setLayout(layout);
        this.setPreferredSize(new Dimension(400, 560));
        this.setBackground(lightColor);
    }

    public void updateNumbers() {
        Component[] listItems = this.getComponents();
        for (int i = 0; i < listItems.length; i++) {
            if (listItems[i] instanceof Task) {
                ((Task) listItems[i]).changeIndex(i + 1);
            }
        }
    }

    public void removeTasks() {
        for (Component c : getComponents()) {
            if (c instanceof Task) {
                if (((Task) c).getState()) {
                    remove(c);
                    updateNumbers();
                }

            }
        }
    }
}

//Footer Class
class Footer extends JPanel{
    //A panel at the bottom of the application
    JButton addTask;
    JButton clear;

    Color orange = new Color(233, 133, 128);
    Color lightColor = new Color(252, 221, 176);
    Border emptyBorder = BorderFactory.createEmptyBorder();

    Footer(){
        this.setPreferredSize(new Dimension(400, 60));
        this.setBackground(lightColor);

        addTask = new JButton("+");
        addTask.setBorder(emptyBorder);
        addTask.setFont(new Font("Sans-serif", Font.ITALIC,20));
        addTask.setVerticalAlignment(JButton.BOTTOM);
        addTask.setBackground(orange);
        this.add(addTask);

        this.add(Box.createHorizontalStrut(20));
        clear = new JButton("Clear");
        clear.setFont(new Font("Sans-serif", Font.ITALIC, 20));
        clear.setBorder(emptyBorder);
        clear.setBackground(orange);
        this.add(clear);
    }

    public JButton getNewTask() {
        return addTask;

    }


    public JButton getClear() {
        return clear;
    }
}
//Title Bar - as the ToDoList at the top of the application

class TitleBar extends JPanel{
    Color lightColor = new Color(252, 221, 176);

    TitleBar(){
        this.setPreferredSize(new Dimension(400, 80));
        this.setBackground(lightColor);
        JLabel titleText = new JLabel("ToDo List");
        titleText.setPreferredSize(new Dimension(200, 60));
        titleText.setFont(new Font("Sans-serif", Font.BOLD, 20));
        titleText.setHorizontalAlignment(JLabel.CENTER);
        this.add(titleText);

    }
}

//AppFrame Class
class AppFrame extends JFrame{ //main frame of the application
    private final List list;
    //contain: TitleBar, Footer and List
    private final JButton newTask;
    private final JButton clear;

    AppFrame(){
        this.setSize(300, 500);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);

        TitleBar title = new TitleBar();
        Footer footer = new Footer();
        list = new List();

        this.add(title, BorderLayout.NORTH);
        this.add(footer, BorderLayout.SOUTH);
        this.add(list, BorderLayout.CENTER);

        newTask = footer.getNewTask();
        clear = footer.getClear();

        addListeners();

    }
    //Mouse listener for the new task and clear button
    public void addListeners() {
        newTask.addMouseListener(new MouseAdapter() {

            @override
            public void mousePressed(MouseEvent e) {
                Task task = new Task();
                list.add(task);
                list.updateNumbers();

                task.getDone().addMouseListener(new MouseAdapter(){
                    @override
                    //Used this method to declared in a superclass
                    public void mousePressed(MouseEvent e) {
                        task.changeState();
                        list.updateNumbers();
                        revalidate();
                    }
                });
            }

        });
        clear.addMouseListener(new MouseAdapter() {
            @override
            public void mousePressed(MouseEvent e) {
                list.removeTasks();
                repaint();
            }
        });
    }
}

public class ToDoList{

    public static void main(String[] args) {
        new AppFrame();
    }
}

@interface override{
}
	
		 
			
	































