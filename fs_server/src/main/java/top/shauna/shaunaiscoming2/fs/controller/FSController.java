package top.shauna.shaunaiscoming2.fs.controller;

import net.sf.jmimemagic.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import top.shauna.shaunaiscoming2.common.bean.INodeBean;
import top.shauna.shaunaiscoming2.common.bean.MessageBean;
import top.shauna.shaunaiscoming2.common.bean.User;
import top.shauna.dfs.kingmanager.bean.INode;
import top.shauna.dfs.kingmanager.bean.INodeDirectory;
import top.shauna.shaunaiscoming2.common.service.ShaunaDfsService;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author Shauna.Chow
 * @Date 2020/11/25 22:12
 * @E-Mail z1023778132@icloud.com
 */
@Controller
public class FSController {
    @Autowired
    private ShaunaDfsService shaunaDfsService;

    @GetMapping("/download")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public ResponseEntity<byte[]> download(String filePath, String id, String clazz){
        /** Cache待添加 **/
        String path = "/"+clazz+"/"+id+"/"+filePath;

        ByteBuffer byteBuffer = shaunaDfsService.downloadFile(path);

        byte[] array = byteBuffer.array();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename="+filePath);

        return new ResponseEntity<>(array,httpHeaders,HttpStatus.OK);
    }

    @PostMapping("/upload")
    @PreAuthorize("hasAnyAuthority('1','2')")
    public String upload(HttpSession session, MultipartFile file, String filePath){
        try {
            String path = "/"+session.getAttribute("clazz")+"/"+session.getAttribute("id")+"/"+filePath;
            if (shaunaDfsService.uploadFile(path, file.getBytes())) {
                return "success";
            }else{
                return "failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }

    @RequestMapping("/downloadtmp")
    public ResponseEntity<byte[]> downloadtmp(String filePath,HttpSession session){
        /** Cache待添加 **/

        String path = getPath(filePath,session);

        if (!path.startsWith("/")){
            path = "/"+path;
        }

        ByteBuffer byteBuffer = shaunaDfsService.downloadFile(path);

        byte[] array = byteBuffer.array();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename="+filePath);

        return new ResponseEntity<>(array,httpHeaders,HttpStatus.OK);
    }

    @RequestMapping("/rmfiletmp")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('1','2')")
    public String rmFile(String filePath,HttpSession session){

        String path = getPath(filePath,session);

        if (shaunaDfsService.rmFile(path)){
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/rmdirtmp")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('1','2')")
    public String rmDir(String filePath, HttpSession session){
        String path = getPath(filePath,session);

        if (shaunaDfsService.rmDir(path)){
            return "success";
        }else{
            return "error";
        }
    }

    @RequestMapping("/mkdirtmp")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('1','2')")
    public String mkDir(String filePath, HttpSession session){
        String path = getPath(filePath,session);

        if (shaunaDfsService.mkdir(path)){
            return "success";
        }else{
            return "error";
        }
    }

    @PostMapping("/uploadtmp")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('1','2')")
    public String uploadTmp(MultipartFile file, String filePath, HttpSession session){
        try {
            String path = getPath(filePath,session);

            if (!path.startsWith("/")){
                path = "/"+path;
            }

            if (shaunaDfsService.uploadFile(path, file.getBytes())) {
                return "success";
            }else{
                return "failed";
            }
        } catch (IOException e) {
            e.printStackTrace();
            return "error";
        }
    }



    @GetMapping("/getDir")
    @ResponseBody
    @PreAuthorize("hasAnyAuthority('1','2')")
    public MessageBean getDir(String path, HttpSession session){
        try{
            path = getPath(path,session);

            INodeDirectory dir = shaunaDfsService.getDir(path);
            List<INodeBean> tmp = new ArrayList<>();
            for (INode iNode : dir.getChildren()) {
                if (iNode.getStatus()<0) {
                    continue;
                }
                if (iNode.isDirectory()){
                    tmp.add(new INodeBean(1,iNode.getName(),0));
                }else{
                    tmp.add(new INodeBean(0,iNode.getName(),0));    /**这里还要加一个文件长度**/
                }
            }
            return new MessageBean(200, tmp);
        }catch (Exception e){
            e.printStackTrace();
            return new MessageBean(400, e.getMessage());
        }
    }

    @RequestMapping("/info")
    public ResponseEntity<Object> info(String filePath, HttpSession session){
        String path = getPath(filePath,session);

        if (!path.startsWith("/")){
            path = "/"+path;
        }

        ByteBuffer byteBuffer = shaunaDfsService.downloadFile(path);

        byte[] array = byteBuffer.array();

        String type = null;
        try {
            Magic parser = new Magic() ;
            MagicMatch match = parser.getMagicMatch(array,false);
            type = match.getMimeType();
        } catch (MagicParseException e) {
            e.printStackTrace();
        } catch (MagicMatchNotFoundException e) {
            e.printStackTrace();
        } catch (MagicException e) {
            e.printStackTrace();
        }

        if (type==null){
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition","inline;filename="+filePath);
            httpHeaders.add("Content-Type","text/plain");
            return new ResponseEntity<>("暂不支持的类型",httpHeaders,HttpStatus.OK);
        }else{
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.add("Content-Disposition","inline;filename="+filePath);
            httpHeaders.add("Content-Type",type);
            return new ResponseEntity<>(array,httpHeaders,HttpStatus.OK);
        }
    }

    private String getPath(String path,HttpSession session){
        User user = (User) session.getAttribute("user");
        String home = user.getHome();
        if (!home.endsWith("/")){
            home = home + "/";
        }
        if (path.startsWith("/")){
            path = path.substring(1);
        }
        return home +path;
    }

    @RequestMapping("/FStest")
    @ResponseBody
    public String test(){
        return "testOk";
    }

}
