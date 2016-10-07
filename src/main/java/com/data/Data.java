package com.data;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * Created by Administrator on 2016/9/25.
 */
public class Data {

    public static void main(String[] args) {
        int i = 0;
        String[] swap = null;
        Data d = new Data();
        Iterator<String[]> bf = d.getFileData("src/main/resources/filedata.txt", "|");
        while (bf.hasNext()) {
            swap = bf.next();
            for (String s : swap) {
                System.out.print(s + i++);
            }
            System.out.println();
        }
    }

    /**
     * getFileData  获取文件中数据，返回Iterator<String[]>
     * fileurl:文件路径     separator:分隔符
     * 当分隔符为  点(.)或者 竖线(|) 必须使用转意字符 变成  \\.   \\|  的格式  否则String.split()会出错
     */
    public Iterator<String[]> getFileData(String fileurl, String separator) {
        separator = "\\" + separator;
        BufferedReader bufferedReader = null;
        String bufferedReaderSwap = null;
        Iterator<String[]> iterator = null;
        try {
            bufferedReader = new BufferedReader(new FileReader(fileurl));
            Collection<String[]> continer = new ArrayList<String[]>();

            while ((bufferedReaderSwap = bufferedReader.readLine()) != null) {
                continer.add(bufferedReaderSwap.split(separator));
            }
            iterator = continer.iterator();
            return iterator;
        } catch (Exception e) {
            e.printStackTrace();
            return iterator;
        } finally {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
                return iterator;
            }
        }
    }

    /**
     * getData() 从数据库获取需要的数据
     */
    public void getDBData() {

    }

}
