package UserInterface;

import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class Player {
    static int Pnt;
    private int id;
    private String name;
    private Icon icon;
    public static HashMap<String,Player> hm = new HashMap<String,Player>();
    public static String filePath = "C:/Users/DELL/IdeaProjects/CS102Afinal/newFile";
    boolean userExist = false;

    public Player(String name) throws IOException {
        Pnt++;
        id = Pnt;
        this.name = name;
        hm.put(name,this);
        saveDataToFile(name);
    }

    public String getName(){
        return this.name;
    }

    private void saveDataToFile(String userName) throws IOException {

        System.out.println("Your name:" + userName);
        File dir = new File(filePath);
        // 一、检查放置文件的文件夹路径是否存在，不存在则创建
        if (!dir.exists()) {
            dir.mkdirs();// mkdirs创建多级目录
        }
        File checkFile = new File(filePath+"/" + userName + ".zzx");
        FileWriter writer = null;
        try {
            // 二、检查目标文件是否存在，不存在则创建
            if (!checkFile.exists()) {
                userExist = true;
                checkFile.createNewFile();// 创建目标文件
            }
            // 三、向目标文件中写入内容
            // FileWriter(File file, boolean append)，append为true时为追加模式，false或缺省则为覆盖模式
            writer = new FileWriter(checkFile, true);
            writer.append(userName);
            writer.append("\n");
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (null != writer)
                writer.close();
        }
    }
}
