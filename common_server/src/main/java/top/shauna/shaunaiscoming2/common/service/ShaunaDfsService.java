package top.shauna.shaunaiscoming2.common.service;

import top.shauna.dfs.kingmanager.bean.INodeDirectory;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Author Shauna.Chou
 * @Date 2020/11/2 15:37
 * @E-Mail z1023778132@icloud.com
 */
public interface ShaunaDfsService {
    boolean uploadFile(String filePath, byte[] data) throws IOException;

    ByteBuffer downloadFile(String filePath);

    boolean mkdir(String dirPath);

    boolean rmFile(String filePath);

    boolean rmDir(String dirPath);

    INodeDirectory getDir(String dirPath);
}
