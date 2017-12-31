package com.diabin.latte.delegates;

import android.Manifest;
import android.content.ContentValues;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.blankj.utilcode.util.FileUtils;
import com.diabin.latte.ui.camera.CameraImageBean;
import com.diabin.latte.ui.camera.LatteCamera;
import com.diabin.latte.ui.camera.RequestCodes;
import com.diabin.latte.util.callback.CallbackManager;
import com.diabin.latte.util.callback.CallbackType;
import com.diabin.latte.util.callback.IGlobalCallback;
import com.diabin.latte.util.file.FileUtil;
import com.yalantis.ucrop.UCrop;

import java.io.File;

import permissions.dispatcher.NeedsPermission;
import permissions.dispatcher.OnNeverAskAgain;
import permissions.dispatcher.OnPermissionDenied;
import permissions.dispatcher.OnShowRationale;
import permissions.dispatcher.PermissionRequest;
import permissions.dispatcher.RuntimePermissions;

/**
 * 中间层: 权限判定
 * Created by yangwenmin on 2017/10/15.
 */
@RuntimePermissions
public abstract class PermissionCheckerDelegate extends BaseDelegate {


    //不是直接调用方法  // 通过在方法上添加注解,表示这个方法要执行的代码需要权限,Rebuild后,会生成真正能调用的方法
    @NeedsPermission({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void startCamera() {
        LatteCamera.start(this);
    }

    // 这是真正调用的方法
    public void startCameraWithCheck() {
        PermissionCheckerDelegatePermissionsDispatcher.startCameraWithCheck(this);
    }

    // OnPermissionDenied: 当用户拒绝相机权限时,调用该方法
    @OnPermissionDenied({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onCameraDenied() {
        Toast.makeText(getContext(), "不允许拍照", Toast.LENGTH_SHORT).show();
    }

    // OnNeverAskAgain: 当用户选择不再询问相机权限后,不管用户选择了拒绝还是同意,调用该方法,之后不再询问该权限
    @OnNeverAskAgain({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onCameraNever() {
        Toast.makeText(getContext(), "不再询问", Toast.LENGTH_SHORT).show();
    }

    // 弹出一个解释为什么需要相机权限,用户可同意或拒绝
    @OnShowRationale({Manifest.permission.CAMERA,Manifest.permission.WRITE_EXTERNAL_STORAGE})
    void onCameraRationale(PermissionRequest request) {
        showRationaleDialog(request);
    }

    private void showRationaleDialog(final PermissionRequest request) {
        new AlertDialog.Builder(getContext())
                .setMessage("权限管理")
                .setPositiveButton("同意使用", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.proceed();
                    }
                })
                .setNegativeButton("拒绝使用", new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        request.cancel();
                    }
                })
                .setCancelable(false)
                .show();
    }

    // 调用辅助类处理放回的结果
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        PermissionCheckerDelegatePermissionsDispatcher.onRequestPermissionsResult(this, requestCode, grantResults);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            switch (requestCode){
                case RequestCodes.TAKE_PHONE:// 拍照相机 回来的
                    // 获取照片文件路径
                    final Uri resultUri =CameraImageBean.getInstance().getmPath();
                    /*File tempFile = CameraImageBean.getInstance().getFile();
                    Uri resultUri = getResultUri(getContext(),tempFile);*/


                    // 去剪裁,将原图覆盖掉
                    UCrop.of(resultUri,resultUri)
                            .withMaxResultSize(400,400)
                            .start(getContext(),this);

                    break;
                case RequestCodes.PICK_PHONE:// 相册 回来的
                    if(data != null){
                        // 获取从相册选择的图片路径
                        final Uri pickPath = data.getData();
                        // 从相册选择后需要有个路径存放剪裁过的图片
                        final String pickCropPath = LatteCamera.creatCropFile().getPath();
                        // 去剪裁,在新位置生成一张剪裁后的图片
                        UCrop.of(pickPath,Uri.parse(pickCropPath))
                                .withMaxResultSize(400,400)
                                .start(getContext(),this);
                    }
                    break;
                case RequestCodes.CROP_PHONE:// 剪裁成功 回来的
                    // 获取裁剪后的图片路径
                    final Uri cropUri = UCrop.getOutput(data);
                    // 拿到剪裁后的数据进行处理
                    final IGlobalCallback<Uri> callbackcrop = CallbackManager.getInstance().getCallback(CallbackType.ON_CROP);
                    if(callbackcrop!=null){
                        callbackcrop.executeCallback(cropUri);
                    }

                    break;
                case RequestCodes.CROP_ERROR:// 剪裁失败 回来的
                    Toast.makeText(getContext(), "剪裁出错了", Toast.LENGTH_SHORT).show();
                    break;
                default:
                    break;
            }

        }
    }

    // 通过图片File 获取文件Uri
    private Uri getResultUri(Context context, File tempFile){
        final ContentValues contentValues = new ContentValues(1);// ?
        contentValues.put(MediaStore.Images.Media.DATA, tempFile.getPath());//?
        final Uri uri = context.getContentResolver().insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, contentValues);
        // 需要将Uri路径转化为实际路径?
        final File realFile = FileUtils.getFileByPath(FileUtil.getRealFilePath(context, uri));
        final Uri resultUri = Uri.fromFile(realFile);// 将File转为Uri
        return resultUri;
    }
}
