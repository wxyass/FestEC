package com.diabin.latte.ui.camera;

import android.net.Uri;

import com.diabin.latte.delegates.PermissionCheckerDelegate;
import com.diabin.latte.util.file.FileUtil;

import java.io.File;

/**
 * Created by yangwenmin on 2017/12/23.
 * 照相机调用类
 */

public class LatteCamera {

    // 获取图片剪裁后 文件的Uri路径
    public static Uri creatCropFile(){
        // 获取图片名称(随机命名,包含后缀,比如: IMG_20171223_112844.jpg)
        String fileName = FileUtil.getFileNameByTime("IMG","jpg");
        // 获取文件路径 // 比如:   /storage/emulated/0/crop_image/IMG_20171223_221700.jpg
        String filePath = FileUtil.createFile("crop_image",fileName).getPath();
        return Uri.parse(filePath);

    }

    // 调用打开弹窗的方法
    public static void start(PermissionCheckerDelegate delegate){
        new CameraHandler(delegate).beginCameraDialog();
    }
}
