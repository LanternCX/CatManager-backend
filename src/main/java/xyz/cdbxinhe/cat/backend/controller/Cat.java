package xyz.cdbxinhe.cat.backend.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import xyz.cdbxinhe.cat.backend.ConfigLoader;
import xyz.cdbxinhe.cat.backend.ConstantVariates;
import xyz.cdbxinhe.cat.backend.Response;
import xyz.cdbxinhe.cat.backend.dao.CatRepository;
import xyz.cdbxinhe.cat.backend.exception.NetworkException;
import xyz.cdbxinhe.cat.backend.exception.PayloadException;
import xyz.cdbxinhe.cat.backend.util.common.IoUtils;
import xyz.cdbxinhe.cat.backend.XyApplication;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.*;
import java.util.List;

/**
 * 猫猫操作相关接口
 * package xyz.cdbxinhe.cat.backend.controller
 * project backend
 * Created by @author CaoXin on date 2023/03/01
 */
@RestController
@RequestMapping("/cat")
@CrossOrigin
public class Cat {
    private CatRepository catRepository;
    private ConfigLoader configLoader;
    private static final String KEY_NAME = "name";
    private static final String KEY_LOCATION = "location";
    private static final String KEY_DESC = "description";
    private static final String KEY_POSTER = "poster";

    private static final String EMPTY_FLAG = " ";
    private static final String EMPTY_FLAG_2 = "";
    /**
     * 使用构造器方法注入依赖
     */
    @Autowired
    private void autoWire(CatRepository catRepository,ConfigLoader configLoader) {
        this.catRepository = catRepository;
        this.configLoader = configLoader;
    }

    /**
     * 获取猫猫列表
     * @return 猫猫列表
     */
    @GetMapping("")
    public Response getAllCat(){
        List<xyz.cdbxinhe.cat.backend.entity.Cat> catList = new ArrayList<>();
        catRepository.findAll().forEach(catList::add);
        return new Response(new HashMap<>(1){{
            put("list",catList);
        }});
    }

    /**
     * 从猫猫id获取猫猫信息
     * @param id 猫猫id
     * @return 猫猫信息
     * @throws PayloadException 找不到猫猫
     */
    @GetMapping("/{id}")
    public Response getCatInfoById(@PathVariable Long id) throws PayloadException {
        if (catRepository.findById(id).isEmpty()){
            throw new PayloadException("找不到猫猫");
        }
        xyz.cdbxinhe.cat.backend.entity.Cat cat = catRepository.findById(id).get();
        return new Response(new HashMap<>(1){{
            put("data",cat);
        }});
    }

    /**
     * 删除猫猫
     * @param id 猫猫id
     * @return 更新后的猫猫列表
     * @throws PayloadException 找不到猫猫
     */
    @DeleteMapping("/{id}")
    public Response deleteCatById(@PathVariable Long id) throws PayloadException {
        if (catRepository.findById(id).isEmpty()){
            throw new PayloadException("找不到猫猫");
        }
        xyz.cdbxinhe.cat.backend.entity.Cat cat = catRepository.findById(id).get();
        catRepository.delete(cat);
        return new Response(new HashMap<>(1){{
            put("list",catRepository.findAll());
        }});
    }

    /**
     * 修改猫猫信息
     * @param id 猫猫id
     * @param requestBody 请求体
     * @return 猫猫列表
     * @throws PayloadException 参数错误
     */
    @PutMapping("/{id}")
    public Response modifyCatInfo(@PathVariable Long id, @RequestBody JSONObject requestBody) throws PayloadException {
        if (catRepository.findById(id).isEmpty()){
            throw new PayloadException("找不到猫猫");
        }
        xyz.cdbxinhe.cat.backend.entity.Cat cat = catRepository.findById(id).get();
        if (!requestBody.containsKey(KEY_NAME) ||
                !requestBody.containsKey(KEY_LOCATION) ||
                !requestBody.containsKey(KEY_DESC) ||
                !requestBody.containsKey(KEY_POSTER)
        ){
            throw new PayloadException("请写认真点,参数不全");
        }
        cat.setName(requestBody.getString(KEY_NAME));
        cat.setLocation(requestBody.getString(KEY_LOCATION));
        cat.setDescription(requestBody.getString(KEY_DESC));
        cat.setPoster(requestBody.getString(KEY_POSTER));
        catRepository.save(cat);
        return new Response(new HashMap<>(1){{
            put("list",catRepository.findAll());
        }});
    }

    /**
     * 创建新猫猫
     * @param name 猫猫名字
     * @return 新建的猫猫实例信息
     */
    @PostMapping("")
    public Response creatCat(@RequestParam("name") String name){
        xyz.cdbxinhe.cat.backend.entity.Cat cat = new xyz.cdbxinhe.cat.backend.entity.Cat(name,configLoader.getDefaultPoster());
        catRepository.save(cat);
        return new Response(new HashMap<>(1){{
            put("cat",cat);
        }});
    }

    /**
     * 上传海报图片
     *
     * @param multipartFile 图片
     * @return 图片链接
     * @throws PayloadException 文件格式不支持
     * @throws IOException      服务器内部错误
     */
    @PostMapping("/uploadPoster")
    @CrossOrigin
    public Object uploadPoster(@RequestParam("file") MultipartFile multipartFile) throws PayloadException, IOException {
        if (multipartFile.isEmpty()) {
            throw new PayloadException("文件为空");
        }
        String suffer = Objects.requireNonNull(multipartFile.getOriginalFilename()).substring(multipartFile.getOriginalFilename().lastIndexOf("."));
        if (
                (!".png".equalsIgnoreCase(suffer)) &&
                        !".jpg".equalsIgnoreCase(suffer) &&
                        !".jpeg".equalsIgnoreCase(suffer) &&
                        !".tiff".equalsIgnoreCase(suffer) &&
                        !".bmp".equalsIgnoreCase(suffer)
        ) {
            throw new PayloadException("文件类型不允许");
        }
        String newFilename = UUID.randomUUID().toString().replace("-", "") + ".jpg";

        // 创建存储目录
        File destFile = new File(configLoader.getImageRoot() + "\\posters\\" + newFilename);
        if (!destFile.getParentFile().exists()) {
            try {
                boolean success = destFile.getParentFile().mkdirs();
                if (!success) {
                    throw new IOException("文件夹创建失败");
                }
            } catch (Exception ignored) {
                try {
                    boolean success = destFile.getParentFile().mkdir();
                    if (!success) {
                        throw new IOException("文件夹创建失败");
                    }
                } catch (Exception ignored2) {
                }
            }
        }

        // 统一转换为JPEG存储
        File tempImgSrc = new File(XyApplication.CatLiveTempPath.toFile(), multipartFile.getOriginalFilename());
        multipartFile.transferTo(tempImgSrc);
        BufferedImage srcImg = ImageIO.read(tempImgSrc);
        BufferedImage finalImg = new BufferedImage(srcImg.getWidth(), srcImg.getHeight(), BufferedImage.TYPE_INT_RGB);
        finalImg.createGraphics().drawImage(srcImg, 0, 0, Color.WHITE, null);
        ImageIO.write(finalImg, "jpeg", destFile);
        tempImgSrc.delete();
        return new HashMap<>(2) {{
            put("success", true);
            put("posterUrl", "/cat/poster/" + newFilename);
        }};
    }

    /**
     * 拉取海报图片
     *
     * @param filename 文件名
     * @return 图片文件 image/jpeg
     * @throws PayloadException 文件不存在
     * @throws IOException      服务器内部错误
     */
    @GetMapping(value = "/poster/{filename}", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    @CrossOrigin
    public byte[] downloadPoster(@PathVariable("filename") String filename) throws PayloadException, IOException {
        File destFile = new File(configLoader.getImageRoot() + "\\posters\\" + filename);
        if (!destFile.exists()) {
            throw new PayloadException("文件不存在");
        }
        FileInputStream inputStream;
        try {
            inputStream = new FileInputStream(destFile);
        } catch (Exception ignored) {
            throw new PayloadException("文件不存在");
        }
        byte[] bytes = new byte[inputStream.available()];
        int len = inputStream.read(bytes, 0, inputStream.available());
        if (len == 0) {
            throw new IOException("文件读取失败");
        }
        return bytes;
    }
}
