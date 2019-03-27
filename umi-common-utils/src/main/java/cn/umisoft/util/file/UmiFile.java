package cn.umisoft.util.file;

import java.io.File;

/**
 * @description: <p>文件操作帮助类</p>
 * @author: hujie@umisoft.cn
 * @date: 2019/3/26 11:53 PM
 */
public class UmiFile {
    public static String addSeparator(String path) {
        if (path.endsWith(java.io.File.separator)) {
            return path;
        }
        return path + java.io.File.separator;
    }
}
