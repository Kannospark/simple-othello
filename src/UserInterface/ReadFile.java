package UserInterface;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;

/**
     * Java读取文件夹下的所有文件名和文件
     * @author Younger
     *
     */
    public class ReadFile {

        public static void main() {
            Player.hm = getFilesDatas(Player.filePath);
        }

        /**
         * 获取某文件夹下的文件名和文件内容,存入map集合中
         * @param filePath 需要获取的文件的 路径
         * @return 返回存储文件名和文件内容的map集合
         */
        public static HashMap<String, Player> getFilesDatas(String filePath){
            HashMap<String, Player> files = new HashMap<>();
            File file = new File(filePath); //需要获取的文件的路径
            String[] fileNameLists = file.list(); //存储文件名的String数组
            File[] filePathLists = file.listFiles(); //存储文件路径的String数组
            for(int i=0;i<filePathLists.length;i++){
                if(filePathLists[i].isFile()){
                    try {//读取指定文件路径下的文件内容
                        Player fileDatas = readFile(filePathLists[i]);
                        //把文件名作为key,文件内容为value 存储在map中
                        files.put(fileNameLists[i], fileDatas);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
            return files;
        }

        /**
         * 读取指定目录下的文件
         * @param path 文件的路径
         * @return 文件内容
         * @throws IOException
         */
        public static Player readFile(File path) throws IOException{
            //创建一个输入流对象
            InputStream is = new FileInputStream(path);
            //定义一个缓冲区
            byte[] bytes = new byte[1024];// 1kb
            //通过输入流使用read方法读取数据
            int len = is.read(bytes);
            //System.out.println("字节数:"+len);
            Player str = null;
            while(len!=-1){
                //把数据转换为字符串
                                                  // str = new Player(bytes, 0, len);
                //System.out.println(str);
                //继续进行读取
                len = is.read(bytes);
            }
            //释放资源
            is.close();
            return str;
        }

}
