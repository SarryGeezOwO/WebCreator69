import java.awt.Color;
import java.awt.Desktop;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import java.io.*;

public class Creator extends JFrame implements ActionListener {

    JButton browseBtn;
    JButton finishBtn;
    JTextField pathField;
    JTextField folderNameField;

    Creator() {
        ImageIcon folderIMG = new ImageIcon("folder-open (1).png");
        Image img = folderIMG.getImage();
        Image scaledImg = img.getScaledInstance(15, 15, Image.SCALE_SMOOTH);
        folderIMG = new ImageIcon(scaledImg);

        JLabel pathLabel = new JLabel("Path : ");
        pathLabel.setBounds(20, 20, 50, 25);
        pathField = new JTextField("C:\\Users\\zinaa\\Desktop\\Cj Shenanigans\\Website Related");
        pathField.setBounds(60, 20, 470, 25);
        pathField.setMargin(new Insets(0, 3, 0, 3));
        browseBtn = new JButton();
        browseBtn.setIcon(folderIMG);
        browseBtn.setBounds(540, 20, 25, 25);

        JLabel folderNameLabel = new JLabel("Folder Name : ");
        folderNameLabel.setBounds(20, 10, 80, 25);
        folderNameField = new JTextField();
        folderNameField.setBounds(105, 10, 380, 25);
        folderNameField.setMargin(new Insets(0, 3, 0, 3));

        finishBtn = new JButton("Finish");
        finishBtn.setBounds(495, 10, 70, 25);


        JPanel topPanel = new JPanel();
        topPanel.setBounds(0, 0, 600,55);
        JPanel bottomPanel = new JPanel();
        bottomPanel.setBounds(0, 50, 600, 60);

        this.setTitle("Website Creator 9000");
        this.setLayout(null); // fuck you border layout >:(
        this.setSize(600, 150);
        this.setResizable(false);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ImageIcon icon = new ImageIcon("settings.png");
        this.setIconImage(icon.getImage());
        this.getContentPane().setBackground(new Color(0x1c1b3f));
        
        this.add(topPanel);
        this.add(bottomPanel);
        topPanel.add(pathLabel);
        topPanel.add(pathField);
        topPanel.add(browseBtn);
        bottomPanel.add(folderNameLabel);
        bottomPanel.add(folderNameField);
        bottomPanel.add(finishBtn);
        browseBtn.addActionListener(this);
        finishBtn.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==browseBtn) {
            JFileChooser fileChoose = new JFileChooser();
            fileChoose.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
            int response = fileChoose.showOpenDialog(null);
            if(response == JFileChooser.APPROVE_OPTION) {
                pathField.setText(fileChoose.getSelectedFile().getAbsolutePath());
            }
        }
        if(e.getSource()==finishBtn) {
            if(folderNameField.getText().isEmpty()) 
                new ErrorPanel("Folder name can't be Empty");
            else {
                File file = new File(pathField.getText());
                FolderCreator(file.getAbsolutePath());
            }
        }
    }

    void FolderCreator(String path) {
        File folder = new File(path+"\\"+folderNameField.getText());
        Desktop desktop = Desktop.getDesktop();
        if(folder.mkdir()) {
            try {
                desktop.open(folder);
            } catch (IOException e) {
                e.printStackTrace();
            }
            GenerateHTML(folder.getAbsolutePath());
            System.exit(0);
        }
        else {
            new ErrorPanel("Folder already exists...");
        }
    }
    
    void GenerateHTML(String folderPath) {
        File html = new File(folderPath+"\\Index.html");
        File css = new File(folderPath+"\\style.css");
        File js = new File(folderPath+"\\Logic.js");
        try {
            html.createNewFile();
            BufferedWriter htmlWriter = new BufferedWriter(new FileWriter(html));
            htmlWriter.write("<!-- HTML FILE GENERATED USING JAVA ( CJAY ) -->\n");
            htmlWriter.write("<!DOCTYPE HTML>\n<html>\n\t<head>\n\t\t");
            htmlWriter.write("<link rel=\"stylesheet\" href=\"style.css\">\n\t\t");
            htmlWriter.write("<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Poppins\">\n\t\t");
            htmlWriter.write("<link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>\n\t\t");
            htmlWriter.write("<script src=\"Logic.js\" defer></script>\n\t");
            htmlWriter.write("</head>\n\t");
            htmlWriter.write("<body>\n\n\n\t");
            htmlWriter.write("</body>\n</html>");
            htmlWriter.close();
            css.createNewFile();
            BufferedWriter cssWriter = new BufferedWriter(new FileWriter(css));
            cssWriter.write("/* CSS FILE GENERATED USING JAVA ( CJAY ) */\n");
            cssWriter.write("body {\n\tfont-family:'Poppins';\n}");
            cssWriter.write("* {\n\tmargin: 0;\n\tpadding: 0;\n\tbox-sizing: border-box;\n}");
            cssWriter.close();
            js.createNewFile();
            BufferedWriter jsWriter = new BufferedWriter(new FileWriter(js));
            jsWriter.write("// JAVASCRIPT FILE GENERATED USING JAVA ( CJAY )");
            jsWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
