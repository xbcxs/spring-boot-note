package com.example.markdown.build;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据blog目录机构生成对应Markdown标题结构
 *
 * @author Xiao
 */
public class MarkdownTitleBuilder {

    private static final Logger logger = LoggerFactory.getLogger(MarkdownTitleBuilder.class);

    public static void main(String[] args) throws Exception {
        MarkdownTitleBuilder mtb = new MarkdownTitleBuilder();
        mtb.build();
    }

    public MarkdownTitleBuilder() {
    }

    /**
     * 构建readme.md
     *
     * @throws IOException
     */
    private void build() throws IOException {
        String rootPath = new ClassPathResource("").getFile().getParentFile().getParentFile().getParent();
        String readmePath = rootPath + File.separator + "readme.md";
        generateReadme(readmePath, generateTitle(new File(rootPath)));
    }

    /**
     * 根据文件目录结构生成对应MarkDown标题结构
     *
     * @param rootFile
     * @throws UnsupportedEncodingException
     */
    private List<String> generateTitle(File rootFile) {
        List blogLists = new ArrayList<String>();
        File[] files = rootFile.listFiles();
        if (files != null) {
            int i = 0;
            for (File sonFile : files) {
                if (sonFile.isDirectory() && sonFile.getName().startsWith("spring-boot-note-")) {
                    String relativeFilePath = sonFile.getName() + File.separator + "readme.md";
                    relativeFilePath = relativeFilePath.replace(File.separator, "/");
                    /* relativeFilePath = URLEncoder.encode(relativeFilePath, "utf-8").replaceAll("\\+", "%20"); */
                    blogLists.add(formatTitle(++i, this.getReadmeFirstLine(sonFile.getPath() + File.separator + "readme.md"), relativeFilePath));
                }
            }
        }
        return blogLists;
    }


    /**
     * 格式化标题
     *
     * @param fileName
     * @param relativeFilePath
     * @return
     */
    private String formatTitle(int num, String fileName, String relativeFilePath) {
        return new StringBuffer(String.valueOf(num)).append(". [").append(fileName).append("](").append("https://github.com/xbcxs/spring-boot-note/blob/master/").append(relativeFilePath).append(")").toString();
    }

    /**
     * 将contentList内容写入filePath对应的文件
     *
     * @param filePath
     * @param contentList
     */
    private void generateReadme(String filePath, List<String> contentList) {
        FileOutputStream fos;
        BufferedWriter bw = null;
        try {
            fos = new FileOutputStream(new File(filePath));
            bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (String content : contentList) {
                bw.write(content);
                bw.newLine();
                logger.debug("写入内容:{}", content);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 读取文件第一行字符
     * @param path
     */
    private String getReadmeFirstLine(String path) {
        String title = "标题未定义";
        BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(new File(path)));
            title = br.readLine().split("#")[1].trim();
        } catch (IOException e) {
            logger.error("获取各子模块readme.md文件第一行标题字符，请检查标题是否符合：在第一行[# 标题示例]");
        } finally {
            if(br != null){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return title;
    }

}
