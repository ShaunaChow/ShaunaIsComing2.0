package top.shauna.shaunaiscoming2.common.dao.impl;

import org.springframework.stereotype.Component;
import top.shauna.dfs.ClientStarter;
import top.shauna.dfs.kingmanager.bean.INodeDirectory;
import top.shauna.dfs.service.ClientService;
import top.shauna.rpc.bean.FoundBean;
import top.shauna.rpc.bean.RegisterBean;
import top.shauna.rpc.config.PubConfig;
import top.shauna.shaunaiscoming2.common.dao.ShaunaDfsDao;

import java.io.IOException;
import java.nio.ByteBuffer;

/**
 * @Author Shauna.Chou
 * @Date 2020/11/2 15:28
 * @E-Mail z1023778132@icloud.com
 */
@Component
public class ShaunaDfsDaoImpl implements ShaunaDfsDao {
    private ClientService clientService;

    public ShaunaDfsDaoImpl(){
        preparePubConfig();
        clientService = ClientStarter.getClientService();
    }

    @Override
    public boolean uploadFile(String filePath, byte[] data) throws IOException {
        return clientService.uploadFile(filePath,data);
    }

    @Override
    public ByteBuffer downloadFile(String filePath) {
        return clientService.downloadFile(filePath);
    }

    @Override
    public boolean mkdir(String dirPath) {
        return clientService.mkdir(dirPath);
    }

    @Override
    public boolean rmFile(String filePath) {
        return clientService.rmFile(filePath);
    }

    @Override
    public boolean rmDir(String dirPath) {
        return clientService.rmDir(dirPath);
    }

    @Override
    public INodeDirectory getDir(String dirPath) {
        return clientService.getDir(dirPath);
    }

    private void preparePubConfig() {
        PubConfig pubConfig = PubConfig.getInstance();
        pubConfig.setThreadPoolNums(10);
        pubConfig.setTimeout(100000L);
        if (pubConfig.getRegisterBean() == null) {
            RegisterBean registerBean = new RegisterBean("zookeeper", "39.105.89.185:2181", null);
            pubConfig.setRegisterBean(registerBean);
        }
        if (pubConfig.getFoundBean() == null) {
            RegisterBean registerBean = pubConfig.getRegisterBean();
            FoundBean foundBean = new FoundBean(
                    registerBean.getPotocol(),
                    registerBean.getUrl(),
                    registerBean.getLoc()
            );
            pubConfig.setFoundBean(foundBean);
        }
    }
}
