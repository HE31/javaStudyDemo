package com.teradata.nio;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * @author joonwhee
 * @date 2019/3/22
 */
public class NIOTest {

    public static void main(String args[]) throws Exception {


       long l = System.currentTimeMillis();
       final String fileName = "D:\\FileTest\\write_buffer_writer_100_0000.txt";
        final String sortedFileName = "D:\\FileTest\\sorted_buffer_writer_100_0000.txt";
        Runtime r = Runtime.getRuntime();
        r.gc();
        // 开始时的剩余内存
        long startMem = r.freeMemory();
        //假数据文件写入
        // bufferWriter(fileName);
        //文件读取，并根据key值按自然顺序排序
      //  Map<String, Integer> stringIntegerMap = bufferReader(fileName);
       // System.out.println("map长度："+stringIntegerMap.size());
        //写入排好序的文件
       // bufferWriter(stringIntegerMap,sortedFileName);
        //读取指定行
        for (int i = 0; i <2_0000 ; i++) {

            String line = bufferReaderLine(sortedFileName, i);
            System.out.println("行号"+i+": "+line);
        }

        long l1 = System.currentTimeMillis();
        long l2 = startMem - Runtime.getRuntime().freeMemory();
        System.out.println("耗时："+((l1-l))+"ms,内存占用："+(l2/1024/1024)+"M");


    }



    /**
     * NIO进行写入
     * @throws IOException
     */
    private static void writeNIO() throws IOException {
        FileOutputStream fos = new FileOutputStream("D:\\FileTest\\write.txt", true);
        FileChannel channel = fos.getChannel();

        StringBuffer content = new StringBuffer();

            for (int i = 0; i <100_0000 ; i++) {
                content.append(i+",17826800154").append(System.lineSeparator());
            }


        byte[] bytes = new byte[1024];
        ByteBuffer buf = ByteBuffer.wrap(content.toString().getBytes());

        buf.put(content.toString().getBytes());
        buf.flip();
        channel.write(buf);
        channel.close();
        fos.close();

    }

    public static void bufferWriter(String fileName){

      //  try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"),1024)) {
        try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileName),StandardCharsets.UTF_8)) {
          Random random = new Random();
            for (int i = 0; i <500_0000 ; i++) {
                bufferedWriter.write((long)(Math.random()*(20000000000L-10000000000L))+10000000000L+"、"+random.nextInt(20));
                bufferedWriter.newLine();
            }
        }catch (Exception e){

        }

    }

    /**
     * hashMap
     */
    public static void bufferWriter(Map<String,Integer> map,String fileName){
        //   BufferedReader bufferedReader = null;
      //  try(BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fileName),"UTF-8"),1024);) {
            try(BufferedWriter bufferedWriter = Files.newBufferedWriter(Paths.get(fileName),StandardCharsets.UTF_8)) {
            //写入流,设置缓存区大小为1024K

            Iterator<Map.Entry<String, Integer>> iterator = map.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, Integer> next = iterator.next();
                bufferedWriter.write(next.getKey()+"、"+next.getValue());
                bufferedWriter.newLine();
            }
        }catch (Exception e){
        }

    }


    public static Map<String, Integer> bufferReader(String fileName){


        Map<String,Integer> map = new TreeMap<>();
        //try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName),"UTF-8"),1024)) {
      try(BufferedReader bufferedReader = Files.newBufferedReader(Paths.get(fileName),StandardCharsets.UTF_8)) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] strings = line.split("、");
                map.put(strings[0],Integer.parseInt(strings[1]));

            }
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
        return map;
    }

    public static String bufferReaderLine(String fileName,int lineNum) {
        String line =null;
        try (Stream<String> lines = Files.lines(Paths.get(fileName),StandardCharsets.UTF_8)) {
          line = lineNum>0?lines.skip(lineNum).findFirst().get():lines.findFirst().get();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return line;
      /*  String line = null;
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(fileName), "UTF-8"), 1024)) {
            line = bufferedReader.readLine();
            return line;
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
        return line;*/
    }
    private void multiFileMerge(List<String> fileNames){

        for (String fileName:fileNames){
        //    bufferReader()
        }

    }

}